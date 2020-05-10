package com.ljw.springboot.thymeleaf.mapper;

import com.ljw.springboot.thymeleaf.model.Title;

import java.util.ArrayList;
import java.util.Map;

public interface TitleMapper {
    int deleteByPrimaryKey(Integer titleId);

    int insert(Title record);

    int insertSelective(Title record);

    Title selectByPrimaryKey(Integer titleId);

    int updateByPrimaryKeySelective(Title record);

    int updateByPrimaryKey(Title record);

    int addarticlepush(Map map);

    ArrayList selectArticlePush(Map map);
    //删除articlepush
    int deleteByid(Integer id);
}