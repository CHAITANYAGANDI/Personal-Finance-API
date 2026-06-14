package org.example.personalfinanceapp.repository;

import org.example.personalfinanceapp.entity.Category;
import org.example.personalfinanceapp.enums.CategoryType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {

 List<Category> findByCategoryTypeAndActiveTrueOrderByCategoryNameAsc(CategoryType categoryType);

 boolean existsByCategoryNameIgnoreCaseAndCategoryType(String categoryName, CategoryType categoryType);

}
