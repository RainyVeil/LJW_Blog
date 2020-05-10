package com.ljw.springboot.thymeleaf.controller;

import com.alibaba.fastjson.JSON;
import com.ljw.springboot.thymeleaf.model.Article;
import com.ljw.springboot.thymeleaf.model.User;
import com.ljw.springboot.thymeleaf.service.ArticleService;
import com.ljw.springboot.thymeleaf.util.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class BackGroundController {
    @Autowired
    ArticleService articleService;


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
    @RequestMapping("/blog/predisplay")
    public String predisplay(Model model){
        return "blog/predisplay";
    }

    @RequestMapping("/blog/editor")
    public String editor(Model model){
        model.addAttribute("article",new Article());
        return "background/editor";
    }
    /*文章管理*/
    @RequestMapping("/blog/adminPage")
    public String adminpg(Model model){
        HashMap data = new HashMap();
        ArrayList<Article> alist = articleService.query(new Article());
        model.addAttribute("articles",alist);
        return "background/starter";
    }
    /*主页设置*/
    @RequestMapping("/blog/articlepush")
    public String articlepush(Model model){
        HashMap data = new HashMap();
        ArrayList<Article> alist = articleService.query(new Article());
        Map map = new HashMap();
        map.put("type",1);
        ArrayList pushlist =  articleService.selectArticlePush(map);
        model.addAttribute("pushArticles1",pushlist);
        map.put("type",2);
        ArrayList pushlist2 =  articleService.selectArticlePush(map);
        model.addAttribute("pushArticles2",pushlist2);
        model.addAttribute("articles",alist);
        return "background/articlepush";
    }

    @RequestMapping("/blog/edit/{aId}")
    public String edit(Model model,@PathVariable("aId") int aId){
        Article article = articleService.queryByAid(aId);

        model.addAttribute("article",article);
        return "background/editor";
    }
    //保存新文章
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
        article.setStatus(0);
        article.setReadtimes(0);
        article.setCollecttimes(0);
        articleService.SaveArticle(article);
        return data;
    }
    //修改文章
    @ResponseBody
    @RequestMapping("/blog/editSave")
    public HashMap editSave(Article article,HttpServletRequest request, HttpServletResponse response){

        HashMap data = new HashMap();
        data.put("success","true");

        article.setContent(request.getParameter("content"));
        article.setTitlename(request.getParameter("title_name"));
        article.setTypeId(request.getParameter("type_id"));
        article.setaId(Integer.parseInt(request.getParameter("aId")));
        article.setStatus(Integer.parseInt(request.getParameter("status")));
        article.setEditdate(new Date());
        articleService.UpdateArticleByAid(article);
        return data;
    }
    //上传图片
    @ResponseBody
    @RequestMapping("/blog/imageUpload")
    public Map imageUpload(HttpServletRequest request,@RequestParam("fileName") MultipartFile file){
        String result_msg="";//上传结果信息

        Map<String,Object> root=new HashMap<String, Object>();

        if (file.getSize() / 1000 > 1000){
            result_msg="图片大小不能超过1MB";
        }
        else{
            //判断上传文件格式
            String fileType = file.getContentType();
            if (fileType.equals("image/jpeg") || fileType.equals("image/png") || fileType.equals("image/jpeg")) {

                final String localPath = this.getClass().getClassLoader().getResource("").getPath()+"static/pic";
                //上传后保存的文件名(需要防止图片重名导致的文件覆盖)
                //获取文件名
                String fileName = file.getOriginalFilename();
                //获取文件后缀名
                String suffixName = fileName.substring(fileName.lastIndexOf("."));
                //重新生成文件名
                fileName = UUID.randomUUID()+suffixName;
                if (FileUtils.upload(file, localPath, fileName)) {
                    //文件存放的相对路径(一般存放在数据库用于img标签的src)
                    String relativePath="/ljw/pic/"+fileName;
                    root.put("relativePath",relativePath);//前端根据是否存在该字段来判断上传是否成功
                    result_msg="图片上传成功";
                }
                else{
                    result_msg="图片上传失败";
                }
            }
            else{
                result_msg="图片格式不正确";
            }
        }

        root.put("result_msg",result_msg);

//        JSON.toJSONString(root,SerializerFeature.DisableCircularReferenceDetect);
        String root_json= JSON.toJSONString(root);
        System.out.println(root_json);
        return root;
    }
    //点击发布后设置文章信息
    @ResponseBody
    @RequestMapping("/blog/setInfo")
    public HashMap setInfo(HttpServletRequest request, HttpServletResponse response){
        Integer aid = Integer.parseInt(request.getParameter("aid"));
        HashMap data = new HashMap();
        data.put("success","true");
        data.put("articleInfo",articleService.queryArticleInfo(aid));
        return data;
    }
    //发布
    @ResponseBody
    @RequestMapping("/blog/publish")
    public HashMap publish(HttpServletRequest request, HttpServletResponse response,
                           @RequestParam(value = "filename1",required=false) MultipartFile file1,
                           @RequestParam(value = "filename2",required=false) MultipartFile file2,
                           @RequestParam(value = "filename3",required=false) MultipartFile file3,
                           @RequestParam(value = "filename4",required=false) MultipartFile file4){


        int styleid = Integer.parseInt(request.getParameter("styleid"));
        String brief_intro = request.getParameter("brief_intro");
        int publishAid = Integer.parseInt(request.getParameter("publishAid"));

        HashMap map = new HashMap();
        HashMap data = new HashMap();
        data.put("success","true");
        map.put("brief_intro",brief_intro);
        map.put("styleid",styleid);
        map.put("publishAid",publishAid);
        map.put("filename1",file1);
        map.put("filename2",file2);
        map.put("filename3",file3);
        map.put("filename4",file4);
        map.put("localPath",request.getSession().getServletContext().getRealPath("/pic"));

       try {
            articleService.publish(map);
        } catch (Exception e) {
            e.printStackTrace();
            data.put("success","false");
            data.put("errorMessage","发布错误："+e.getMessage());
            return data;
        }


        return data;
    }

    //添加推荐
    @ResponseBody
    @RequestMapping("/blog/addarticlepush")
    public HashMap addarticlepush(HttpServletRequest request, HttpServletResponse response,
                                  @RequestParam(value="a_id",required=false) int a_id,
                                  @RequestParam(value="type",required=false) int type ){
        HashMap data = new HashMap();

        try {
            articleService.addArticlePush(a_id, type);
        } catch (Exception e) {
            e.printStackTrace();
            data.put("success","false");
            data.put("errorMessage","发布错误："+e.getMessage());
            return data;
        }
        data.put("success","true");
        return data;
    }
    //删除文章推荐
    @ResponseBody
    @RequestMapping("/blog/deletearticlepush")
    public HashMap deletearticlepush(HttpServletRequest request, HttpServletResponse response,
                                  @RequestParam(value="id",required=false) int id){
        HashMap data = new HashMap();

        try {
            articleService.deleteArticlePush(id);
        } catch (Exception e) {
            e.printStackTrace();
            data.put("success","false");
            data.put("errorMessage","删除错误："+e.getMessage());
            return data;
        }
        data.put("success","true");
        return data;
    }
}
