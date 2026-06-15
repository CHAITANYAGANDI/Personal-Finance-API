package org.example.personalfinanceapp.repository;

import org.example.personalfinanceapp.entity.Account;
import org.example.personalfinanceapp.enums.AccountType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {

    boolean existsByAccountNumber(Long accountNumber);

    boolean existsByUserIdAndBankNameIgnoreCaseAndAccountType(Long userId,
                                                              String bankName,
                                                              AccountType accountType);

    List<Account> findByUserId(Long userId);

    Optional<Account> findByUserIdAndId(Long userId, Long id);

}
