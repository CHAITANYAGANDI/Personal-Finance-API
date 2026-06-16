package org.example.personalfinanceapp.service;

import org.example.personalfinanceapp.dto.BudgetRequestDTO;
import org.example.personalfinanceapp.dto.BudgetResponseDTO;
import org.example.personalfinanceapp.entity.Budget;
import org.example.personalfinanceapp.entity.Category;
import org.example.personalfinanceapp.entity.User;
import org.example.personalfinanceapp.enums.CategoryType;
import org.example.personalfinanceapp.repository.BudgetRepository;
import org.example.personalfinanceapp.repository.CategoryRepository;
import org.example.personalfinanceapp.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BudgetService {

    private final BudgetRepository budgetRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    public BudgetService(BudgetRepository budgetRepository, CategoryRepository categoryRepository, UserRepository userRepository){

        this.budgetRepository = budgetRepository;
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
    }

    private BudgetResponseDTO convertToResponseDTO(Budget budget){

        return new BudgetResponseDTO(budget.getId(),
                budget.getCategory().getId(),
                budget.getCategory().getCategoryName(),
                budget.getCategory().getCategoryType(),
                budget.getMonthlyLimit(),
                budget.getMonth(),
                budget.getYear(),
                budget.getCreatedAt(),
                budget.getUpdatedAt());
    }


    @Transactional
    public BudgetResponseDTO addBudget(String email, BudgetRequestDTO budgetRequestDTO){


        User user = userRepository.findByEmail(email).orElseThrow(()-> new RuntimeException("User not found"));

        Category category = categoryRepository
                .findByIdAndCategoryTypeAndActiveTrue(budgetRequestDTO.getCategoryId(),CategoryType.EXPENSE)
                .orElseThrow(()-> new RuntimeException("category not found"));

        boolean budgetAlreadyExists = budgetRepository.
                existsByUserIdAndCategoryIdAndMonthAndYear(
                        user.getId(),
                        category.getId(),
                        budgetRequestDTO.getMonth(),
                        budgetRequestDTO.getYear()
                );

        if(budgetAlreadyExists){

            throw new RuntimeException("budget already exists");
        }


        Budget budget = new Budget(
                budgetRequestDTO.getMonthlyLimit(),
                budgetRequestDTO.getMonth(),
                budgetRequestDTO.getYear(),
                user,
                category);

        Budget savedBudget = budgetRepository.save(budget);


        return convertToResponseDTO(savedBudget);

    }

    @Transactional(readOnly = true)
    public List<BudgetResponseDTO> getBudgets(String email, Integer month, Integer year){

        User user = userRepository.findByEmail(email).orElseThrow(()-> new RuntimeException("User not found"));

        return budgetRepository.findByUserIdAndMonthAndYear(user.getId(),month,year)
                .stream().map(this::convertToResponseDTO)
                .collect(Collectors.toList());

    }

    @Transactional
    public BudgetResponseDTO updateBudget(Long budgetId, String email, BudgetRequestDTO budgetRequestDTO){

        User user = userRepository
                .findByEmail(email)
                .orElseThrow(()-> new RuntimeException("user not found"));

        Budget budget = budgetRepository
                .findByUserIdAndId(user.getId(), budgetId)
                .orElseThrow(()-> new RuntimeException("Budget not found"));

        Category category = categoryRepository
                .findByIdAndCategoryTypeAndActiveTrue(budgetRequestDTO.getCategoryId(),CategoryType.EXPENSE)
                .orElseThrow(()-> new RuntimeException("category not found"));



        Optional<Budget> existingBudget = budgetRepository.findByUserIdAndCategoryIdAndMonthAndYear(
                user.getId(),
                category.getId(),
                budgetRequestDTO.getMonth(),
                budgetRequestDTO.getYear()
        );

        if (existingBudget.isPresent() && !existingBudget.get().getId().equals(budget.getId())) {
            throw new RuntimeException("Budget already exists for this category, month, and year");
        }

        budget.setCategory(category);
        budget.setMonthlyLimit(budgetRequestDTO.getMonthlyLimit());
        budget.setMonth(budgetRequestDTO.getMonth());
        budget.setYear(budgetRequestDTO.getYear());

        Budget updatedBudget = budgetRepository.save(budget);

        return convertToResponseDTO(updatedBudget);

    }


    @Transactional
    public void deleteBudget(Long budgetId, String email){

        User user = userRepository.findByEmail(email)
                .orElseThrow(()-> new RuntimeException("User not found"));

        Budget budget = budgetRepository.findByUserIdAndId(user.getId(),budgetId)
                .orElseThrow(()-> new RuntimeException("Budget not found"));


        budgetRepository.delete(budget);

    }

}
