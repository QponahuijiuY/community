package com.mutong.mtcommunity.controller;

import cn.hutool.core.util.ObjectUtil;
import com.mutong.mtcommunity.model.Post;
import com.mutong.mtcommunity.model.User;
import com.mutong.mtcommunity.service.PostService;
import com.mutong.mtcommunity.utils.CommunityConstant;
import com.mutong.mtcommunity.utils.HostHolder;
import org.apache.maven.surefire.shade.org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * @description:
 * @Author: gengchen.jing@yoyi.com.cn
 * @Date: 2020-06-22 16:16
 */
@Controller
public class PostController implements CommunityConstant {
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
    public String add(@RequestParam("title") String titile, @RequestParam("demo") String content, @RequestParam("file") String s, @RequestParam("quiz") Integer specialColumn, Model model, HttpSession session,@RequestParam("code") String code){
        //从session里面获取验证码
        String kaptcha = (String) session.getAttribute("kaptcha");
        if (StringUtils.isBlank(code) || StringUtils.isBlank(kaptcha) || !kaptcha.equalsIgnoreCase(code)){
            model.addAttribute("codeMsg","验证码不正确");
            return "jie/add";
        }
        User user = hostHolder.getUser();
        Post post = new Post();
        post.setUserId(user.getId());
        post.setSpecialColumn(specialColumn);
        post.setContent(content);
        post.setTitle(titile);
        post.setCreateTime(new Date());
        post.setModTime(new Date());
        post.setPageView(0);
        post.setCommentCount(0);
        post.setType(0);
        post.setStatus(1);
        post.setScore(DEFAULT_SCORE);
        postService.insertPost(post);

        return "redirect:/";
    }
}
