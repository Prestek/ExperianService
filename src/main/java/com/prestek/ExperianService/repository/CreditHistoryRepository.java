package com.prestek.ExperianService.repository;

import com.prestek.ExperianService.repository.document.CreditHistoryDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditHistoryRepository extends MongoRepository<CreditHistoryDocument, String> {
}

