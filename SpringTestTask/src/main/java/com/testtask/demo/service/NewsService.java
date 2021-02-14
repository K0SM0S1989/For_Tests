package com.testtask.demo.service;

import com.testtask.demo.dto.NewsDTO;
import com.testtask.demo.exceptions.NewsNotFoundException;
import com.testtask.demo.responce.NewsResponseList;
import com.testtask.demo.responce.StringResponse;

import java.util.List;

public interface NewsService {
     List<NewsResponseList> getAllNews();

     List<NewsResponseList> getByCategory(String category);

     List<NewsResponseList> getByQuery(String query) throws NewsNotFoundException;

    NewsResponseList getById(Long id) throws NewsNotFoundException;

    StringResponse addNews(NewsDTO newsDTO);

    StringResponse updateNews(Long id, NewsDTO newsDTO) throws NewsNotFoundException;

    StringResponse deleteNews(Long id) throws NewsNotFoundException;


}
