package com.ljw.springboot.thymeleaf.controller;

import com.github.pagehelper.PageInfo;
import com.ljw.springboot.thymeleaf.model.User;
import com.ljw.springboot.thymeleaf.service.ArticleService;
import com.ljw.springboot.thymeleaf.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Controller
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    ArticleService articleService;

    @RequestMapping("/blog/about")
    public String aboutpage(Model model){
        model.addAttribute("msg","关于我");
        return "blog/about";
    }
    //查询不同类型的文章
    @RequestMapping("/blog/branch/{typeId}")
    public String branchpage(Model model,@PathVariable("typeId") int typeId){
        HashMap paraMap = new HashMap();
        paraMap.put("typeId",typeId);

        //分页信息
        PageInfo<Map> page = articleService.queryTopArticlePage( 1, 10,paraMap);
        //查询展示的文章
        HashMap<String,ArrayList<Map>> aticleMap = articleService.queryTopArticle(paraMap);
        model.addAttribute("topArticles",aticleMap.get("topArticles"));
        model.addAttribute("specPushArticles",aticleMap.get("specPushArticles"));
        model.addAttribute("pushArticles",aticleMap.get("pushArticles"));
        //查询一些标签name和分页信息
        HashMap typeMessage = articleService.queryTypeMessage(typeId);


        model.addAttribute("typeMessage",typeMessage);
        model.addAttribute("total",page.getTotal());
        model.addAttribute("lastPage",page.getNavigateLastPage());
        model.addAttribute("pageNums",page.getNavigatepageNums());
        model.addAttribute("nextpage",page.getNextPage());


        return "blog/branch";
    }
    //查询不同类型的文章 分页查询
    @RequestMapping("/blog/branch/{typeId}/{currentPage}")
    public String branchpageInfo(Model model, @PathVariable("typeId") int typeId,@PathVariable("currentPage") int currentPage ){
        HashMap paraMap = new HashMap();
        paraMap.put("typeId",typeId);
        //分页信息
        PageInfo<Map> page = articleService.queryTopArticlePage( currentPage, 10,paraMap);

        //查询推荐文章信息
        HashMap<String,ArrayList<Map>> pushMap = articleService.queryPushInfo();
        model.addAttribute("specPushArticles",pushMap.get("specPushArticles"));
        model.addAttribute("pushArticles",pushMap.get("pushArticles"));
        model.addAttribute("topArticles",page.getList());


        HashMap typeMessage = articleService.queryTypeMessage(typeId);
        model.addAttribute("typeMessage",typeMessage);
        model.addAttribute("total",page.getTotal());
        model.addAttribute("lastPage",page.getNavigateLastPage());
        model.addAttribute("pageNums",page.getNavigatepageNums());
        model.addAttribute("nextpage",page.getNextPage());


        return "blog/branch";
    }
/*	"pageNum": 1,
	"pageSize": 3,
	"size": 3,
	"startRow": 1,
	"endRow": 3,
	"pages": 6,
	"prePage": 0,
	"nextPage": 2,
	"isFirstPage": true,
	"isLastPage": false,
	"hasPreviousPage": false,
	"hasNextPage": true,
	"navigatePages": 8,
	"navigatepageNums": [1, 2, 3, 4, 5, 6],
	"navigateFirstPage": 1,
	"navigateLastPage": 6,
	"firstPage": 1,
	"lastPage": 6*/
    //上一页下一页

    @RequestMapping("/blog/showContent/{aId}")
    public String showContent(Model model,@PathVariable("aId") int aId){
        HashMap map1  = articleService.queryArticleInfo(aId);
        model.addAttribute("article",map1);
        HashMap paraMap = new HashMap();
        paraMap.put("aId",aId);
        paraMap.put("typeId",map1.get("typeId"));
        HashMap map2  = articleService.queryupdown(paraMap);
        model.addAttribute("updownPage",map2);
        //查询推荐信息
        HashMap<String,ArrayList<Map>> aticleMap = articleService.queryPushInfo();
        model.addAttribute("specPushArticles",aticleMap.get("specPushArticles"));
        model.addAttribute("pushArticles",aticleMap.get("pushArticles"));

        return "blog/content";
    }
    @RequestMapping("/blog/list")
    public String listpage(Model model){
        model.addAttribute("msg","list");
        return "blog/list";
    }

    //错误页面
    @RequestMapping("/blog/error")
    public String showError(Model model){
        return "blog/error";
    }


/*    @ResponseBody
    @RequestMapping("/blog/userLogin/{userid}/{userPass}")
    public HashMap userLoin(User user, HttpServletRequest request, HttpServletResponse response){
        String name = request.getParameter("userid");
        HashMap data = new HashMap();
        data.put("success","false");
        data.put("succmsg","保存成功！");
        return data;
    }*/

    @ResponseBody
    @RequestMapping("/blog/userLogin")
    public HashMap userLoin(User user, HttpServletRequest request, HttpServletResponse response){

        HashMap data = new HashMap();
        String userid = request.getParameter("userid");
        String userpass = request.getParameter("userPass");

        //获取当前用户
        Subject subject = SecurityUtils.getSubject();
        //封装用户数据
        UsernamePasswordToken token = new UsernamePasswordToken(userid,userpass);
        data.put("success", "true");

        try {
            subject.login(token);
        } catch (UnknownAccountException e) {
            data.put("success","false");
            data.put("msg","用户不存在！");
        }catch (IncorrectCredentialsException e) {
            data.put("success", "false");
            data.put("msg", "密码错误！");
        }
      /*  User loginUser =userService.getUserBysUserid(userid);
        if(loginUser != null){
            String checkpass = loginUser.getUserPass();
            if(userpass.equals(checkpass)){
                data.put("success","true");
                request.getSession().setAttribute("USER_SESSION",loginUser);
            }else{

                data.put("success","false");
                、‘；。、；、、、
                data.put("msg","用户密码错误！");
            }
        }else{
            data.put("success","false");
            data.put("msg","用户不存在！");
        }*/

        return data;
    }


    @ResponseBody
    @RequestMapping("/article/{aId}")
    public Object displaypage(Model model,@PathVariable("aId") int aId){
        return model;
    }


    //主页
    @RequestMapping("/blog/index")
    public String indexpage2(Model model){
        HashMap<String,ArrayList<Map>> aticleMap = articleService.queryTopArticle(new HashMap());

        model.addAttribute("topArticles",aticleMap.get("topArticles"));
        model.addAttribute("specPushArticles",aticleMap.get("specPushArticles"));
        model.addAttribute("pushArticles",aticleMap.get("pushArticles"));

        return "blog/index";
    }
    //权限错误页面
    @RequestMapping("/blog/tologin")
    public String tologin(Model model){
        return "blog/autherror";
    }




    @Value("${server.port}")
    public    String port;
    @ResponseBody
    @RequestMapping("/blog/index5")
    public String index5(Model model){
        return port;
    }


}
