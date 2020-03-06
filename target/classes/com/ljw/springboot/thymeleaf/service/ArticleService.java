package com.ljw.springboot.thymeleaf.service;

import com.ljw.springboot.thymeleaf.model.Article;
import org.springframework.stereotype.Service;


public interface ArticleService {
    int SaveArticle(Article articlerticle);
}
