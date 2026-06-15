package org.example.personalfinanceapp.config;

import org.example.personalfinanceapp.entity.Category;
import org.example.personalfinanceapp.enums.CategoryType;
import org.example.personalfinanceapp.repository.CategoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class DataSeeder implements CommandLineRunner {

    private final CategoryRepository categoryRepository;

    public DataSeeder(CategoryRepository categoryRepository){

        this.categoryRepository = categoryRepository;
    }

    @Transactional
    @Override
    public void run(String... args){

        seedIncomeCategories();
        seedExpenseCategories();
    }

    private void seedIncomeCategories(){

        createCategoryIfNotExists("Salary", CategoryType.INCOME);
        createCategoryIfNotExists("Refund", CategoryType.INCOME);
        createCategoryIfNotExists("Gift", CategoryType.INCOME);
        createCategoryIfNotExists("Freelance", CategoryType.INCOME);
        createCategoryIfNotExists("Bonus", CategoryType.INCOME);
        createCategoryIfNotExists("Cash Deposit", CategoryType.INCOME);
        createCategoryIfNotExists("Other Income", CategoryType.INCOME);

    }

    private void seedExpenseCategories(){
        createCategoryIfNotExists("Rent", CategoryType.EXPENSE);
        createCategoryIfNotExists("Groceries", CategoryType.EXPENSE);
        createCategoryIfNotExists("Dining", CategoryType.EXPENSE);
        createCategoryIfNotExists("Transit", CategoryType.EXPENSE);
        createCategoryIfNotExists("Shopping", CategoryType.EXPENSE);
        createCategoryIfNotExists("Subscriptions", CategoryType.EXPENSE);
        createCategoryIfNotExists("Gym", CategoryType.EXPENSE);
        createCategoryIfNotExists("Health", CategoryType.EXPENSE);
        createCategoryIfNotExists("Utilities", CategoryType.EXPENSE);
        createCategoryIfNotExists("Other Expense", CategoryType.EXPENSE);
    }

    private void createCategoryIfNotExists(String categoryName, CategoryType categoryType){

        boolean categoryAlreadyExists = categoryRepository.
                existsByCategoryNameIgnoreCaseAndCategoryType(
                        categoryName,categoryType);

        if(!categoryAlreadyExists){

            Category category = new Category(categoryName,categoryType);

            categoryRepository.save(category);
        }

    }
}
