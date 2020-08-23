package com.ljw.springboot.thymeleaf.shiro;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.ShiroFilter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 *Shiro配置类
 */
@Configuration
public class ShiroConfig {

    /*ShiroFilterFactoryBean*/
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager")DefaultWebSecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(securityManager);
       /**
        * 添加shiro内置过滤器*
        * 常用过滤器：
        *      anon:无需认证可以访问
        *      authc 必须认证
        *     user：如果使用remeberMe得功能可以直接访问
        *     perms: 该资源必须资源权限才可以访问
        *      role 该资源必须得到角色授权才可以访问
        */
        Map<String,String> filterMap = new LinkedHashMap<String,String>();
          filterMap.put("/blog/adminPage","authc");
            filterMap.put("/blog/articlepush","authc");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);

     //修改跳转登录页面
        shiroFilterFactoryBean.setLoginUrl("/blog/tologin");
        return shiroFilterFactoryBean;
    }

    /*创建DefaultWebSecurityManager*/
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm")UserRealm userRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(userRealm);
        return securityManager;
    }
    /*创建realm*/
    @Bean(name = "userRealm")
    public UserRealm userRealm(){
        return new UserRealm();
    }
}
