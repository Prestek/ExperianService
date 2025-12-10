package com.prestek.ExperianService.service.impl;

import com.prestek.ExperianService.model.CreditHistory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryCreditHistoryServiceTest {

    private InMemoryCreditHistoryService service;

    @BeforeEach
    void setUp() {
        service = new InMemoryCreditHistoryService();
    }

    @Test
    void testGetCreditHistory_ExistingUser() {
        Optional<CreditHistory> result = service.getCreditHistory("user123");

        assertTrue(result.isPresent());
        assertEquals(7.0, result.get().score());
        assertEquals(3, result.get().numberOfDelinquencies());
        assertEquals(0, result.get().numberOfChargedOffAccounts());
        assertTrue(result.get().fewerThanSixCreditBureauInquiries());
    }

    @Test
    void testGetCreditHistory_AnotherExistingUser() {
        Optional<CreditHistory> result = service.getCreditHistory("user456");

        assertTrue(result.isPresent());
        assertEquals(5.6, result.get().score());
        assertEquals(1, result.get().numberOfDelinquencies());
        assertFalse(result.get().fewerThanSixCreditBureauInquiries());
    }

    @Test
    void testGetCreditHistory_NonExistingUser() {
        Optional<CreditHistory> result = service.getCreditHistory("nonexistent");

        assertFalse(result.isPresent());
    }

    @Test
    void testSaveCreditHistory_NewUser() {
        CreditHistory newHistory = new CreditHistory(
                8.5, 1, 0, 0, 0, 0, true, 0, 1, 0);

        service.saveCreditHistory("newUser", newHistory);

        Optional<CreditHistory> result = service.getCreditHistory("newUser");
        assertTrue(result.isPresent());
        assertEquals(8.5, result.get().score());
        assertEquals(1, result.get().numberOfDelinquencies());
    }

    @Test
    void testSaveCreditHistory_UpdateExistingUser() {
        CreditHistory updatedHistory = new CreditHistory(
                9.0, 0, 0, 0, 0, 0, true, 0, 0, 0);

        service.saveCreditHistory("user123", updatedHistory);

        Optional<CreditHistory> result = service.getCreditHistory("user123");
        assertTrue(result.isPresent());
        assertEquals(9.0, result.get().score());
        assertEquals(0, result.get().numberOfDelinquencies());
    }
}
