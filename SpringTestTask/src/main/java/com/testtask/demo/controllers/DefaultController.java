package com.testtask.demo.controllers;

import com.testtask.demo.exceptions.NewsNotFoundException;
import com.testtask.demo.service.NewsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class DefaultController {
    private final NewsService newsService;

    public DefaultController(NewsService newsService) {
        this.newsService = newsService;
    }

    @GetMapping
    public String mainPage(Model model){
        model.addAttribute("list_news", newsService.getAllNews());
        return "index";
    }

}
