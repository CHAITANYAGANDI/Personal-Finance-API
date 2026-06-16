package org.example.personalfinanceapp.repository;

import org.example.personalfinanceapp.entity.RecurringExpense;
import org.example.personalfinanceapp.enums.Frequency;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface RecurringExpenseRepository extends JpaRepository<RecurringExpense, Long> {

    boolean existsByUserIdAndDescriptionAndAmountAndFrequencyAndDueDate(Long userId,
                                                                        String description,
                                                                        BigDecimal amount,
                                                                        Frequency frequency,
                                                                        LocalDate dueDate);

    List<RecurringExpense> findByUserId(Long userId);

    Optional<RecurringExpense> findByUserIdAndId(Long userId, Long id);


}
