package org.example.personalfinanceapp.dto;

import java.math.BigDecimal;

public class BudgetProgressDTO {

    private Long budgetId;
    private Long categoryId;
    private String categoryName;
    private BigDecimal monthlyLimit;
    private BigDecimal spentAmount;
    private BigDecimal remainingAmount;
    private BigDecimal progressPercentage;
    private String status;

    public BudgetProgressDTO(Long budgetId,
                             Long categoryId,
                             String categoryName,
                             BigDecimal monthlyLimit,
                             BigDecimal spentAmount,
                             BigDecimal remainingAmount,
                             BigDecimal progressPercentage,
                             String status) {


        this.budgetId = budgetId;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.monthlyLimit = monthlyLimit;
        this.spentAmount = spentAmount;
        this.remainingAmount = remainingAmount;
        this.progressPercentage = progressPercentage;
        this.status = status;

    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Long getBudgetId() {
        return budgetId;
    }

    public void setBudgetId(Long budgetId) {
        this.budgetId = budgetId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public BigDecimal getMonthlyLimit() {
        return monthlyLimit;
    }

    public void setMonthlyLimit(BigDecimal monthlyLimit) {
        this.monthlyLimit = monthlyLimit;
    }

    public BigDecimal getSpentAmount() {
        return spentAmount;
    }

    public void setSpentAmount(BigDecimal spentAmount) {
        this.spentAmount = spentAmount;
    }

    public BigDecimal getRemainingAmount() {
        return remainingAmount;
    }

    public void setRemainingAmount(BigDecimal remainingAmount) {
        this.remainingAmount = remainingAmount;
    }

    public BigDecimal getProgressPercentage() {
        return progressPercentage;
    }

    public void setProgressPercentage(BigDecimal progressPercentage) {
        this.progressPercentage = progressPercentage;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
