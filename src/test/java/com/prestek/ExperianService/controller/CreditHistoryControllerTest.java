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

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
                1
        );
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
    @DisplayName("GET /api/credit-history/{userId} returns 404 with standardized ApiResponse when not found")
    void getCreditHistory_notFound() throws Exception {
        when(creditHistoryService.getCreditHistory("unknown")).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/credit-history/{userId}", "unknown").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.success").value(false))
                .andExpect(jsonPath("$.message").exists())
                .andExpect(jsonPath("$.data").doesNotExist());
    }
}
