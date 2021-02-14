package com.testtask.demo.repo;

import com.testtask.demo.models.Category;
import com.testtask.demo.models.News;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewsRepo extends CrudRepository<News, Long> {
    List<News> findAll();

    List<News> findAllByCategory(Category category);

}
