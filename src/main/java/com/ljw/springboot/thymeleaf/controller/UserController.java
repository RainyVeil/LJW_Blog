package com.ljw.springboot.thymeleaf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {
    @RequestMapping("/bootstrap/hello")
    public String bthello(Model model){
        return "/bootstrapdemo/hello";
    }
    @RequestMapping("/bootstrap/bootstrapCSS")
    public String btCSS(Model model){
        return "/bootstrapdemo/bootstrapCSS";
    }
    @RequestMapping("/bootstrap/bootstrapLayout")
    public String btLayout(Model model){
        return "/bootstrapdemo/bootstrapLayout";
    }





}
