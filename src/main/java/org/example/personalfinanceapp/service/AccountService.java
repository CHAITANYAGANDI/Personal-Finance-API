package org.example.personalfinanceapp.service;

import org.example.personalfinanceapp.dto.AccountRequestDTO;
import org.example.personalfinanceapp.dto.AccountResponseDTO;
import org.example.personalfinanceapp.entity.Account;
import org.example.personalfinanceapp.entity.User;
import org.example.personalfinanceapp.repository.AccountRepository;
import org.example.personalfinanceapp.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.security.SecureRandom;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountService {

    private final UserRepository userRepository;
    private final AccountRepository accountRepository;
    private final SecureRandom secureRandom = new SecureRandom();

    public AccountService(UserRepository userRepository, AccountRepository accountRepository){

        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
    }

    private Long generateUniqueAccountNumber() {

        Long accountNumber;

        do {
            accountNumber = 1000000000L + secureRandom.nextLong(9000000000L);

        } while (accountRepository.existsByAccountNumber(accountNumber));

        return accountNumber;
    }

    private AccountResponseDTO convertToResponseDTO(Account account){

        return new AccountResponseDTO(account.getId(),
                account.getAccountNumber(),
                account.getBankName(),
                account.getAccountType(),
                account.getBalance(),
                account.getCreatedAt(),
                account.getUpdatedAt());
    }



    @Transactional
    public AccountResponseDTO addBankAccount(String email, AccountRequestDTO accountRequestDTO){

        User user = userRepository.findByEmail(email)
                .orElseThrow(()-> new RuntimeException("User not found"));

        boolean accountExists = accountRepository.existsByBankNameAndAccountType(accountRequestDTO.getBankName(),accountRequestDTO.getAccountType());

        if(accountExists){

            throw new RuntimeException("Account already exists");
        }

        Long accountNumber = generateUniqueAccountNumber();

        BigDecimal balance = BigDecimal.ZERO;

        Account account = new Account(
                accountNumber,
                accountRequestDTO.getBankName(),
                accountRequestDTO.getAccountType(),
                balance,
                user);

        Account savedAccount = accountRepository.save(account);

        return convertToResponseDTO(savedAccount);

    }

    @Transactional(readOnly = true)
    public List<AccountResponseDTO> getAllBankAccounts(String email){

        User user = userRepository.findByEmail(email).orElseThrow(()-> new RuntimeException("User not found"));

        return accountRepository
                .findAllById(user.getId())
                .stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }
}
