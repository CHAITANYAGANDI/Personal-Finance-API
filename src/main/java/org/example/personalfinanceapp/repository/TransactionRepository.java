package org.example.personalfinanceapp.repository;

import org.example.personalfinanceapp.entity.Transaction;
import org.example.personalfinanceapp.enums.TransactionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findByUserIdOrderByTransactionDateDescCreatedAtDesc(Long userId);

    List<Transaction> findByUserIdAndTransactionTypeOrderByTransactionDateDescCreatedAtDesc(Long userId, TransactionType transactionType);

    @Query("""
            SELECT COALESCE(SUM(t.amount),0)
            FROM Transaction t
            WHERE t.user.id = :userId
            AND t.transactionType = :transactionType
            AND t.transactionDate BETWEEN :startDate AND :endDate
            """)
    BigDecimal getMonthlyTotalByTransactionType(@Param("userId") Long userId,
                                                @Param("transactionType") TransactionType transactionType,
                                                @Param("startDate") LocalDate startDate,
                                                @Param("endDate") LocalDate endDate);


    @Query("""
            SELECT t.category.id, t.category.name, SUM(t.amount)
            FROM Transaction t
            WHERE t.user.id = :userId
            AND t.transactionType = :transactionType
            AND t.transactionDate BETWEEN :startDate AND :endDate
            AND t.category IS NOT NULL
            GROUP BY t.category.id, t.category.categoryName
            ORDER BY SUM(t.amount) DESC
            """)
    List<Object[]> getSpendingByCategory(
            @Param("userId") Long userId,
            @Param("transactionType") TransactionType transactionType,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate);

}