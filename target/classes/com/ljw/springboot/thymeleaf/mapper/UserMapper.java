package com.ljw.springboot.thymeleaf.mapper;

import com.ljw.springboot.thymeleaf.model.User;

public interface UserMapper {
    int deleteByPrimaryKey(Integer accountId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer accountId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    User selectByuserid(String userid);
}