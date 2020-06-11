package com.mutong.mtcommunity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @description:
 * @Author: gengchen.jing@yoyi.com.cn
 * @Date: 2020-06-11 13:32
 */
@Controller
public class LoginController {

    @GetMapping("/login")
    public String getLoginPage(){
        return "user/login";
    }

    @GetMapping("/reg")
    public String getRegisterPage(){
        return "user/reg";
    }

    @PostMapping("/reg")
    public String register(){

    }

}
