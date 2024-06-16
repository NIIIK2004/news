package com.example.news.impl;

import com.example.news.dao.NewsDao;
import com.example.news.model.News;
import com.example.news.repo.NewsRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NewsImpl implements NewsDao {
    private final NewsRepo newsRepo;

    @Override
    public News save(News news) {
        return newsRepo.save(news);
    }

    @Override
    public void delete(Long id) {
        newsRepo.deleteById(id);
    }

    @Override
    public Optional<News> findById(Long id) {
        return newsRepo.findById(id);
    }

    @Override
    public List<News> findAll() {
        return newsRepo.findAll();
    }
}
