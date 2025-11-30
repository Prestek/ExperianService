package com.prestek.ExperianService.controller;

import com.prestek.ExperianService.model.ApiResponse;
import com.prestek.ExperianService.model.CreditHistory;
import com.prestek.ExperianService.service.CreditHistoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/credit-history")
public class CreditHistoryController {

    private final CreditHistoryService creditHistoryService;

    public CreditHistoryController(CreditHistoryService creditHistoryService) {
        this.creditHistoryService = creditHistoryService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<ApiResponse<CreditHistory>> getCreditHistory(@PathVariable String userId) {
        return creditHistoryService.getCreditHistory(userId)
                .map(history -> ResponseEntity.ok(new ApiResponse<>(true, "OK", history)))
                .orElseGet(() -> {
                    CreditHistory defaultHistory = new CreditHistory(0.0, 0, 0, 0, 0, 0, true, 0, 0, 0);
                    return ResponseEntity.ok(new ApiResponse<>(true, "Default history", defaultHistory));
                });
    }

    @PostMapping("/{userId}")
    public ResponseEntity<ApiResponse<CreditHistory>> upsertCreditHistory(
            @PathVariable String userId,
            @RequestBody CreditHistory creditHistory
    ) {
        creditHistoryService.saveCreditHistory(userId, creditHistory);
        return ResponseEntity.ok(new ApiResponse<>(true, "Saved", creditHistory));
    }
}
