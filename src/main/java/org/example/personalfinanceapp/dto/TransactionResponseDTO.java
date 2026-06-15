package org.example.personalfinanceapp.dto;

import org.example.personalfinanceapp.enums.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class TransactionResponseDTO {

    private Long id;

    private TransactionType transactionType;

    private Long accountId;

    private String accountName;

    private Long categoryId;

    private String categoryName;

    private Long fromAccountId;

    private String fromAccountName;

    private Long toAccountId;

    private String toAccountName;

    private BigDecimal amount;

    private LocalDate transactionDate;

    private String description;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public TransactionResponseDTO(Long id,
                                  TransactionType transactionType,
                                  Long accountId,
                                  String accountName,
                                  Long categoryId,
                                  String categoryName,
                                  Long fromAccountId,
                                  String fromAccountName,
                                  Long toAccountId,
                                  String toAccountName,
                                  BigDecimal amount,
                                  LocalDate transactionDate,
                                  String description,
                                  LocalDateTime createdAt,
                                  LocalDateTime updatedAt) {
        this.id = id;
        this.transactionType = transactionType;
        this.accountId = accountId;
        this.accountName = accountName;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.fromAccountId = fromAccountId;
        this.fromAccountName = fromAccountName;
        this.toAccountId = toAccountId;
        this.toAccountName = toAccountName;
        this.amount = amount;
        this.transactionDate = transactionDate;
        this.description = description;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }


    public Long getId() {
        return id;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public Long getAccountId() {
        return accountId;
    }

    public String getAccountName() {
        return accountName;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public Long getFromAccountId() {
        return fromAccountId;
    }

    public String getFromAccountName() {
        return fromAccountName;
    }

    public Long getToAccountId() {
        return toAccountId;
    }

    public String getToAccountName() {
        return toAccountName;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public LocalDate getTransactionDate() {
        return transactionDate;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
