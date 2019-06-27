package com.ljw.springboot.thymeleaf.controller;

import com.ljw.springboot.thymeleaf.model.EasyuiJson;
import com.ljw.springboot.thymeleaf.model.Student;
import com.ljw.springboot.thymeleaf.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;

@Controller
public class StudentController {
    @Autowired
    StudentService studentService;

    @RequestMapping("/index")
    public String indexpage(Model model){
        model.addAttribute("msg","学生默认页面！");
        return "index";
    }
    @RequestMapping("/showStudent")
    public String show(Model model){
        model.addAttribute("msg","展示页面！");

        List students = studentService.queryallstudent();
        Student student1 = (Student)students.get(0);
        Student student2 = (Student)students.get(1);
        model.addAttribute("student1",student1);
        model.addAttribute("student2",student2);
        model.addAttribute("students",students);
        return "index";
    }

    @RequestMapping("/studentGrid")
    public String showStudentGrid(Model model){


        /*List students = studentService.queryallstudent();

        model.addAttribute("students",students);*/

        return "easyuitest";
    }
    @ResponseBody
    @RequestMapping("/addStudent")
    public Object addStudent(Student student){

            HashMap data = new HashMap();
            /*System.out.println("sId is:" + student.getsId());
            System.out.println("sName is:" + student.getsName());*/
            
            int num = studentService.insertStudent(student);

            if(num==1){
                data.put("success","true");
                data.put("succmsg","保存成功！");
            }else{
                data.put("success","false");
                data.put("succmsg","保存失败！");
            }

            return  data;
    }

    @ResponseBody
    @RequestMapping("/getStudentGrid")
    public EasyuiJson getStudentGrid(Model model){
        List students = studentService.queryallstudent();
        return new EasyuiJson(students.size(),students);
    }
}
