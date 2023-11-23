package com.example.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

//@Controller는 해당 클래스가 컨트롤러라고 표시해주는 어노테이션
@Controller
public class HelloController {
    @GetMapping("/hello")
    @ResponseBody
    public String hello(){
        return "Hello Eujiadfadfn Jeon";
    }
}
