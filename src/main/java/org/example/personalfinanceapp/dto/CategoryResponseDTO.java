package org.example.personalfinanceapp.dto;

import org.example.personalfinanceapp.enums.CategoryType;

public class CategoryResponseDTO {

    private Long id;
    private String categoryName;
    private CategoryType categoryType;

    public CategoryResponseDTO(Long id,
                              String categoryName,
                              CategoryType categoryType){

        this.id = id;
        this.categoryName = categoryName;
        this.categoryType = categoryType;
    }


    public Long getId() {
        return id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public CategoryType getCategoryType() {
        return categoryType;
    }
}
