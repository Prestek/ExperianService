package com.prestek.ExperianService.controller;

import com.prestek.ExperianService.model.CreditHistory;
import com.prestek.ExperianService.service.CreditHistoryService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CreditHistoryController.class)
@AutoConfigureMockMvc(addFilters = false)
class CreditHistoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private CreditHistoryService creditHistoryService;

    @Test
    @DisplayName("GET /api/credit-history/{userId} returns 200 with ApiResponse and CreditHistory payload")
    void getCreditHistory_ok() throws Exception {
        CreditHistory payload = new CreditHistory(
                7.0,
                3,
                0,
                0,
                0,
                0,
                true,
                0,
                2,
                1);
        when(creditHistoryService.getCreditHistory("user123")).thenReturn(Optional.of(payload));

        mockMvc.perform(get("/api/credit-history/{userId}", "user123").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.message").value("OK"))
                .andExpect(jsonPath("$.data.score").value(7.0))
                .andExpect(jsonPath("$.data.numberOfDelinquencies").value(3))
                .andExpect(jsonPath("$.data.numberOfChargedOffAccounts").value(0))
                .andExpect(jsonPath("$.data.numberOfRatingsCDE").value(0))
                .andExpect(jsonPath("$.data.numberOfSeizedAccounts").value(0))
                .andExpect(jsonPath("$.data.numberOfAccountsCancelledDueToMismanagement").value(0))
                .andExpect(jsonPath("$.data.fewerThanSixCreditBureauInquiries").value(true))
                .andExpect(jsonPath("$.data.numberOfCurrentDelinquencies").value(0))
                .andExpect(jsonPath("$.data.numberOfDelinquenciesOver30Days").value(2))
                .andExpect(jsonPath("$.data.numberOfDelinquenciesOver60Days").value(1));
    }

    @Test
    @DisplayName("GET /api/credit-history/{userId} returns 200 with random data when not found")
    void getCreditHistory_notFound_returnsRandomData() throws Exception {
        when(creditHistoryService.getCreditHistory("unknown")).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/credit-history/{userId}", "unknown").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.message").value("Random generated history"))
                .andExpect(jsonPath("$.data").exists())
                .andExpect(jsonPath("$.data.score").exists());
    }

    @Test
    @DisplayName("POST /api/credit-history/{userId} saves and returns the credit history")
    void upsertCreditHistory_success() throws Exception {
        String jsonContent = """
                {
                    "score": 7.5,
                    "numberOfDelinquencies": 3,
                    "numberOfChargedOffAccounts": 1,
                    "numberOfRatingsCDE": 2,
                    "numberOfSeizedAccounts": 0,
                    "numberOfAccountsCancelledDueToMismanagement": 1,
                    "fewerThanSixCreditBureauInquiries": true,
                    "numberOfCurrentDelinquencies": 2,
                    "numberOfDelinquenciesOver30Days": 1,
                    "numberOfDelinquenciesOver60Days": 0
                }
                """;

        mockMvc.perform(post("/api/credit-history/{userId}", "user123")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonContent))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.message").value("Saved"))
                .andExpect(jsonPath("$.data.score").value(7.5))
                .andExpect(jsonPath("$.data.numberOfDelinquencies").value(3));

        verify(creditHistoryService, times(1)).saveCreditHistory(eq("user123"), any(CreditHistory.class));
    }

    @Test
    @DisplayName("POST /api/credit-history/{userId} handles update correctly")
    void upsertCreditHistory_update() throws Exception {
        String jsonContent = """
                {
                    "score": 9.0,
                    "numberOfDelinquencies": 0,
                    "numberOfChargedOffAccounts": 0,
                    "numberOfRatingsCDE": 0,
                    "numberOfSeizedAccounts": 0,
                    "numberOfAccountsCancelledDueToMismanagement": 0,
                    "fewerThanSixCreditBureauInquiries": true,
                    "numberOfCurrentDelinquencies": 0,
                    "numberOfDelinquenciesOver30Days": 0,
                    "numberOfDelinquenciesOver60Days": 0
                }
                """;

        mockMvc.perform(post("/api/credit-history/{userId}", "existingUser")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonContent))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.score").value(9.0));

        verify(creditHistoryService, times(1)).saveCreditHistory(eq("existingUser"), any(CreditHistory.class));
    }
}
