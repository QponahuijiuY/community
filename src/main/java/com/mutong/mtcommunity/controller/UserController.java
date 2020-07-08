package com.mutong.mtcommunity.controller;

import com.mutong.mtcommunity.model.Post;
import com.mutong.mtcommunity.model.User;
import com.mutong.mtcommunity.service.PostService;
import com.mutong.mtcommunity.service.UserService;
import com.mutong.mtcommunity.utils.HostHolder;
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
    @Resource
    private HostHolder hostHolder;
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
    @GetMapping("/user")
    public String index(Model model){
        User user = hostHolder.getUser();
        if (user != null){
            List<Post> posts = postService.findAllPosts(user.getId(), 0, 15, 0, 0);
            model.addAttribute("posts",posts);
            model.addAttribute("user",user);
            return "user/index";
        }
        //findAllPosts(int userId,  int offset ,  int limit,int orderModel,int specialColumn){
        return "redirect:/";
    }
    @GetMapping("/collection")
    public String collection(Model model){
        User user = hostHolder.getUser();
        if(user != null){
            model.addAttribute("user",user);
            return "user/collection";
        }
        return "redirect:/";

    }
    @GetMapping("/set")
    public String setting(Model model){
        User user = hostHolder.getUser();
        model.addAttribute("user",user);

        return "user/set";
    }
    @GetMapping("/message")
    public String message(Model model){
        User user = hostHolder.getUser();
        model.addAttribute("user",user);

        return "user/message";
    }
}
