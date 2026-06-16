package org.example.personalfinanceapp.controller;

import jakarta.validation.Valid;
import org.example.personalfinanceapp.dto.RecurringExpenseRequestDTO;
import org.example.personalfinanceapp.dto.RecurringExpenseResponseDTO;
import org.example.personalfinanceapp.service.RecurringExpenseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/recurring-expenses")
public class RecurringExpenseController {

    private final RecurringExpenseService recurringExpenseService;

    public RecurringExpenseController(RecurringExpenseService recurringExpenseService){

        this.recurringExpenseService = recurringExpenseService;
    }

    @PostMapping
    public ResponseEntity<RecurringExpenseResponseDTO> addRecurringExpense
            (Authentication authentication,
             @Valid @RequestBody RecurringExpenseRequestDTO recurringExpenseRequestDTO){

        String email = authentication.getName();

        RecurringExpenseResponseDTO addedRecurringExpense = recurringExpenseService
                .addRecurringExpense(email, recurringExpenseRequestDTO);

        return new ResponseEntity<>(addedRecurringExpense, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<RecurringExpenseResponseDTO>> getAllRecurringExpenses(Authentication authentication){

        String email = authentication.getName();

        List<RecurringExpenseResponseDTO> allRecurringExpenses = recurringExpenseService
                .getAllRecurringExpenses(email);

        return ResponseEntity.ok(allRecurringExpenses);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RecurringExpenseResponseDTO> updateRecurringExpense(
            Authentication authentication,
            @PathVariable Long id,
            @Valid @RequestBody RecurringExpenseRequestDTO recurringExpenseRequestDTO){


        String email = authentication.getName();

        RecurringExpenseResponseDTO updatedRecurringExpense = recurringExpenseService
                .updateRecurringExpense(email, id, recurringExpenseRequestDTO);

        return ResponseEntity.ok(updatedRecurringExpense);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRecurringExpense(Authentication authentication, @PathVariable Long id){


        String email = authentication.getName();

        recurringExpenseService.deleteRecurringExpense(email,id);

        return ResponseEntity.noContent().build();
    }
}
