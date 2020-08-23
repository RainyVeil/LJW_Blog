package com.ljw.springboot.thymeleaf.mapper;

import com.ljw.springboot.thymeleaf.model.BlogFile;
import com.ljw.springboot.thymeleaf.model.BlogFileKey;

import java.util.ArrayList;

public interface BlogFileMapper {
    int deleteByPrimaryKey(BlogFileKey key);

    int insert(BlogFile record);

    int insertSelective(BlogFile record);

    BlogFile selectByPrimaryKey(BlogFileKey key);

    int updateByPrimaryKeySelective(BlogFile record);

    int updateByPrimaryKey(BlogFile record);

    ArrayList queryAllPicture (BlogFile record);
 }