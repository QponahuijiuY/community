package com.mutong.mtcommunity.controller;

import com.mutong.mtcommunity.model.Post;
import com.mutong.mtcommunity.model.User;
import com.mutong.mtcommunity.service.PostService;
import com.mutong.mtcommunity.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description: 用户相关
 * @Author: gengchen.jing@yoyi.com.cn
 * @Date: 2020-06-25 17:33
 */
@Controller
public class UserController {
    @Resource
    private UserService userService;
    @Resource
    private PostService postService;
    @GetMapping("/user/{userId}")
    public String detail(@PathVariable("userId")Integer userId, Model model, HttpServletRequest request){
        User user = userService.findUserById(userId);
        if (user == null){
            throw new RuntimeException("该用户不存在");
        }
        Map<String,Object> map = new HashMap<>();
        List<Post> posts = postService.findAllPosts(userId, 0, 0, 0, 0);
        model.addAttribute("user",user);
        model.addAttribute("posts",posts);
        return "/user/home";
    }
}
