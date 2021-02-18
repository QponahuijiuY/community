package com.mutong.mtcommunity.controller;

import com.mutong.mtcommunity.utils.CommonResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller("/news")
public class NewsController {

    @GetMapping("/")
    public String getNews(){
        return null;
    }

    @GetMapping("/update")
    public CommonResult updateNews(){
        return null;
    }

    @GetMapping("/delete")
    public CommonResult deleteNews(){
        return null;
    }

    @GetMapping("")
    public CommonResult selectNews(){
        return null;
    }
}
