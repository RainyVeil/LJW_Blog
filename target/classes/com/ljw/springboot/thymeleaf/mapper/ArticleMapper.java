package com.ljw.springboot.thymeleaf.mapper;

import com.ljw.springboot.thymeleaf.model.Article;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public interface ArticleMapper {
    int deleteByPrimaryKey(Integer aId);

    int insert(Article record);

    int insertSelective(Article record);

    Article selectByPrimaryKey(Integer aId);

    int updateByPrimaryKeySelective(Article record);

    int updateByPrimaryKey(Article record);
    ArrayList<Article> selectByArticle(Article record);
    ArrayList<Map> selectTopArticle(HashMap map);
    ArrayList<Map> selectTopArticlePage(HashMap map);
    HashMap selectArticleInfo(Integer aId);
    HashMap selectTypeMessage(Integer typeId);
    HashMap selectupdown(HashMap map);
    int upReadTiemsByAid(Article record);
}