package com.ljw.springboot.thymeleaf.controller;

import com.ljw.springboot.thymeleaf.model.User;
import com.ljw.springboot.thymeleaf.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

@Controller
public class UserController {

    @Autowired
    UserService userService;
    @RequestMapping("/blog/index")
    public String indexpage(Model model){
        return "blog/index";
    }
    @RequestMapping("/blog/about")
    public String aboutpage(Model model){
        model.addAttribute("msg","关于我");
        return "/blog/about";
    }
    @RequestMapping("/blog/life")
    public String lifepage(Model model){
        model.addAttribute("msg","life");
        return "/blog/life";
    }
    //bt测试
    @RequestMapping("/bootstrap/hello")
    public String bthello(Model model){
        return "/bootstrapdemo/hello";
    }
    @RequestMapping("/bootstrap/bootstrapCSS")
    public String btCSS(Model model){
        return "/bootstrapdemo/bootstrapCSS";
    }
    @RequestMapping("/bootstrap/bootstrapLayout")
    public String btLayout(Model model){
        return "/bootstrapdemo/bootstrapLayout";
    }

    @RequestMapping("/blog/adminPage")
    public String adminpg(Model model){
        HashMap data = new HashMap();

        return "/background/starter";
    }
    @RequestMapping("/blog/error")
    public String showError(Model model){
        return "/blog/error";
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



        User loginUser =userService.getUserBysUserid(userid);

        if(loginUser != null){
            String checkpass = loginUser.getUserPass();
            if(userpass.equals(checkpass)){
                data.put("success","true");
                request.getSession().setAttribute("USER_SESSION",loginUser);

            }else{

                data.put("success","false");
                data.put("msg","用户密码错误！");

            }
        }else{
            data.put("success","false");
            data.put("msg","用户不存在！");
        }
        return data;
    }//


}
