package org.example.personalfinanceapp.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.example.personalfinanceapp.enums.AccountType;

public class AccountRequestDTO {

    @NotBlank
    @Size(min = 2, max = 100)
    private String bankName;

    @NotNull
    private AccountType accountType;

    public AccountRequestDTO(){}

    public AccountRequestDTO(String bankName, AccountType accountType){

        this.bankName = bankName;
        this.accountType = accountType;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }
}
