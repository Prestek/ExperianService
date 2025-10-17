package com.prestek.ExperianService.service.impl;

import com.prestek.ExperianService.model.CreditHistory;
import com.prestek.ExperianService.repository.CreditHistoryRepository;
import com.prestek.ExperianService.repository.document.CreditHistoryDocument;
import com.prestek.ExperianService.service.CreditHistoryService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Primary
//@ConditionalOnBean(CreditHistoryRepository.class)
public class MongoCreditHistoryService implements CreditHistoryService {

    private final CreditHistoryRepository repository;

    public MongoCreditHistoryService(CreditHistoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<CreditHistory> getCreditHistory(String userId) {
        return repository.findById(userId).map(this::toModel);
    }

    @Override
    public void saveCreditHistory(String userId, CreditHistory creditHistory) {
        CreditHistoryDocument doc = toDocument(userId, creditHistory);
        repository.save(doc);
    }

    private CreditHistory toModel(CreditHistoryDocument d) {
        return new CreditHistory(
                d.getScore(),
                d.getNumberOfDelinquencies(),
                d.getNumberOfChargedOffAccounts(),
                d.getNumberOfRatingsCDE(),
                d.getNumberOfSeizedAccounts(),
                d.getNumberOfAccountsCancelledDueToMismanagement(),
                d.isFewerThanSixCreditBureauInquiries(),
                d.getNumberOfCurrentDelinquencies(),
                d.getNumberOfDelinquenciesOver30Days(),
                d.getNumberOfDelinquenciesOver60Days()
        );
    }

    private CreditHistoryDocument toDocument(String userId, CreditHistory m) {
        return new CreditHistoryDocument(
                userId,
                m.score(),
                m.numberOfDelinquencies(),
                m.numberOfChargedOffAccounts(),
                m.numberOfRatingsCDE(),
                m.numberOfSeizedAccounts(),
                m.numberOfAccountsCancelledDueToMismanagement(),
                m.fewerThanSixCreditBureauInquiries(),
                m.numberOfCurrentDelinquencies(),
                m.numberOfDelinquenciesOver30Days(),
                m.numberOfDelinquenciesOver60Days()
        );
    }
}
