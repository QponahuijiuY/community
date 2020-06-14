package com.mutong.mtcommunity.controller;

import com.mutong.mtcommunity.model.User;
import com.mutong.mtcommunity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;

/**
 * @description:
 * @Author: gengchen.jing@yoyi.com.cn
 * @Date: 2020-06-12 10:49
 */
@Controller
public class LoginController {

    @Autowired
    private UserService userService;
    @GetMapping("/reg")
    public String getRegisterPage() {
        return "user/reg";
    }

    @GetMapping("/login")
    public String getLoginPage() {
        return "user/login";
    }

    @PostMapping("/register")
    public void register(Model model, User user){
        Map<String,Object> map = userService.register(user);

    }

}
