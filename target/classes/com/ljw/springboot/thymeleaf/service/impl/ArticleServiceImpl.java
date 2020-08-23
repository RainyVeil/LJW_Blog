package com.ljw.springboot.thymeleaf.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ljw.springboot.thymeleaf.mapper.ArticleMapper;
import com.ljw.springboot.thymeleaf.mapper.BlogFileMapper;
import com.ljw.springboot.thymeleaf.mapper.TitleMapper;
import com.ljw.springboot.thymeleaf.model.Article;
import com.ljw.springboot.thymeleaf.model.BlogFile;
import com.ljw.springboot.thymeleaf.model.Title;
import com.ljw.springboot.thymeleaf.service.ArticleService;
import com.ljw.springboot.thymeleaf.util.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@Service
public class ArticleServiceImpl implements ArticleService{
    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private TitleMapper titleMapper;
    @Autowired
    private BlogFileMapper blogFileMapper;
    @Autowired
    private RedisTemplate<Object,Object> redisTemplate;

    @Override
    public int SaveArticle(Article article) {
        articleMapper.insert(article);
        return 0;
    }

    @Override
    public ArrayList<Article> query(Article article) {
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        //查询缓存
        ArrayList aDatagrid = (ArrayList<Article>)redisTemplate.opsForValue().get("ArticleDatagrid");
        if(null == aDatagrid){
            aDatagrid = articleMapper.selectByArticle(article);
            //存入缓存
            redisTemplate.opsForValue().set("ArticleDatagrid",aDatagrid);
        }

        return aDatagrid;
    }

    @Override
    public Article queryByAid(int aId) {
        return articleMapper.selectByPrimaryKey(aId);
    }

    @Override
    public int UpdateArticleByAid(Article article) {
        articleMapper.updateByPrimaryKeySelective(article);
        //字符串序列化器
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        //存入缓存
        redisTemplate.opsForValue().set("topArticles",articleMapper.selectTopArticle(new HashMap()));
        redisTemplate.opsForValue().set("ArticleDatagrid",articleMapper.selectByArticle(new Article()));

        return 0;
    }
//查询主页的文章
    @Override
    public  HashMap<String,ArrayList<Map>> queryTopArticle(HashMap map) {


        HashMap paraMap = new HashMap();
        //查询推荐文章
        HashMap<String,ArrayList<Map>> articleMap = queryPushInfo();
        //字符串序列化器
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        //查询缓存
        ArrayList toplist = (ArrayList<Article>)redisTemplate.opsForValue().get("topArticles");
        if(null == toplist){
            toplist = articleMapper.selectTopArticle(map);
            //存入缓存
            redisTemplate.opsForValue().set("topArticles",toplist);
        }
        //主题文章列表
        articleMap.put("topArticles",toplist);


        return articleMap;
    }

    //查询推荐文章
    @Override
    public  HashMap<String,ArrayList<Map>> queryPushInfo() {
        HashMap articleMap = new HashMap();
        HashMap paraMap = new HashMap();
        //特别推荐文章
        paraMap.put("type",1);
        articleMap.put("specPushArticles",titleMapper.selectArticlePush(paraMap));
        //推荐文章
        paraMap.put("type",2);
        articleMap.put("pushArticles",titleMapper.selectArticlePush(paraMap));
        return articleMap;
    }
    @Override
    public PageInfo<Map> queryTopArticlePage(int currentPage, int pageSize,HashMap map) {
        PageHelper.startPage(currentPage,pageSize);
        ArrayList<Map> list = articleMapper.selectTopArticlePage(map);
        return new PageInfo<Map>(list);
    }

    @Override
    public HashMap queryArticleInfo(Integer aId) {
        return articleMapper.selectArticleInfo(aId);
    }
    @Override
    public HashMap queryTypeMessage(Integer typeId) {
        return articleMapper.selectTypeMessage(typeId);
    }

    @Override
    public HashMap queryupdown(HashMap map) {
        return articleMapper.selectupdown(map);
    }

    @Override
    public int addArticlePush(int a_id, int type) {
        Map paraMap = new HashMap();
        paraMap.put("pushdate",new Date());
        paraMap.put("a_id",a_id);
        paraMap.put("type",type);
        paraMap.put("mark",1);

       return titleMapper.addarticlepush(paraMap);
    }

