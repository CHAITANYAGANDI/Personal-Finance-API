package org.example.personalfinanceapp.service;

import org.example.personalfinanceapp.dto.CategoryResponseDTO;
import org.example.personalfinanceapp.entity.Category;
import org.example.personalfinanceapp.enums.CategoryType;
import org.example.personalfinanceapp.repository.CategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository){

        this.categoryRepository = categoryRepository;
    }

    private CategoryResponseDTO convertToResponseDTO(Category category){

        return new CategoryResponseDTO(

                category.getId(),
                category.getCategoryName(),
                category.getCategoryType()
        );
    }


    @Transactional(readOnly = true)
    public List<CategoryResponseDTO> getCategoriesByType(CategoryType categoryType){

        return categoryRepository
                .findByCategoryTypeAndActiveTrueOrderByCategoryNameAsc(categoryType)
                .stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }
}
