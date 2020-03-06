package com.ljw.springboot.thymeleaf.service.impl;

import com.ljw.springboot.thymeleaf.mapper.ArticleMapper;
import com.ljw.springboot.thymeleaf.model.Article;
import com.ljw.springboot.thymeleaf.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticleServiceImpl implements ArticleService{
    @Autowired
    private ArticleMapper articleMapper;
    @Override
    public int SaveArticle(Article article) {
        articleMapper.insert(article);
        return 0;
    }
}
