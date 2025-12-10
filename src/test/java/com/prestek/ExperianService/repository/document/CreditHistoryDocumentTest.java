package com.prestek.ExperianService.repository.document;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CreditHistoryDocumentTest {

    @Test
    void testNoArgsConstructor() {
        CreditHistoryDocument doc = new CreditHistoryDocument();

        assertNotNull(doc);
        assertNull(doc.getUserId());
    }

    @Test
    void testAllArgsConstructor() {
        CreditHistoryDocument doc = new CreditHistoryDocument(
                "user123",
                7.5,
                3,
                1,
                2,
                0,
                1,
                true,
                2,
                1,
                0);

        assertEquals("user123", doc.getUserId());
        assertEquals(7.5, doc.getScore());
        assertEquals(3, doc.getNumberOfDelinquencies());
        assertEquals(1, doc.getNumberOfChargedOffAccounts());
        assertEquals(2, doc.getNumberOfRatingsCDE());
        assertEquals(0, doc.getNumberOfSeizedAccounts());
        assertEquals(1, doc.getNumberOfAccountsCancelledDueToMismanagement());
        assertTrue(doc.isFewerThanSixCreditBureauInquiries());
        assertEquals(2, doc.getNumberOfCurrentDelinquencies());
        assertEquals(1, doc.getNumberOfDelinquenciesOver30Days());
        assertEquals(0, doc.getNumberOfDelinquenciesOver60Days());
    }

    @Test
    void testSetters() {
        CreditHistoryDocument doc = new CreditHistoryDocument();

        doc.setUserId("user456");
        doc.setScore(8.5);
        doc.setNumberOfDelinquencies(5);
        doc.setNumberOfChargedOffAccounts(2);
        doc.setNumberOfRatingsCDE(3);
        doc.setNumberOfSeizedAccounts(1);
        doc.setNumberOfAccountsCancelledDueToMismanagement(2);
        doc.setFewerThanSixCreditBureauInquiries(false);
        doc.setNumberOfCurrentDelinquencies(3);
        doc.setNumberOfDelinquenciesOver30Days(2);
        doc.setNumberOfDelinquenciesOver60Days(1);

        assertEquals("user456", doc.getUserId());
        assertEquals(8.5, doc.getScore());
        assertEquals(5, doc.getNumberOfDelinquencies());
        assertEquals(2, doc.getNumberOfChargedOffAccounts());
        assertEquals(3, doc.getNumberOfRatingsCDE());
        assertEquals(1, doc.getNumberOfSeizedAccounts());
        assertEquals(2, doc.getNumberOfAccountsCancelledDueToMismanagement());
        assertFalse(doc.isFewerThanSixCreditBureauInquiries());
        assertEquals(3, doc.getNumberOfCurrentDelinquencies());
        assertEquals(2, doc.getNumberOfDelinquenciesOver30Days());
        assertEquals(1, doc.getNumberOfDelinquenciesOver60Days());
    }

    @Test
    void testBooleanSetter() {
        CreditHistoryDocument doc = new CreditHistoryDocument();

        doc.setFewerThanSixCreditBureauInquiries(true);
        assertTrue(doc.isFewerThanSixCreditBureauInquiries());

        doc.setFewerThanSixCreditBureauInquiries(false);
        assertFalse(doc.isFewerThanSixCreditBureauInquiries());
    }
}
