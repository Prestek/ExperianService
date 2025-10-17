package com.prestek.ExperianService.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CreditHistory(
        double score,
        int numberOfDelinquencies,
        int numberOfChargedOffAccounts,
        int numberOfRatingsCDE,
        int numberOfSeizedAccounts,
        int numberOfAccountsCancelledDueToMismanagement,
        boolean fewerThanSixCreditBureauInquiries,
        int numberOfCurrentDelinquencies,
        int numberOfDelinquenciesOver30Days,
        int numberOfDelinquenciesOver60Days
) {}

