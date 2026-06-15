package org.example.personalfinanceapp.service;

import org.example.personalfinanceapp.dto.TransactionRequestDTO;
import org.example.personalfinanceapp.dto.TransactionResponseDTO;
import org.example.personalfinanceapp.entity.Account;
import org.example.personalfinanceapp.entity.Category;
import org.example.personalfinanceapp.entity.Transaction;
import org.example.personalfinanceapp.entity.User;
import org.example.personalfinanceapp.enums.CategoryType;
import org.example.personalfinanceapp.enums.TransactionType;
import org.example.personalfinanceapp.repository.AccountRepository;
import org.example.personalfinanceapp.repository.CategoryRepository;
import org.example.personalfinanceapp.repository.TransactionRepository;
import org.example.personalfinanceapp.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final AccountRepository accountRepository;

    public TransactionService(TransactionRepository transactionRepository,
                              UserRepository userRepository,
                              AccountRepository accountRepository,
                              CategoryRepository categoryRepository){

        this.transactionRepository = transactionRepository;
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
        this.categoryRepository = categoryRepository;
    }

    private TransactionResponseDTO convertToResponseDTO(Transaction transaction) {

        Long accountId = null;
        String accountName = null;

        Long categoryId = null;
        String categoryName = null;

        Long fromAccountId = null;
        String fromAccountName = null;

        Long toAccountId = null;
        String toAccountName = null;

        if (transaction.getAccount() != null) {
            accountId = transaction.getAccount().getId();
            accountName = transaction.getAccount().getBankName();
        }

        if (transaction.getCategory() != null) {
            categoryId = transaction.getCategory().getId();
            categoryName = transaction.getCategory().getCategoryName();
        }

        if (transaction.getFromAccount() != null) {
            fromAccountId = transaction.getFromAccount().getId();
            fromAccountName = transaction.getFromAccount().getBankName();
        }

        if (transaction.getToAccount() != null) {
            toAccountId = transaction.getToAccount().getId();
            toAccountName = transaction.getToAccount().getBankName();
        }

        return new TransactionResponseDTO(
                transaction.getId(),
                transaction.getTransactionType(),
                accountId,
                accountName,
                categoryId,
                categoryName,
                fromAccountId,
                fromAccountName,
                toAccountId,
                toAccountName,
                transaction.getAmount(),
                transaction.getTransactionDate(),
                transaction.getDescription(),
                transaction.getCreatedAt(),
                transaction.getUpdatedAt()
        );
    }

    private Transaction handleDebit(TransactionRequestDTO transactionRequestDTO, User user){

        if (transactionRequestDTO.getAccountId() == null) {
            throw new RuntimeException("Account is required for debit transaction");
        }

        if (transactionRequestDTO.getCategoryId() == null) {
            throw new RuntimeException("Category is required for debit transaction");
        }

        Account account = accountRepository.findByUserIdAndId(user.getId(), transactionRequestDTO.getAccountId())
                .orElseThrow(() -> new RuntimeException("Account not found"));

        Category category = categoryRepository
                .findByIdAndCategoryTypeAndActiveTrue(transactionRequestDTO.getCategoryId(), CategoryType.EXPENSE)
                .orElseThrow(() -> new RuntimeException("Expense category not found"));


        if(account.getBalance().compareTo(transactionRequestDTO.getAmount())<0){

            throw new RuntimeException("Insufficient funds");
        }


        account.setBalance(account.getBalance().subtract(transactionRequestDTO.getAmount()));

        Transaction transaction = new Transaction(

                transactionRequestDTO.getTransactionDate(),
                transactionRequestDTO.getTransactionType(),
                transactionRequestDTO.getAmount(),
                transactionRequestDTO.getDescription()

        );

        transaction.setUser(user);
        transaction.setAccount(account);
        transaction.setCategory(category);

        return transactionRepository.save(transaction);

    }

    private Transaction handleCredit(TransactionRequestDTO transactionRequestDTO,User user){

        if(transactionRequestDTO.getAccountId() == null){

            throw new RuntimeException("Account is required for the credit transaction");

        }

        if(transactionRequestDTO.getCategoryId() == null){

            throw new RuntimeException("Category is required for the credit transaction");
        }

        Account account = accountRepository.findByUserIdAndId(user.getId(), transactionRequestDTO.getAccountId())
                .orElseThrow(()-> new RuntimeException("Account not found"));

        Category category = categoryRepository
                .findByIdAndCategoryTypeAndActiveTrue(transactionRequestDTO.getCategoryId(),CategoryType.INCOME)
                .orElseThrow(()-> new RuntimeException("Income Category not found"));


        account.setBalance(account.getBalance().add(transactionRequestDTO.getAmount()));

        Transaction creditTransaction = new Transaction(
                transactionRequestDTO.getTransactionDate(),
                transactionRequestDTO.getTransactionType(),
                transactionRequestDTO.getAmount(),
                transactionRequestDTO.getDescription()
        );

        creditTransaction.setAccount(account);
        creditTransaction.setUser(user);
        creditTransaction.setCategory(category);

        return transactionRepository.save(creditTransaction);
    }

    private Transaction handleTransfer(TransactionRequestDTO transactionRequestDTO, User user){

        if(transactionRequestDTO.getFromAccountId() == null){

            throw new RuntimeException("From Account is required to make a transfer");
        }

        if(transactionRequestDTO.getToAccountId() == null){

            throw new RuntimeException("To Account is required to make a transfer");
        }

        Account fromAccount = accountRepository.findByUserIdAndId(user.getId(), transactionRequestDTO.getFromAccountId())
                .orElseThrow(()-> new RuntimeException("account not found"));

        Account toAccount = accountRepository.findByUserIdAndId(user.getId(), transactionRequestDTO.getToAccountId())
                .orElseThrow(()-> new RuntimeException("account not found"));

        if(transactionRequestDTO.getFromAccountId().equals(transactionRequestDTO.getToAccountId())){

            throw new RuntimeException("From Account and To Account should be different to make a transfer");
        }

        if(fromAccount.getBalance().compareTo(transactionRequestDTO.getAmount()) < 0){

            throw new RuntimeException("Insufficient funds to make a transfer");
        }

        fromAccount.setBalance(fromAccount.getBalance().subtract(transactionRequestDTO.getAmount()));
        toAccount.setBalance(toAccount.getBalance().add(transactionRequestDTO.getAmount()));

        Transaction transferTransaction = new Transaction(

                transactionRequestDTO.getTransactionDate(),
                transactionRequestDTO.getTransactionType(),
                transactionRequestDTO.getAmount(),
                transactionRequestDTO.getDescription()
        );

        transferTransaction.setFromAccount(fromAccount);
        transferTransaction.setToAccount(toAccount);
        transferTransaction.setUser(user);

        return transactionRepository.save(transferTransaction);
    }

    @Transactional
    public TransactionResponseDTO addTransaction(String email, TransactionRequestDTO transactionRequestDTO){

        User user = userRepository.findByEmail(email).orElseThrow(()-> new RuntimeException("User not found"));

        TransactionType transactionType = transactionRequestDTO.getTransactionType();

        if(transactionType == null){

            throw new RuntimeException("Transaction type is required");
        }


        return switch (transactionType) {
            case DEBIT -> {

                Transaction savedTransaction = handleDebit(transactionRequestDTO, user);
                yield convertToResponseDTO(savedTransaction);
            }
            case CREDIT -> {

                Transaction savedTransaction = handleCredit(transactionRequestDTO, user);
                yield convertToResponseDTO(savedTransaction);
            }
            case TRANSFER -> {

                Transaction savedTransaction = handleTransfer(transactionRequestDTO, user);
                yield convertToResponseDTO(savedTransaction);
            }

            default -> throw new RuntimeException("Invalid transaction type");
        };


    }
}
