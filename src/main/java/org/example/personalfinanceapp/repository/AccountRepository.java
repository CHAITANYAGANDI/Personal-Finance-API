package org.example.personalfinanceapp.repository;

import org.example.personalfinanceapp.entity.Account;
import org.example.personalfinanceapp.enums.AccountType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Long> {

    boolean existsByAccountNumber(Long accountNumber);

    boolean existsByBankNameAndAccountType(String bankName, AccountType accountType);

    List<Account> findAllById(Long id);

}
