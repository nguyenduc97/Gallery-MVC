package com.haiduc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {
    @GetMapping("/login")
    public String login() {
        return "/login/index";
    }

    @PostMapping("/dologin")
    public String doLogin(){
        return "/admingallery/list";
    }
}
