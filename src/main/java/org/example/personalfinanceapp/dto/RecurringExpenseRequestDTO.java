package org.example.personalfinanceapp.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.example.personalfinanceapp.enums.Frequency;

import java.math.BigDecimal;
import java.time.LocalDate;

public class RecurringExpenseRequestDTO {

    @NotNull
    @Size(max = 250)
    private String description;

    @NotNull
    private BigDecimal amount;

    @NotNull
    private Frequency frequency;

    @NotNull
    private LocalDate dueDate;

    public RecurringExpenseRequestDTO(){}

    public RecurringExpenseRequestDTO(String description,
                                      BigDecimal amount,
                                      Frequency frequency,
                                      LocalDate dueDate){

        this.description = description;
        this.amount = amount;
        this.frequency = frequency;
        this.dueDate = dueDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Frequency getFrequency() {
        return frequency;
    }

    public void setFrequency(Frequency frequency) {
        this.frequency = frequency;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }
}
