package org.example.personalfinanceapp.service;

import org.example.personalfinanceapp.dto.BudgetProgressDTO;
import org.example.personalfinanceapp.dto.CategorySpendingDTO;
import org.example.personalfinanceapp.dto.DashboardResponseDTO;
import org.example.personalfinanceapp.entity.Budget;
import org.example.personalfinanceapp.entity.User;
import org.example.personalfinanceapp.enums.TransactionType;
import org.example.personalfinanceapp.repository.AccountRepository;
import org.example.personalfinanceapp.repository.BudgetRepository;
import org.example.personalfinanceapp.repository.TransactionRepository;
import org.example.personalfinanceapp.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DashBoardService {

    private final UserRepository userRepository;
    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;
    private final BudgetRepository budgetRepository;


    public DashBoardService(UserRepository userRepository,
                            AccountRepository accountRepository,
                            TransactionRepository transactionRepository,
                            BudgetRepository budgetRepository){

        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
        this.budgetRepository = budgetRepository;
    }

    private List<CategorySpendingDTO> getSpendingByCategory(Long userId,
                                                            LocalDate startDate,
                                                            LocalDate endDate,
                                                            BigDecimal totalExpenses){


        List<Object[]> rows = transactionRepository.getSpendingByCategory(userId,
                TransactionType.DEBIT,
                startDate,
                endDate
        );

        List<CategorySpendingDTO> spendingList = new ArrayList<>();

        for(Object[] row: rows){

            Long categoryId = (Long) row[0];

            String categoryName = (String) row[1];

            BigDecimal amount = (BigDecimal) row[2];

            BigDecimal percentage = BigDecimal.ZERO;

            if(totalExpenses.compareTo(BigDecimal.ZERO)>0){

                percentage = amount
                        .multiply(BigDecimal.valueOf(100))
                        .divide(totalExpenses,2, RoundingMode.HALF_UP);
            }

            CategorySpendingDTO dto = new CategorySpendingDTO(
                    categoryId,
                    categoryName,
                    amount,
                    percentage
            );

            spendingList.add(dto);
        }

        return spendingList;
    }

    private List<BudgetProgressDTO> getBudgetProgressByCategory(Long userId,
                                                                LocalDate startDate,
                                                                LocalDate endDate,
                                                                Integer month,
                                                                Integer year,
                                                                BigDecimal totalExpenses){

        List<Budget> budgets = budgetRepository.findByUserIdAndMonthAndYear(
                userId,
                month,
                year
        );

        List<Object[]> rows = transactionRepository.getSpendingByCategory(
                userId,
                TransactionType.DEBIT,
                startDate,
                endDate);

        Map<Long, BigDecimal> spendingMap = new HashMap<>();

        for(Object[] row: rows){

            Long categoryId = (Long) row[0];

            BigDecimal spentAmount = (BigDecimal) row[1];

            spendingMap.put(categoryId,spentAmount);
        }

        List<BudgetProgressDTO> budgetProgressList = new ArrayList<>();

        for(Budget budget: budgets){

            Long categoryId = budget.getCategory().getId();

            BigDecimal spentAmount = spendingMap.getOrDefault(categoryId,BigDecimal.ZERO);

            BigDecimal monthlyLimit = budget.getMonthlyLimit();

            BigDecimal remainingAmount = monthlyLimit.subtract(spentAmount);

            BigDecimal progressPercentage = BigDecimal.ZERO;

            if(monthlyLimit.compareTo(BigDecimal.ZERO)>0){

                progressPercentage = spentAmount
                        .multiply(BigDecimal.valueOf(100))
                        .divide(monthlyLimit,2,RoundingMode.HALF_UP);
            }

            String status;

            if (progressPercentage.compareTo(BigDecimal.valueOf(100))>0){

                status = "EXCEEDED";
            } else if(progressPercentage.compareTo(BigDecimal.valueOf(100))==0){

                status = "LIMIT_REACHED";
            } else{

                status = "ON_TRACK";
            }

            BudgetProgressDTO dto = new BudgetProgressDTO(
                    budget.getId(),
                    categoryId,
                    budget.getCategory().getCategoryName(),
                    monthlyLimit,
                    spentAmount,
                    remainingAmount,
                    progressPercentage,
                    status
            );

            budgetProgressList.add(dto);
        }

        return budgetProgressList;

    }


    @Transactional(readOnly = true)
    public DashboardResponseDTO getDashboard(String email,
                                             Integer month,
                                             Integer year){

        User user = userRepository.findByEmail(email).orElseThrow(()-> new RuntimeException("User not found"));

        LocalDate today = LocalDate.now();

        int selectedMonth = month!= null ? month : today.getMonthValue();

        int selectedYear = year!= null ? year : today.getYear();

        YearMonth yearMonth = YearMonth.of(selectedYear,selectedMonth);

        LocalDate startDate = yearMonth.atDay(1);
        LocalDate endDate = yearMonth.atEndOfMonth();

        BigDecimal netWorth = accountRepository.getNetWorthByUserId(user.getId());

        BigDecimal monthlyIncome = transactionRepository.getMonthlyTotalByTransactionType(user.getId(),
                TransactionType.CREDIT, startDate, endDate);

        BigDecimal monthlyExpenses = transactionRepository.getMonthlyTotalByTransactionType(user.getId(),
                TransactionType.DEBIT, startDate, endDate);

        BigDecimal netSaved = monthlyIncome.subtract(monthlyExpenses);

        List<CategorySpendingDTO> spendingByCategory = getSpendingByCategory(

                user.getId(),
                startDate,
                endDate,
                monthlyExpenses
        );


        List<BudgetProgressDTO> budgetProgressByCategory = getBudgetProgressByCategory(
                user.getId(),
                startDate,
                endDate,
                selectedMonth,
                selectedYear,
                monthlyExpenses
        );

        return new DashboardResponseDTO(netWorth,
                monthlyIncome,
                monthlyExpenses,
                netSaved,
                spendingByCategory,
                List.of(),
                budgetProgressByCategory);

    }
}
