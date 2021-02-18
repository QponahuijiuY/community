package com.mutong.mtcommunity.api;

import com.mutong.mtcommunity.utils.CommonResult;
import org.springframework.web.bind.annotation.*;

@RestController
public class NewsApi {

    @PutMapping("/news")
    public CommonResult updateNews(){
        return null;
    }

    @GetMapping("/news")
    public CommonResult selectNews(){
        return null;
    }

    @PostMapping("/news")
    public CommonResult insertNews(){
        return null;
    }

    @DeleteMapping("news")
    public CommonResult deleteNews(){
        return null;
    }
}
