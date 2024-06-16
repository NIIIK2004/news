package com.example.news.controller;

import com.example.news.impl.NewsImpl;
import com.example.news.impl.UserImpl;
import com.example.news.model.News;
import com.example.news.model.User;
import com.example.news.repo.NewsRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class NewsController {
    private final UserImpl userImpl;
    private final NewsImpl newsImpl;
    private final NewsRepo newsRepo;

    @GetMapping("/")
    public String home(Authentication authentication, Model model, Principal principal) {
        if (principal != null) {
            String username = principal.getName();
            User user = userImpl.findByUsername(username);
            if (user == null) {
                return "redirect:/login?logout";
            }
            model.addAttribute("user", user);
        }

        if (authentication != null && authentication.isAuthenticated()) {
            model.addAttribute("newsList", newsImpl.findAll());
            return "index";
        }
        return "auth";
    }

//    @GetMapping("/{id}")
//    public String getNewsById(@PathVariable Long id, Model model) {
//        newsService.findNewsById(id).ifPresent(news -> model.addAttribute("news", news));
//        return "newsDetails";
//    }

    @GetMapping("/news/create")
    public String createNewsPage(Model model) {
        model.addAttribute("news", new News());
        return "create";
    }

    @PostMapping("/news/save")
    public String createNews(Authentication authentication, @ModelAttribute("news") News news) {
        if (authentication != null && authentication.isAuthenticated()) {
            newsImpl.save(news);
        }
        return "redirect:/";
    }

    @GetMapping("/news/delete/{id}")
    public String deleteNews(@PathVariable Long id) {
        News news = newsRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Инвалид айди друк"));
        newsRepo.deleteById(id);
        return "redirect:/";
    }

}













