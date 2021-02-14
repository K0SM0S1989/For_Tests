package com.testtask.demo.repo;

import com.testtask.demo.enums.CategoryNames;
import com.testtask.demo.models.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepo extends CrudRepository<Category, Long> {
    Category findCategoryByName(CategoryNames categoryNames);
}
