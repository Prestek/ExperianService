package com.prestek.ExperianService.repository.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "credit_histories")
public class CreditHistoryDocument {
    @Id
    private String userId;

    private double score;
    private int numberOfDelinquencies;
    private int numberOfChargedOffAccounts;
    private int numberOfRatingsCDE;
    private int numberOfSeizedAccounts;
    private int numberOfAccountsCancelledDueToMismanagement;
    private boolean fewerThanSixCreditBureauInquiries;

    private int numberOfCurrentDelinquencies;
    private int numberOfDelinquenciesOver30Days;
    private int numberOfDelinquenciesOver60Days;

    public CreditHistoryDocument() {}

    public CreditHistoryDocument(String userId, double score, int numberOfDelinquencies, int numberOfChargedOffAccounts, int numberOfRatingsCDE, int numberOfSeizedAccounts, int numberOfAccountsCancelledDueToMismanagement, boolean fewerThanSixCreditBureauInquiries, int numberOfCurrentDelinquencies, int numberOfDelinquenciesOver30Days, int numberOfDelinquenciesOver60Days) {
        this.userId = userId;
        this.score = score;
        this.numberOfDelinquencies = numberOfDelinquencies;
        this.numberOfChargedOffAccounts = numberOfChargedOffAccounts;
        this.numberOfRatingsCDE = numberOfRatingsCDE;
        this.numberOfSeizedAccounts = numberOfSeizedAccounts;
        this.numberOfAccountsCancelledDueToMismanagement = numberOfAccountsCancelledDueToMismanagement;
        this.fewerThanSixCreditBureauInquiries = fewerThanSixCreditBureauInquiries;
        this.numberOfCurrentDelinquencies = numberOfCurrentDelinquencies;
        this.numberOfDelinquenciesOver30Days = numberOfDelinquenciesOver30Days;
        this.numberOfDelinquenciesOver60Days = numberOfDelinquenciesOver60Days;
    }

    // getters and setters
    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }
    public double getScore() { return score; }
    public void setScore(double score) { this.score = score; }
    public int getNumberOfDelinquencies() { return numberOfDelinquencies; }
    public void setNumberOfDelinquencies(int numberOfDelinquencies) { this.numberOfDelinquencies = numberOfDelinquencies; }
    public int getNumberOfChargedOffAccounts() { return numberOfChargedOffAccounts; }
    public void setNumberOfChargedOffAccounts(int numberOfChargedOffAccounts) { this.numberOfChargedOffAccounts = numberOfChargedOffAccounts; }
    public int getNumberOfRatingsCDE() { return numberOfRatingsCDE; }
    public void setNumberOfRatingsCDE(int numberOfRatingsCDE) { this.numberOfRatingsCDE = numberOfRatingsCDE; }
    public int getNumberOfSeizedAccounts() { return numberOfSeizedAccounts; }
    public void setNumberOfSeizedAccounts(int numberOfSeizedAccounts) { this.numberOfSeizedAccounts = numberOfSeizedAccounts; }
    public int getNumberOfAccountsCancelledDueToMismanagement() { return numberOfAccountsCancelledDueToMismanagement; }
    public void setNumberOfAccountsCancelledDueToMismanagement(int numberOfAccountsCancelledDueToMismanagement) { this.numberOfAccountsCancelledDueToMismanagement = numberOfAccountsCancelledDueToMismanagement; }
    public boolean isFewerThanSixCreditBureauInquiries() { return fewerThanSixCreditBureauInquiries; }
    public void setFewerThanSixCreditBureauInquiries(boolean fewerThanSixCreditBureauInquiries) { this.fewerThanSixCreditBureauInquiries = fewerThanSixCreditBureauInquiries; }
    public int getNumberOfCurrentDelinquencies() { return numberOfCurrentDelinquencies; }
    public void setNumberOfCurrentDelinquencies(int numberOfCurrentDelinquencies) { this.numberOfCurrentDelinquencies = numberOfCurrentDelinquencies; }
    public int getNumberOfDelinquenciesOver30Days() { return numberOfDelinquenciesOver30Days; }
    public void setNumberOfDelinquenciesOver30Days(int numberOfDelinquenciesOver30Days) { this.numberOfDelinquenciesOver30Days = numberOfDelinquenciesOver30Days; }
    public int getNumberOfDelinquenciesOver60Days() { return numberOfDelinquenciesOver60Days; }
    public void setNumberOfDelinquenciesOver60Days(int numberOfDelinquenciesOver60Days) { this.numberOfDelinquenciesOver60Days = numberOfDelinquenciesOver60Days; }
}

