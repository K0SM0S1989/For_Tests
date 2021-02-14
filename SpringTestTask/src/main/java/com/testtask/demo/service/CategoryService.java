package com.testtask.demo.service;

import com.testtask.demo.enums.CategoryNames;
import com.testtask.demo.models.Category;

public interface CategoryService {
    Category findByName(CategoryNames categoryNames);
}
