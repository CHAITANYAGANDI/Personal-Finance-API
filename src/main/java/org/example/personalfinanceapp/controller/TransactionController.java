package org.example.personalfinanceapp.controller;

import jakarta.validation.Valid;
import org.example.personalfinanceapp.dto.TransactionRequestDTO;
import org.example.personalfinanceapp.dto.TransactionResponseDTO;
import org.example.personalfinanceapp.enums.TransactionType;
import org.example.personalfinanceapp.service.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping
    public ResponseEntity<List<TransactionResponseDTO>> getAllTransactions(Authentication authentication){

        String email = authentication.getName();

        List<TransactionResponseDTO> listOfAllTransactions = transactionService.getAllTransactions(email);

        return ResponseEntity.ok(listOfAllTransactions);
    }

    @GetMapping
    public ResponseEntity<List<TransactionResponseDTO>> getSpecifiedTransactions
            (Authentication authentication, @RequestParam(required = false) TransactionType transactionType){

        String email = authentication.getName();

        List<TransactionResponseDTO> listOfSpecifiedTransactions = transactionService
                .getSpecifiedTransactions(email, transactionType);

        return ResponseEntity.ok(listOfSpecifiedTransactions);
    }

}
