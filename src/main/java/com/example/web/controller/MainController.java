package com.example.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {
    @GetMapping("/sbb")
    // Request 요청에 대한 리턴값을 표현하기 위한 어노테이션
    @ResponseBody
    public String index() {
        //System.out.println("index");
        return "index";
    }

    @GetMapping("/")
    public String root(){
        return "redirect:/question/list";
    }
}
