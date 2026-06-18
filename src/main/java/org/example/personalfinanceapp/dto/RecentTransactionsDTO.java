package org.example.personalfinanceapp.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class RecentTransactionsDTO {

    private Long transactionId;

    private Long categoryId;

    private Long accountId;

    private String description;

    private BigDecimal amount;

    private String accountName;

    private String categoryName;

    private LocalDate transactionDate;


    public RecentTransactionsDTO(Long transactionId,
                                 Long categoryId,
                                 Long accountId,
                                 String description,
                                 BigDecimal amount,
                                 String accountName,
                                 String categoryName,
                                 LocalDate transactionDate) {

        this.transactionId = transactionId;
        this.categoryId = categoryId;
        this.accountId = accountId;
        this.description = description;
        this.amount = amount;
        this.accountName = accountName;
        this.categoryName = categoryName;
        this.transactionDate = transactionDate;

    }

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDate getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDate transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
