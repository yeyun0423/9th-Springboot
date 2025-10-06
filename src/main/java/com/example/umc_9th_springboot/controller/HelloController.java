package com.example.umc_9th_springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {
    @GetMapping("/hello")
    public String hello(Model model) {
        model.addAttribute("message", "안녕! 나는 솜솜, 최예윤");
        return "hello"; // templates/hello.html 을 찾아서 렌더링
    }
}
