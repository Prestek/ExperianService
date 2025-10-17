package com.prestek.ExperianService.service.impl;

import com.prestek.ExperianService.model.CreditHistory;
import com.prestek.ExperianService.repository.CreditHistoryRepository;
import com.prestek.ExperianService.service.CreditHistoryService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Service
@Profile("inmemory")
//@ConditionalOnMissingBean(CreditHistoryRepository.class)
public class InMemoryCreditHistoryService implements CreditHistoryService {

    private final Map<String, CreditHistory> data = new ConcurrentHashMap<>(Map.of(
            "user123", new CreditHistory(7.0, 3, 0, 0, 0, 0, true, 0, 2, 1),
            "user456", new CreditHistory(5.6, 1, 0, 0, 0, 0, false, 0, 1, 0)
    ));

    @Override
    public Optional<CreditHistory> getCreditHistory(String userId) {
        return Optional.ofNullable(data.get(userId));
    }

    @Override
    public void saveCreditHistory(String userId, CreditHistory creditHistory) {
        data.put(userId, creditHistory);
    }
}
