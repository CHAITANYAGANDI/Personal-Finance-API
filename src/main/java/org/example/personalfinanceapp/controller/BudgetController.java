package org.example.personalfinanceapp.controller;

import jakarta.validation.Valid;
import org.example.personalfinanceapp.dto.BudgetRequestDTO;
import org.example.personalfinanceapp.dto.BudgetResponseDTO;
import org.example.personalfinanceapp.service.BudgetService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/budgets")
public class BudgetController {

    private final BudgetService budgetService;

    public BudgetController(BudgetService budgetService){

        this.budgetService = budgetService;
    }

    @PostMapping("/add")
    public ResponseEntity<BudgetResponseDTO> addBudget(
            @Valid @RequestBody BudgetRequestDTO budgetRequestDTO,
            Authentication authentication){

        String email = authentication.getName();
        BudgetResponseDTO responseFromAddBudget = budgetService.addBudget(email, budgetRequestDTO);

        return new ResponseEntity<>(responseFromAddBudget, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<BudgetResponseDTO>> getAllBudgets(
            Authentication authentication,
            @RequestParam Integer month,
            @RequestParam Integer year){

        String email = authentication.getName();

        List<BudgetResponseDTO> budgets = budgetService.getBudgets(email,month,year);

        return ResponseEntity.ok(budgets);
    }

    @PutMapping("/{budgetId}")
    public ResponseEntity<BudgetResponseDTO> updateBudget(
            @PathVariable Long budgetId,
            @Valid @RequestBody BudgetRequestDTO budgetRequestDTO,
            Authentication authentication){

        String email = authentication.getName();

        BudgetResponseDTO updatedBudget = budgetService.updateBudget(budgetId,email,budgetRequestDTO);

        return ResponseEntity.ok(updatedBudget);
    }

    @DeleteMapping("/{budgetId}")
    public ResponseEntity<Void> deleteBudget(
            @PathVariable Long budgetId,
            Authentication authentication
    ){
        String email = authentication.getName();

        budgetService.deleteBudget(budgetId,email);

        return ResponseEntity.noContent().build();
    }
}
