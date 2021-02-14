package com.testtask.demo.controllers;

import com.testtask.demo.dto.NewsDTO;
import com.testtask.demo.exceptions.NewsNotFoundException;
import com.testtask.demo.responce.NewsResponseList;
import com.testtask.demo.service.NewsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/api")
public class NewsController {
    private final NewsService newsService;

    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }


    @GetMapping("/category")
    public String getByCategory(@RequestParam String category, Model model){
        model.addAttribute("list_news",newsService.getByCategory(category));
        return "category";
    }

    @GetMapping("/search")
    public String getByQuery(@RequestParam String query, Model model) throws NewsNotFoundException {
         model.addAttribute("list_news", newsService.getByQuery(query));
        return "search-by-query";
    }

    @GetMapping("/choice/{id}")
    public String getById(@PathVariable Long id, Model model) throws NewsNotFoundException {
        List<NewsResponseList> responseLists = new ArrayList<>();
        responseLists.add(newsService.getById(id));
        model.addAttribute("news_by_id", responseLists);
        return "news-details";
    }

    @PostMapping("/add")
    public String addNews(@Valid NewsDTO newsDTO) {
        newsService.addNews(newsDTO);
        return "redirect:/";
    }

    @GetMapping("/add")
    public String addNewsGet() {
        return "add";
    }

    @PostMapping("/update/{id}")
    public String updateNews(@PathVariable Long id, @Valid NewsDTO newsDTO) throws NewsNotFoundException {
        newsService.updateNews(id, newsDTO);
        return "redirect:/";
    }

    @GetMapping("/update/{id}")
    public String updatePage(@PathVariable Long id, Model model) throws NewsNotFoundException {
        List<NewsResponseList> responseLists = new ArrayList<>();
        responseLists.add(newsService.getById(id));
        model.addAttribute("news_for_update", responseLists);
        return "news-edit";
    }

    @PostMapping("/delete/{id}")
    public String deleteNews(@PathVariable Long id) throws NewsNotFoundException {
        newsService.deleteNews(id);
        return "redirect:/";
    }
}
