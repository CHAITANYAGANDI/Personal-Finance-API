package org.example.personalfinanceapp.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import org.example.personalfinanceapp.enums.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDate;

public class TransactionRequestDTO {

    @NotNull
    private TransactionType transactionType;

    private Long accountId;

    private Long categoryId;

    private Long fromAccountId;

    private Long toAccountId;

    @NotNull
    @Positive
    private BigDecimal amount;

    @NotNull
    private LocalDate transactionDate;

    @Size(max = 255)
    private String description;

    public TransactionRequestDTO(){}

    public TransactionRequestDTO(TransactionType transactionType,
                                 Long accountId,
                                 Long categoryId,
                                 Long fromAccountId,
                                 Long toAccountId,
                                 BigDecimal amount,
                                 LocalDate transactionDate,
                                 String description){

        this.transactionType = transactionType;
        this.accountId = accountId;
        this.categoryId  = categoryId;
        this.fromAccountId = fromAccountId;
        this.toAccountId = toAccountId;
        this.amount = amount;
        this.transactionDate = transactionDate;
        this.description = description;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Long getFromAccountId() {
        return fromAccountId;
    }

    public void setFromAccountId(Long fromAccountId) {
        this.fromAccountId = fromAccountId;
    }

    public Long getToAccountId() {
        return toAccountId;
    }

    public void setToAccountId(Long toAccountId) {
        this.toAccountId = toAccountId;
    }

    public LocalDate getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDate transactionDate) {
        this.transactionDate = transactionDate;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
