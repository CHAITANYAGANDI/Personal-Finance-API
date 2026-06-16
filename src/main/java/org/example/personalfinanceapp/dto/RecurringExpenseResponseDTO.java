package org.example.personalfinanceapp.dto;

import org.example.personalfinanceapp.enums.Frequency;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class RecurringExpenseResponseDTO {

    private Long id;

    private String description;

    private Frequency frequency;

    private LocalDate dueDate;

    private BigDecimal amount;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public RecurringExpenseResponseDTO(Long id,
                                       String description,
                                       Frequency frequency,
                                       LocalDate dueDate,
                                       BigDecimal amount,
                                       LocalDateTime createdAt,
                                       LocalDateTime updatedAt){

        this.id = id;
        this.description = description;
        this.frequency = frequency;
        this.dueDate = dueDate;
        this.amount = amount;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public Frequency getFrequency() {
        return frequency;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
