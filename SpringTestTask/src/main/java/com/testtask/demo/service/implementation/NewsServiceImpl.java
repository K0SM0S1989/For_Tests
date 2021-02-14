package com.testtask.demo.service.implementation;

import com.testtask.demo.dto.NewsDTO;
import com.testtask.demo.enums.CategoryNames;
import com.testtask.demo.exceptions.AddNewsError;
import com.testtask.demo.exceptions.NewsNotFoundException;
import com.testtask.demo.mapping.NewsMap;
import com.testtask.demo.models.Category;
import com.testtask.demo.models.News;
import com.testtask.demo.repo.NewsRepo;
import com.testtask.demo.responce.NewsResponseList;
import com.testtask.demo.responce.StringResponse;
import com.testtask.demo.service.CategoryService;
import com.testtask.demo.service.NewsService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class NewsServiceImpl implements NewsService {
    private final NewsRepo newsRepo;
    private final CategoryService categoryService;

    public NewsServiceImpl(NewsRepo newsRepo, CategoryService categoryService) {
        this.newsRepo = newsRepo;
        this.categoryService = categoryService;
    }

    @Override
    public List<NewsResponseList> getAllNews(){
        return fillingResponseList(newsRepo.findAll());
    }

    @Override
    public List<NewsResponseList> getByCategory(String category){
        Category cat = categoryService.findByName(CategoryNames.valueOf(category));
        return fillingResponseList(newsRepo.findAllByCategory(cat));
    }

    @Override
    public List<NewsResponseList> getByQuery(String query){
        List<News> newsList = newsRepo.findAll();
        List<NewsResponseList> newsResponseList = new ArrayList<>();
        newsList.forEach(news -> {
            if (news.getTitle().contains(query) || news.getText().contains(query)) {
                newsResponseList.add(new NewsResponseList(news));
            }
        });
            return newsResponseList;
    }

    @Override
    public NewsResponseList getById(Long id) throws NewsNotFoundException {
        Optional<News> newsOptional = newsRepo.findById(id);
        if (newsOptional.isPresent()) {
            return new NewsResponseList(newsOptional.get());
        } else throw new NewsNotFoundException("Новостей нет");

    }

    @Override
    public StringResponse addNews(NewsDTO newsDTO) {
        if (newsDTO.getText().length() < 50 || newsDTO.getTitle().length() < 3) {
            return new StringResponse(AddNewsError.errorFail(newsDTO.getText(), newsDTO.getTitle()));
        } else {
            CategoryNames categoryNames;
            if (newsDTO.getCategoryNames().equals(CategoryNames.EMPTY.toString())) {
                categoryNames = CategoryNames.WORLD;
            } else categoryNames = CategoryNames.valueOf(newsDTO.getCategoryNames());
            Category cat = categoryService.findByName(categoryNames);
            News news = NewsMap.map(newsDTO, cat);
            newsRepo.save(news);
            Map<String, String> response = new HashMap<>();
            response.put("correct", "Новость добавлена");
            return new StringResponse(response);
        }

    }

    @Override
    public StringResponse updateNews(Long id, NewsDTO newsDTO) throws NewsNotFoundException {
        Optional<News> news = newsRepo.findById(id);
        if (news.isPresent()) {
            Map<String, String> textOfErrors = new HashMap<>();
            if (newsDTO.getTitle() != null) {
                if (newsDTO.getTitle().length() < 3) {
                    textOfErrors.put("title", "Заголовок публикации слишком короткий");
                } else news.get().setTitle(newsDTO.getTitle());
            }
            if (newsDTO.getText() != null) {
                if (newsDTO.getText().length() < 50) {
                    textOfErrors.put("text", "Текст публикации слишком короткий");
                } else news.get().setText(newsDTO.getText());
            }
            if (newsDTO.getCategoryNames() != null && !newsDTO.getCategoryNames().equals(CategoryNames.EMPTY.toString())) {
                Category cat = categoryService.findByName(CategoryNames.valueOf(newsDTO.getCategoryNames()));
                news.get().setCategory(cat);
            }
            newsRepo.save(news.get());
            Map<String, String> response = new HashMap<>();
            response.put("correct", "Новость изменена");
            if (textOfErrors.isEmpty()) {
                return new StringResponse(response);
            } else return new StringResponse(textOfErrors);
        } else {
            throw new NewsNotFoundException("Новость не найдена");
        }

    }

    @Override
    public StringResponse deleteNews(Long id) throws NewsNotFoundException {
        if (newsRepo.findById(id).isPresent()) {
            newsRepo.deleteById(id);
            Map<String, String> response = new HashMap<>();
            response.put("correct", "Новость удалена");
            return new StringResponse(response);
        } else {
            throw new NewsNotFoundException("Новость не найдена");
        }

    }

    private List<NewsResponseList> fillingResponseList(List<News> newsList){
        List<NewsResponseList> newsResponseList = new ArrayList<>();
        if (!newsList.isEmpty()) {
            newsList.forEach(news -> newsResponseList.add(new NewsResponseList(news)));
        }
        return newsResponseList;
    }
}
