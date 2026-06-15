package org.example.personalfinanceapp.controller;

import jakarta.validation.Valid;
import org.example.personalfinanceapp.dto.TransactionRequestDTO;
import org.example.personalfinanceapp.dto.TransactionResponseDTO;
import org.example.personalfinanceapp.service.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService){

        this.transactionService = transactionService;
    }

    @PostMapping
    public ResponseEntity<TransactionResponseDTO> addTransaction(Authentication authentication,
                                                                 @Valid @RequestBody
                                                                 TransactionRequestDTO transactionRequestDTO){
        String email = authentication.getName();

        TransactionResponseDTO newTransaction = transactionService.addTransaction(email, transactionRequestDTO);

        return new ResponseEntity<>(newTransaction, HttpStatus.CREATED);


    }
}
