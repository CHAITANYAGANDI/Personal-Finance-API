package org.example.personalfinanceapp.dto;

import java.math.BigDecimal;

public class CategorySpendingDTO {

    private Long categoryId;
    private String categoryName;
    private BigDecimal amount;
    private BigDecimal percentage;


    public CategorySpendingDTO(){}

    public CategorySpendingDTO(Long categoryId,
                               String categoryName,
                               BigDecimal amount,
                               BigDecimal percentage){

        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.amount = amount;
        this.percentage  = percentage;

    }


    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getPercentage() {
        return percentage;
    }

    public void setPercentage(BigDecimal percentage) {
        this.percentage = percentage;
    }
}
