package com.ljw.springboot.thymeleaf.mapper;

import com.ljw.springboot.thymeleaf.model.Student;

import java.util.List;

public interface StudentMapper {
    int deleteByPrimaryKey(Integer sId);

    int insert(Student record);

    int insertSelective(Student record);

    Student selectByPrimaryKey(Integer sId);

    int updateByPrimaryKeySelective(Student record);

    int updateByPrimaryKey(Student record);
    List queryallstudent();
    int insertStudent(Student student);
}