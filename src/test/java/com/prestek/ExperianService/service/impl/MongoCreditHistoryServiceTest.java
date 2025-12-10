package com.prestek.ExperianService.service.impl;

import com.prestek.ExperianService.model.CreditHistory;
import com.prestek.ExperianService.repository.CreditHistoryRepository;
import com.prestek.ExperianService.repository.document.CreditHistoryDocument;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MongoCreditHistoryServiceTest {

    @Mock
    private CreditHistoryRepository repository;

    @InjectMocks
    private MongoCreditHistoryService service;

    private CreditHistoryDocument document;
    private CreditHistory creditHistory;

    @BeforeEach
    void setUp() {
        document = new CreditHistoryDocument(
                "user123",
                7.5,
                2,
                1,
                0,
                0,
                1,
                true,
                1,
                1,
                0);

        creditHistory = new CreditHistory(
                7.5,
                2,
                1,
                0,
                0,
                1,
                true,
                1,
                1,
                0);
    }

    @Test
    void testGetCreditHistory_Found() {
        when(repository.findById("user123")).thenReturn(Optional.of(document));

        Optional<CreditHistory> result = service.getCreditHistory("user123");

        assertTrue(result.isPresent());
        assertEquals(7.5, result.get().score());
        assertEquals(2, result.get().numberOfDelinquencies());
        assertEquals(1, result.get().numberOfChargedOffAccounts());
        assertTrue(result.get().fewerThanSixCreditBureauInquiries());

        verify(repository, times(1)).findById("user123");
    }

    @Test
    void testGetCreditHistory_NotFound() {
        when(repository.findById("nonexistent")).thenReturn(Optional.empty());

        Optional<CreditHistory> result = service.getCreditHistory("nonexistent");

        assertFalse(result.isPresent());
        verify(repository, times(1)).findById("nonexistent");
    }

    @Test
    void testSaveCreditHistory() {
        when(repository.save(any(CreditHistoryDocument.class))).thenReturn(document);

        service.saveCreditHistory("user123", creditHistory);

        verify(repository, times(1)).save(any(CreditHistoryDocument.class));
    }

    @Test
    void testSaveCreditHistory_VerifyMapping() {
        when(repository.save(any(CreditHistoryDocument.class))).thenAnswer(invocation -> {
            CreditHistoryDocument savedDoc = invocation.getArgument(0);
            assertEquals("user123", savedDoc.getUserId());
            assertEquals(7.5, savedDoc.getScore());
            assertEquals(2, savedDoc.getNumberOfDelinquencies());
            assertEquals(1, savedDoc.getNumberOfChargedOffAccounts());
            assertTrue(savedDoc.isFewerThanSixCreditBureauInquiries());
            return savedDoc;
        });

        service.saveCreditHistory("user123", creditHistory);
    }

    @Test
    void testToModel_AllFields() {
        when(repository.findById("user123")).thenReturn(Optional.of(document));

        Optional<CreditHistory> result = service.getCreditHistory("user123");

        assertTrue(result.isPresent());
        CreditHistory history = result.get();
        assertEquals(document.getScore(), history.score());
        assertEquals(document.getNumberOfDelinquencies(), history.numberOfDelinquencies());
        assertEquals(document.getNumberOfChargedOffAccounts(), history.numberOfChargedOffAccounts());
        assertEquals(document.getNumberOfRatingsCDE(), history.numberOfRatingsCDE());
        assertEquals(document.getNumberOfSeizedAccounts(), history.numberOfSeizedAccounts());
        assertEquals(document.getNumberOfAccountsCancelledDueToMismanagement(),
                history.numberOfAccountsCancelledDueToMismanagement());
        assertEquals(document.isFewerThanSixCreditBureauInquiries(), history.fewerThanSixCreditBureauInquiries());
        assertEquals(document.getNumberOfCurrentDelinquencies(), history.numberOfCurrentDelinquencies());
        assertEquals(document.getNumberOfDelinquenciesOver30Days(), history.numberOfDelinquenciesOver30Days());
        assertEquals(document.getNumberOfDelinquenciesOver60Days(), history.numberOfDelinquenciesOver60Days());
    }
}
