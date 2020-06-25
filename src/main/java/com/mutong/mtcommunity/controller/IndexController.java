package com.mutong.mtcommunity.controller;

import com.mutong.mtcommunity.model.Post;
import com.mutong.mtcommunity.model.User;
import com.mutong.mtcommunity.service.PostService;
import com.mutong.mtcommunity.service.UserService;
import com.mutong.mtcommunity.utils.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @Author: gengchen.jing@yoyi.com.cn
 * @Date: 2020-06-11 10:37
 */
@Controller
public class IndexController {

    @Resource
    private PostService postService;

    @Resource
    private UserService userService;
    @RequestMapping("/")
    public String index(){
        return "redirect:/index";
    }
    @GetMapping("/case")
    public String example(){
        return "case/case";
    }

    /**
     *
     * @param model
     * @param page 页面
     * @return
     */
    @GetMapping("/index")
    public String getIndexPage(Model model , Page page, @RequestParam(name = "orderModel", defaultValue = "0") int orderModel){
        //方法调用之前,SpringMVC会自动实例化Model和Page,并且将page自动注入给Model
        //所以在thymeleaf中可以直接访问Page对象中的数据.
//        page.setRows(discussPostService.findDiscussPostRows(0));
//        page.setPath("/index?orderModel="+orderModel);

        //list返回一个查询列表
        List<Post> list = postService.findAllPosts(0, page.getOffset(), page.getLimit(),orderModel);
        List<Map<String, Object>> posts = new ArrayList<>();
        if (list != null){
            //遍历得到每一个discusspost
            for (Post post : list) {
                HashMap<String, Object> map = new HashMap<>();
                //遍历得到的post对象信息放到map里面
                map.put("post",post);
                //根据post获取用户的相关信息
                User user = userService.findUserById(post.getUserId());
                map.put("user",user);
                posts.add(map);
            }
        }
        model.addAttribute("posts",posts);
//        model.addAttribute("orderModel",orderModel);

        return "index";
    }

//    @GetMapping("/error")
//    public String getErrorPage(){
//        return "other/500";
//    }
    @GetMapping(value = "/denied")
    public String getDeniedPage(){
        return "other/404";
    }
}
