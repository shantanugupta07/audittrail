package com.shantanugupta.audittrail.dto;

import java.math.BigDecimal;

public class TransactionUploadRequest {
    private String transactionReference;
    private String accountId;
    private BigDecimal amount;
    private String currency;

    // --- Getters and Setters ---
    public String getTransactionReference() { return transactionReference; }
    public void setTransactionReference(String txnRef) { this.transactionReference = txnRef; }
    public String getAccountId() { return accountId; }
    public void setAccountId(String accId) { this.accountId = accId; }
    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amt) { this.amount = amt; }
    public String getCurrency() { return currency; }
    public void setCurrency(String curr) { this.currency = curr; }
}