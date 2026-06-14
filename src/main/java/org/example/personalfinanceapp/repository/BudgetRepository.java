package org.example.personalfinanceapp.repository;

import org.example.personalfinanceapp.entity.Budget;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BudgetRepository extends JpaRepository<Budget,Long> {

    boolean existsByUserIdAndCategoryIdAndMonthAndYear(
            Long userId,
            Long categoryId,
            Integer month,
            Integer year
    );

    List<Budget> findByUserIdAndMonthAndYear(Long userId,Integer month, Integer year);

    Optional<Budget> findByUserIdAndId(Long userId, Long budgetId);
}
