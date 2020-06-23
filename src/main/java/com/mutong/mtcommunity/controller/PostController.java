package com.mutong.mtcommunity.controller;

import cn.hutool.core.util.ObjectUtil;
import com.mutong.mtcommunity.model.Post;
import com.mutong.mtcommunity.model.User;
import com.mutong.mtcommunity.service.PostService;
import com.mutong.mtcommunity.utils.HostHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @description:
 * @Author: gengchen.jing@yoyi.com.cn
 * @Date: 2020-06-22 16:16
 */
@Controller
public class PostController {
    @Resource
    private HostHolder hostHolder;

    @Resource
    private PostService postService;
    @GetMapping("/add")
    public String getAddPage(Model model){
        User user = hostHolder.getUser();
        if (ObjectUtil.isNull(user) || ObjectUtil.isEmpty(user)){
            model.addAttribute("loginMsg","你还没有登陆");
            return "redirect:/login";
        }
        return "jie/add";

    }

    @PostMapping("/add")
    public String add(@RequestParam("title") String titile, @RequestParam("demo") String content,  @RequestParam("file") String s,@RequestParam("quiz") Integer specialColumn, Model model){
        User user = hostHolder.getUser();
        Post post = new Post();
        post.setUserId(user.getId());
        post.setColumn(specialColumn);
        post.setContent(content);
        post.setTitle(titile);
        post.setCreateTime(new Date());
        post.setModTime(new Date());
        postService.insertPost(post);
        return "redirect:/";
    }
}
