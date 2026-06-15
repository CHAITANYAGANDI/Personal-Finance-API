package org.example.personalfinanceapp.repository;

import org.example.personalfinanceapp.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {


}
