package org.example.personalfinanceapp.service;

import org.example.personalfinanceapp.dto.RecurringExpenseRequestDTO;
import org.example.personalfinanceapp.dto.RecurringExpenseResponseDTO;
import org.example.personalfinanceapp.entity.RecurringExpense;
import org.example.personalfinanceapp.entity.User;
import org.example.personalfinanceapp.repository.RecurringExpenseRepository;
import org.example.personalfinanceapp.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecurringExpenseService {

    private final RecurringExpenseRepository recurringExpenseRepository;
    private final UserRepository userRepository;

    public RecurringExpenseService(RecurringExpenseRepository recurringExpenseRepository, UserRepository userRepository){

        this.recurringExpenseRepository = recurringExpenseRepository;
        this.userRepository = userRepository;
    }

    private RecurringExpenseResponseDTO convertToResponseDTO(RecurringExpense recurringExpense){

        return new RecurringExpenseResponseDTO(recurringExpense.getId(),
                recurringExpense.getDescription(),
                recurringExpense.getFrequency(),
                recurringExpense.getDueDate(),
                recurringExpense.getAmount(),
                recurringExpense.getCreatedAt(),
                recurringExpense.getUpdatedAt());
    }

    @Transactional
    public RecurringExpenseResponseDTO addRecurringExpense(String email, RecurringExpenseRequestDTO recurringExpenseRequestDTO){

        User user = userRepository.findByEmail(email).orElseThrow(()-> new RuntimeException("User not found"));

        boolean recurringExpenseExists = recurringExpenseRepository.existsByUserIdAndDescriptionAndAmountAndFrequencyAndDueDate(
                user.getId(),
                recurringExpenseRequestDTO.getDescription(),
                recurringExpenseRequestDTO.getAmount(),
                recurringExpenseRequestDTO.getFrequency(),
                recurringExpenseRequestDTO.getDueDate()
        );

        if(recurringExpenseExists){

            throw new RuntimeException("Recurring Expense already exists");

        }

        RecurringExpense newRecurringExpense = new RecurringExpense(
                recurringExpenseRequestDTO.getDescription(),
                recurringExpenseRequestDTO.getAmount(),
                recurringExpenseRequestDTO.getFrequency(),
                recurringExpenseRequestDTO.getDueDate()
        );

        newRecurringExpense.setUser(user);

        RecurringExpense recurringExpense = recurringExpenseRepository.save(newRecurringExpense);

        return convertToResponseDTO(recurringExpense);


    }

    @Transactional(readOnly = true)
    public List<RecurringExpenseResponseDTO> getAllRecurringExpenses(String email){

        User user = userRepository.findByEmail(email).orElseThrow(()-> new RuntimeException("User not found"));

        return recurringExpenseRepository.findByUserId(user.getId())
                .stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public RecurringExpenseResponseDTO updateRecurringExpense
            (String email,
             Long id,
             RecurringExpenseRequestDTO recurringExpenseRequestDTO){

        User user = userRepository.findByEmail(email).orElseThrow(()-> new RuntimeException("User not found"));

        RecurringExpense recurringExpense = recurringExpenseRepository.findByUserIdAndId(user.getId(), id)
                .orElseThrow(()-> new RuntimeException("Recurring Expense not found"));

        recurringExpense.setUser(user);
        recurringExpense.setDescription(recurringExpenseRequestDTO.getDescription());
        recurringExpense.setAmount(recurringExpenseRequestDTO.getAmount());
        recurringExpense.setDueDate(recurringExpenseRequestDTO.getDueDate());
        recurringExpense.setFrequency(recurringExpenseRequestDTO.getFrequency());


        RecurringExpense updatedRecurringExpense = recurringExpenseRepository.save(recurringExpense);

        return convertToResponseDTO(updatedRecurringExpense);


    }


    @Transactional
    public void deleteRecurringExpense(String email, Long id) {

        User user = userRepository.findByEmail(email).orElseThrow(()-> new RuntimeException("User not found"));

        RecurringExpense foundExpense = recurringExpenseRepository.findByUserIdAndId(user.getId(), id)
                .orElseThrow(()-> new RuntimeException("Recurring Expense not found"));

        recurringExpenseRepository.delete(foundExpense);
    }
}
