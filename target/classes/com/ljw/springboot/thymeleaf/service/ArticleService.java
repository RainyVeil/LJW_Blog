package com.ljw.springboot.thymeleaf.service;

import com.github.pagehelper.PageInfo;
import com.ljw.springboot.thymeleaf.model.Article;
import org.omg.CORBA.portable.ApplicationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public interface ArticleService {
    int SaveArticle(Article articlerticle);
    ArrayList<Article> query(Article article);
    Article queryByAid(int aId);
    int UpdateArticleByAid(Article article);
    //不同分类的文章 按typeid查询
    HashMap<String,ArrayList<Map>> queryTopArticle(HashMap map);
    //查询推荐文章
    HashMap<String,ArrayList<Map>> queryPushInfo();
    //分页的查询
    PageInfo<Map> queryTopArticlePage(int currentPage, int pageSize, HashMap map);
    //查询文章的信息
    HashMap queryArticleInfo(Integer aId);
    //发布
    HashMap publish(HashMap Map) throws Exception;
    //查询界面标签信息
    HashMap queryTypeMessage(Integer typeId);
    //上下页
    HashMap queryupdown(HashMap map);
    //管理的新增文章推荐
    int addArticlePush(int a_id,int type);
    ArrayList selectArticlePush(Map map);
    int deleteArticlePush(int id);
    //阅读数加1
    int readTimesUpOne(Article article);
}
