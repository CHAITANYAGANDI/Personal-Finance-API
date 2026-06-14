package org.example.personalfinanceapp.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.example.personalfinanceapp.enums.CategoryType;

public class CategoryRequestDTO {

    @NotBlank
    @Size(min = 2, max = 100)
    private String categoryName;

    @NotNull
    private CategoryType categoryType;

    public CategoryRequestDTO(){}

    public CategoryRequestDTO(String categoryName, CategoryType categoryType){

        this.categoryName = categoryName;
        this.categoryType = categoryType;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public CategoryType getCategoryType() {
        return categoryType;
    }

    public void setCategoryType(CategoryType categoryType) {
        this.categoryType = categoryType;
    }
}

