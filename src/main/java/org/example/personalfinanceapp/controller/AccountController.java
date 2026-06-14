package org.example.personalfinanceapp.controller;

import jakarta.validation.Valid;
import org.example.personalfinanceapp.dto.AccountRequestDTO;
import org.example.personalfinanceapp.dto.AccountResponseDTO;
import org.example.personalfinanceapp.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/accounts")
public class AccountController {

    private AccountService accountService;

    public  AccountController(AccountService accountService){

        this.accountService = accountService;

    }

    @PostMapping("/add-account")
    public ResponseEntity<AccountResponseDTO> addBankAccount(
            Authentication authentication,
            @Valid @RequestBody AccountRequestDTO accountRequestDTO){

        String email = authentication.getName();

        AccountResponseDTO addAccount = accountService.addBankAccount(email,accountRequestDTO);

        return new ResponseEntity<>(addAccount, HttpStatus.CREATED);
    }

    @GetMapping("/get-accounts")
    public ResponseEntity<List<AccountResponseDTO>> getAllBankAccounts(Authentication authentication){

        String email = authentication.getName();

        List<AccountResponseDTO> allBankAccounts = accountService.getAllBankAccounts(email);

        return ResponseEntity.ok(allBankAccounts);
    }
}
