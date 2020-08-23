package com.ljw.springboot.thymeleaf.interceptor;

import com.ljw.springboot.thymeleaf.model.User;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

public class LoginInterceptor implements HandlerInterceptor{
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)throws Exception {
        //System.out.println("进入登录拦截器");
/*
        //获取session
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("USER_SESSION");
        //判断session中是否有用户数据，如果有，则返回true，继续向下执行
        if (user != null) {
            return true;
        }else {
            //不符合条件的给出提示信息，并转发到登录页面
            request.setAttribute("msg", "您还没有登录，请先登录！");
            request.setAttribute("errorCode", "1001");
            request.getRequestDispatcher("/blog/error").forward(request, response);
            return false;
        }*/
        return true;
    }
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           @Nullable ModelAndView modelAndView) throws Exception {
    }
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
                         @Nullable Exception ex) throws Exception {
    }
}