    @Override
    public HashMap publish(HashMap map) throws Exception {
        Title title = new Title();
        Map<String,Object> root=new HashMap<String, Object>();
        String result_msg="";//上传结果信息


        String brief_intro =(String)map.get("brief_intro");
        int styleid = (Integer)map.get("styleid");
        MultipartFile file1 = (MultipartFile)map.get("filename1");
        MultipartFile file2 = (MultipartFile)map.get("filename2");
        MultipartFile file3 = (MultipartFile)map.get("filename3");
        MultipartFile file4 = (MultipartFile)map.get("filename4");


        BlogFile blogFile  = new BlogFile();
        blogFile.setAdddate(new Date());
        blogFile.setAddress((String) map.get("localPath"));
        blogFile.setType("picture");
        blogFile.setInfo("发布文件时自动添加。");
        blogFile.setPurpose("title");
        final String localPath =(String) map.get("localPath");
        String fileName = null;
        String suffixName = null;
        if(styleid == 1){
            root = uploadPicture(file1,localPath);

            fileName = file1.getOriginalFilename();
            suffixName = fileName.substring(fileName.lastIndexOf("."));

            blogFile.setFilename((String) root.get("UUIDFileName"));
            blogFile.setSuffix(suffixName);
            blogFile.setSize( Long.toString(file1.getSize()));
            blogFileMapper.insertSelective(blogFile);

           if(root.get("relativePath") == null || root.get("relativePath") == ""){
               throw new Exception("图片1"+  root.get("result_msg"));
           }
            title.setPicture1((String) root.get("UUIDFileName"));
            root = uploadPicture(file2,localPath);


            fileName = file2.getOriginalFilename();
            suffixName = fileName.substring(fileName.lastIndexOf("."));


            blogFile.setFilename((String) root.get("UUIDFileName"));
            blogFile.setSuffix(suffixName);
            blogFile.setSize( Long.toString(file2.getSize()));
            blogFileMapper.insertSelective(blogFile);

            if(root.get("relativePath") == null || root.get("relativePath") == ""){
                throw new Exception("图片2"+  root.get("result_msg"));
            }
            title.setPicture2((String) root.get("UUIDFileName"));

            root = uploadPicture(file3,localPath);

            fileName = file3.getOriginalFilename();
            suffixName = fileName.substring(fileName.lastIndexOf("."));

            blogFile.setFilename((String) root.get("UUIDFileName"));
            blogFile.setSuffix(suffixName);
            blogFile.setSize( Long.toString(file3.getSize()));
            blogFileMapper.insertSelective(blogFile);
            if(root.get("relativePath") == null || root.get("relativePath") == ""){
                throw new Exception("图片3"+  root.get("result_msg"));
            }
            title.setPicture3((String) root.get("UUIDFileName"));

        }else if(styleid == 2){
            root = uploadPicture(file4,localPath);


            fileName = file4.getOriginalFilename();
            suffixName = fileName.substring(fileName.lastIndexOf("."));

            blogFile.setFilename((String) root.get("UUIDFileName"));
            blogFile.setSuffix(suffixName);
            blogFile.setSize( Long.toString(file4.getSize()));
            blogFileMapper.insertSelective(blogFile);
            if(root.get("relativePath") == null || root.get("relativePath") == ""){
                throw new Exception("图片4"+  root.get("result_msg"));
            }
            title.setPicture4((String) root.get("UUIDFileName"));

        }
        title.setBriefIntro(brief_intro);
        title.setDisplayType(styleid);
        int num1 =  titleMapper.insertSelective(title);
        Article a = new Article();
        a.setTitleId(title.getTitleId());
        a.setaId((Integer)map.get("publishAid"));
        a.setStatus(1);
        int num2 = articleMapper.updateByPrimaryKeySelective(a);
        if(!(num1 == 1 && num2 ==1)){
            throw new Exception("保存数据出错！");
        }
        //字符串序列化器
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        //存入缓存
        redisTemplate.opsForValue().set("topArticles",articleMapper.selectTopArticle(new HashMap()));
        redisTemplate.opsForValue().set("ArticleDatagrid",articleMapper.selectByArticle(new Article()));
        return null;
    }


    //上传图片到linux  picaddress /opt/static/pic
    //title表的picture只保存名字 relativePath 目前就只用于前台显示图片预览2020-05-14
    public  Map<String,Object> uploadPicture(MultipartFile file,String localPath) {

        Map<String,Object> root=new HashMap<String, Object>();
        String result_msg="";//上传结果信息
        if (file.getSize() / 1000 > 1000) {
            result_msg = "图片大小不能超过1MB";
        } else {
            //判断上传文件格式
            String fileType = file.getContentType();
            if (fileType.equals("image/jpeg") || fileType.equals("image/png") || fileType.equals("image/jpeg")) {

                //上传后保存的文件名(需要防止图片重名导致的文件覆盖)
                //获取文件名
                String fileName = file.getOriginalFilename();
                //获取文件后缀名
                String suffixName = fileName.substring(fileName.lastIndexOf("."));
                //重新生成文件名
                fileName = UUID.randomUUID() + suffixName;
                root.put("UUIDFileName",fileName);
                if (FileUtils.upload(file, localPath, fileName)) {
                    //文件存放的相对路径(一般存放在数据库用于img标签的src)
                    String relativePath = "http://47.108.30.112/pic/" + fileName;
                    root.put("relativePath", relativePath);//前端根据是否存在该字段来判断上传是否成功
                    result_msg = "图片上传成功";
                } else {
                    result_msg = "图片上传失败";
                }
            } else {
                result_msg = "图片格式不正确";
            }
        }
        root.put("result_msg",result_msg);
        return root;
    }
    public  ArrayList selectArticlePush(Map map){
        return titleMapper.selectArticlePush(map);
    }

    @Override
    public int deleteArticlePush(int id) {
        return titleMapper.deleteByid(id);
    }

    public int readTimesUpOne(Article article){
        return articleMapper.upReadTiemsByAid(article);
    }
}
