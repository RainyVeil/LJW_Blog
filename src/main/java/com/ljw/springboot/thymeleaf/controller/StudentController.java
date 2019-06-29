package com.ljw.springboot.thymeleaf.controller;

import com.ljw.springboot.thymeleaf.model.EasyuiJson;
import com.ljw.springboot.thymeleaf.model.Student;
import com.ljw.springboot.thymeleaf.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;

@Controller
public class StudentController {
    @Autowired
    StudentService studentService;
    @Autowired
    private RedisTemplate<Object,Object> redistemp;
    RedisSerializer redisSerializer = new StringRedisSerializer();

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
    //学生管理界面
    @RequestMapping("/studentGrid")
    public String showStudentGrid(Model model){


        return "easyuitest";
    }
    @ResponseBody
    @RequestMapping("/addStudent")
    public Object addStudent(Student student){

        HashMap data = new HashMap();
        /*System.out.println("sId is:" + student.getsId());
        System.out.println("sName is:" + student.getsName());*/

        int num = studentService.insertStudent(student);
        boolean flag = false;
        if(num==1) flag =true;

        return  fallbackdata(flag);
    }
    @ResponseBody
    @RequestMapping("/editStudent/{psId}")
    public Object editStudent(Student student,@PathVariable("psId") int psId){
        student.setsId(psId);
        int num = studentService.editStudent(student);
        boolean flag = false;
        if(num==1) flag =true;
        return  fallbackdata(flag);
    }
    @ResponseBody
    @RequestMapping("/deleteStudent/{sId}")
    public Object deleteStudent(Student student,@PathVariable("sId") int sId){
        student.setsId(sId);
        int num = studentService.deleteStudent(student);
        boolean flag = false;
        if(num==1) flag =true;
        return  fallbackdata(flag);
    }



    //查询student表数据
    @ResponseBody
    @RequestMapping("/getStudentGrid")
    public EasyuiJson getStudentGrid(Model model){
        redistemp.setKeySerializer(redisSerializer);
        List students = (List<Student>) redistemp.opsForValue().get("allStudents");

        if(null == students || 0 == students.size()){
            synchronized (this){//高并发下防止缓存穿透
                students = (List<Student>) redistemp.opsForValue().get("allStudents");
                if(null == students || 0 == students.size()){
                    students = studentService.queryallstudent();
                    redistemp.opsForValue().set("allStudents",students);
                }
            }
        }
        return new EasyuiJson(students.size(),students);
    }

    //设置回调函数信息
    private HashMap fallbackdata(boolean flag){
        HashMap data = new HashMap();
        if(flag){
            data.put("success","true");
            data.put("succmsg","保存成功！");
            redistemp.delete("allStudents"); //成功删除缓存
        }else{
            data.put("success","false");
            data.put("succmsg","保存失败！");
        }
        return data;
    }

}
