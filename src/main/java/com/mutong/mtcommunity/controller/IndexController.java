package com.mutong.mtcommunity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @description:
 * @Author: gengchen.jing@yoyi.com.cn
 * @Date: 2020-06-11 10:37
 */
@Controller
public class IndexController {


    @RequestMapping("/")
    public String index(){
        return "index";
    }
    @GetMapping("/case")
    public String example(){
        return "case/case";
    }

}
