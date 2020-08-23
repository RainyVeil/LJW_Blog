package com.ljw.springboot.thymeleaf.service;

import com.ljw.springboot.thymeleaf.model.User;

public interface UserService {
    User getUserBysUserid(String userid);
}
