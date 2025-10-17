package com.prestek.ExperianService.service;

import com.prestek.ExperianService.model.CreditHistory;

import java.util.Optional;

public interface CreditHistoryService {
    Optional<CreditHistory> getCreditHistory(String userId);

    void saveCreditHistory(String userId, CreditHistory creditHistory);
}
