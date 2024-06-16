package com.example.news.dao;

import com.example.news.model.News;

import java.util.List;
import java.util.Optional;

public interface NewsDao {
    News save(News news);
    void delete(Long id);
    Optional<News> findById(Long id);
    List<News> findAll();

}
