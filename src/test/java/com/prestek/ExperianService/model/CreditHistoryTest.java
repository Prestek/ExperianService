package com.prestek.ExperianService.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CreditHistoryTest {

    @Test
    void testCreditHistory_Creation() {
        CreditHistory creditHistory = new CreditHistory(
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

        assertEquals(7.5, creditHistory.score());
        assertEquals(3, creditHistory.numberOfDelinquencies());
        assertEquals(1, creditHistory.numberOfChargedOffAccounts());
        assertEquals(2, creditHistory.numberOfRatingsCDE());
        assertEquals(0, creditHistory.numberOfSeizedAccounts());
        assertEquals(1, creditHistory.numberOfAccountsCancelledDueToMismanagement());
        assertTrue(creditHistory.fewerThanSixCreditBureauInquiries());
        assertEquals(2, creditHistory.numberOfCurrentDelinquencies());
        assertEquals(1, creditHistory.numberOfDelinquenciesOver30Days());
        assertEquals(0, creditHistory.numberOfDelinquenciesOver60Days());
    }

    @Test
    void testCreditHistory_Equality() {
        CreditHistory ch1 = new CreditHistory(7.5, 3, 1, 2, 0, 1, true, 2, 1, 0);
        CreditHistory ch2 = new CreditHistory(7.5, 3, 1, 2, 0, 1, true, 2, 1, 0);

        assertEquals(ch1, ch2);
        assertEquals(ch1.hashCode(), ch2.hashCode());
    }

    @Test
    void testCreditHistory_Inequality() {
        CreditHistory ch1 = new CreditHistory(7.5, 3, 1, 2, 0, 1, true, 2, 1, 0);
        CreditHistory ch2 = new CreditHistory(8.5, 3, 1, 2, 0, 1, true, 2, 1, 0);

        assertNotEquals(ch1, ch2);
    }

    @Test
    void testCreditHistory_ToString() {
        CreditHistory creditHistory = new CreditHistory(7.5, 3, 1, 2, 0, 1, true, 2, 1, 0);

        String toString = creditHistory.toString();

        assertNotNull(toString);
        assertTrue(toString.contains("7.5"));
    }
}
