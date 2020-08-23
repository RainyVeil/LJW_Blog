package com.ljw.springboot.thymeleaf.service.impl;

import com.ljw.springboot.thymeleaf.mapper.UserMapper;
import com.ljw.springboot.thymeleaf.model.User;
import com.ljw.springboot.thymeleaf.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    public User getUserBysUserid(String userid){
       return userMapper.selectByuserid(userid);
    }

}
