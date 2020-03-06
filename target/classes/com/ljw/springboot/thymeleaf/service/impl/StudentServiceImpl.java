package com.ljw.springboot.thymeleaf.service.impl;

import com.ljw.springboot.thymeleaf.mapper.StudentMapper;
import com.ljw.springboot.thymeleaf.model.Student;
import com.ljw.springboot.thymeleaf.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentMapper studentMapper;
    @Override
    public List queryallstudent() {
        return studentMapper.queryallstudent();
    }
    public int insertStudent(Student student) {
        return studentMapper.insertStudent(student);
    }
    public int editStudent(Student student) { return studentMapper.updateByPrimaryKeySelective(student); }
    public int deleteStudent(Student student) { return studentMapper.deleteByPrimaryKey(student.getsId()); }
}
