package org.example.personalfinanceapp.dto;

import org.example.personalfinanceapp.enums.AccountType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class AccountResponseDTO {

    private Long id;
    private Long accountNumber;
    private String bankName;
    private AccountType accountType;
    private BigDecimal balance;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public AccountResponseDTO(Long id,
                              Long accountNumber,
                              String bankName,
                              AccountType accountType,
                              BigDecimal balance,
                              LocalDateTime createdAt,
                              LocalDateTime updatedAt){

        this.id = id;
        this.accountNumber = accountNumber;
        this.bankName = bankName;
        this.accountType = accountType;
        this.balance = balance;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public Long getAccountNumber() {
        return accountNumber;
    }

    public String getBankName() {
        return bankName;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
