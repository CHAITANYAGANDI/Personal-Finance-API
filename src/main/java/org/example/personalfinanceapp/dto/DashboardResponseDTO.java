package org.example.personalfinanceapp.dto;

import java.math.BigDecimal;
import java.util.List;

public class DashboardResponseDTO {

    private BigDecimal netWorth;

    private BigDecimal income;

    private BigDecimal expenses;

    private BigDecimal netSaved;

    private List<CategorySpendingDTO> categorySpending;

    private List<BudgetProgressDTO> budgetProgress;

    private List<RecentTransactionsDTO> recentTransactions;

    public DashboardResponseDTO(BigDecimal netWorth,
                                BigDecimal income,
                                BigDecimal expenses,
                                BigDecimal netSaved,
                                List<CategorySpendingDTO> categorySpending,
                                List<RecentTransactionsDTO> recentTransactions,
                                List<BudgetProgressDTO> budgetProgress) {

        this.netWorth = netWorth;
        this.income = income;
        this.expenses = expenses;
        this.netSaved = netSaved;
        this.categorySpending = categorySpending;
        this.recentTransactions = recentTransactions;
        this.budgetProgress = budgetProgress;

    }

    public BigDecimal getNetWorth() {
        return netWorth;
    }

    public void setNetWorth(BigDecimal netWorth) {
        this.netWorth = netWorth;
    }

    public BigDecimal getIncome() {
        return income;
    }

    public void setIncome(BigDecimal income) {
        this.income = income;
    }

    public BigDecimal getExpenses() {
        return expenses;
    }

    public void setExpenses(BigDecimal expenses) {
        this.expenses = expenses;
    }

    public BigDecimal getNetSaved() {
        return netSaved;
    }

    public void setNetSaved(BigDecimal netSaved) {
        this.netSaved = netSaved;
    }

    public List<CategorySpendingDTO> getCategorySpending() {
        return categorySpending;
    }

    public void setCategorySpending(List<CategorySpendingDTO> categorySpending) {
        this.categorySpending = categorySpending;
    }

    public List<BudgetProgressDTO> getBudgetProgress() {
        return budgetProgress;
    }

    public void setBudgetProgress(List<BudgetProgressDTO> budgetProgress) {
        this.budgetProgress = budgetProgress;
    }

    public List<RecentTransactionsDTO> getRecentTransactions() {
        return recentTransactions;
    }

    public void setRecentTransactions(List<RecentTransactionsDTO> recentTransactions) {
        this.recentTransactions = recentTransactions;
    }
}
