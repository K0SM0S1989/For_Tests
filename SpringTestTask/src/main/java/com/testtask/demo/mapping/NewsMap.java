package com.testtask.demo.mapping;

import com.testtask.demo.dto.NewsDTO;
import com.testtask.demo.models.Category;
import com.testtask.demo.models.News;

import java.util.Date;

public class NewsMap {
    public static News map(NewsDTO newsDTO, Category category) {
        News news = new News();
        news.setTitle(newsDTO.getTitle());
        news.setPublicationDate(new Date());
        news.setText(newsDTO.getText());
        news.setCategory(category);
        return news;
    }
}
