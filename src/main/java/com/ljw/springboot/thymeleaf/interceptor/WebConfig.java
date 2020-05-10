package com.ljw.springboot.thymeleaf.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class WebConfig implements WebMvcConfigurer {
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration registration = registry.addInterceptor(new LoginInterceptor());
        //拦截的路径
        String[] addPathPatterns = {
                "/blog/adminPage",
                "/blog/articlepush"
        };
        //不拦截的路径
        String[] excludePathPatterns = {
                "/blog/index",
                "/blog/userLogin"
        };
        registration.addPathPatterns(addPathPatterns);
        registration.excludePathPatterns(excludePathPatterns);
    }
}
