package org.example.personalfinanceapp.dto;

import org.example.personalfinanceapp.enums.CategoryType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class BudgetResponseDTO {

    private Long id;
    private Long categoryId;
    private String categoryName;
    private CategoryType categoryType;
    private BigDecimal monthlyLimit;
    private Integer month;
    private Integer year;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public BudgetResponseDTO(Long id,
                             Long categoryId,
                             String categoryName,
                             CategoryType categoryType,
                             BigDecimal monthlyLimit,
                             Integer month,
                             Integer year,
                             LocalDateTime createdAt,
                             LocalDateTime updatedAt) {
        this.id = id;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.categoryType = categoryType;
        this.monthlyLimit = monthlyLimit;
        this.month = month;
        this.year = year;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public CategoryType getCategoryType() {
        return categoryType;
    }

    public BigDecimal getMonthlyLimit() {
        return monthlyLimit;
    }

    public Integer getMonth() {
        return month;
    }

    public Integer getYear() {
        return year;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
