package com.ljw.springboot.thymeleaf.service;

import com.ljw.springboot.thymeleaf.model.Student;

import java.util.List;

public interface StudentService {
    List queryallstudent();
    int insertStudent(Student student);
    int editStudent(Student student);
    int deleteStudent(Student student);
}
