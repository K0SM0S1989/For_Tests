package com.testtask.demo.service.implementation;

import com.testtask.demo.enums.CategoryNames;
import com.testtask.demo.models.Category;
import com.testtask.demo.repo.CategoryRepo;
import com.testtask.demo.service.CategoryService;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepo categoryRepo;

    public CategoryServiceImpl(CategoryRepo categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    @Override
    public Category findByName(CategoryNames categoryNames) {
        return categoryRepo.findCategoryByName(categoryNames);
    }
}
