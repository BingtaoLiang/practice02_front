package com.practice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    @GetMapping("/register")
    public String register(){
        return "register";
    }

    @GetMapping("/denglu")
    public String denglu(){
        return "denglu";
    }

    @GetMapping("/weici")
    public String weici(){
        return "weici";
    }
}
