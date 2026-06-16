package org.example.personalfinanceapp.repository;

import org.example.personalfinanceapp.entity.Transaction;
import org.example.personalfinanceapp.enums.TransactionType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findByUserIdOrderByTransactionDateDescCreatedAtDesc(Long userId);

    List<Transaction> findByUserIdAndTransactionTypeOrderByTransactionDateDescCreatedAtDesc(Long userId, TransactionType transactionType);

}
