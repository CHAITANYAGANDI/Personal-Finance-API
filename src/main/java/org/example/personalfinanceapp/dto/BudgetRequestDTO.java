package org.example.personalfinanceapp.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public class BudgetRequestDTO {

    @NotNull
    @Positive
    private BigDecimal monthlyLimit;

    @NotNull
    @Min(1)
    @Max(12)
    private Integer month;

    @NotNull
    @Min(2000)
    private Integer year;

    @NotNull
    private Long categoryId;


    public BudgetRequestDTO(){}

    public BudgetRequestDTO(Long categoryId,
                            BigDecimal monthlyLimit,
                            Integer month,
                            Integer year){

        this.categoryId = categoryId;
        this.monthlyLimit = monthlyLimit;
        this.month = month;
        this.year = year;
    }

    public BigDecimal getMonthlyLimit() {
        return monthlyLimit;
    }

    public void setMonthlyLimit(BigDecimal monthlyLimit) {
        this.monthlyLimit = monthlyLimit;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
}
