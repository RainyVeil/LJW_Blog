package com.ljw.springboot.thymeleaf.controller;

import com.ljw.springboot.thymeleaf.model.Article;
import com.ljw.springboot.thymeleaf.model.User;
import com.ljw.springboot.thymeleaf.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

@Controller
public class BackGroundController {
    @Autowired
    ArticleService articleService;

    @RequestMapping("/blog/background")
    public String starter(Model model){
        return "background/starter";
    }
    @RequestMapping("/blog/backindex1")
    public String indexpage(Model model){
        return "background/index";
    }
    @RequestMapping("/blog/backindex2")
    public String indexpage2(Model model){
        return "background/index2";
    }
    @RequestMapping("/blog/backindex3")
    public String indexpage3(Model model){
        return "background/index3";
    }
    @RequestMapping("/blog/editor")
    public String editor(Model model){
        return "background/editor";
    }


    @ResponseBody
    @RequestMapping("/blog/editorSave")
    public HashMap editorSave(Article article,HttpServletRequest request, HttpServletResponse response){

        HashMap data = new HashMap();
        data.put("success","true");

        User loginUser = (User)request.getSession().getAttribute("USER_SESSION");
        article.setAccountId(loginUser.getAccountId());
        article.setContent(request.getParameter("content"));
        article.setTitlename(request.getParameter("title_name"));
        article.setTypeId(request.getParameter("type_id"));
        article.setCreatedate(new Date());

        articleService.SaveArticle(article);
        return data;
    }
}
