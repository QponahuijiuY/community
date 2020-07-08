package com.mutong.mtcommunity.controller;

import com.mutong.mtcommunity.model.Column;
import com.mutong.mtcommunity.model.Post;
import com.mutong.mtcommunity.model.User;
import com.mutong.mtcommunity.service.ColumnService;
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
 * @description: 主页显示controller
 * @Author: gengchen.jing@yoyi.com.cn
 * @Date: 2020-06-11 10:37
 */
@Controller
public class IndexController {

    @Resource
    private PostService postService;
    @Resource
    private ColumnService columnService;

    public static void main(String[] args) {

    }
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
     * @param model 页面
     * @param page 页码
     * @param orderModel 标签
     * @return
     */
    @GetMapping("/index")
    public String getIndexPage(Model model , Page page, @RequestParam(name = "orderModel", defaultValue = "0") int orderModel,@RequestParam(value = "specialColumn",defaultValue = "0")Integer specialColumn/*,@RequestParam("key") String key*/){
        //方法调用之前,SpringMVC会自动实例化Model和Page,并且将page自动注入给Model
        //所以在thymeleaf中可以直接访问Page对象中的数据.
        page.setRows(postService.findPostRows(0));
        page.setPath("/index?orderModel="+orderModel);

        //list返回一个查询列表
        List<Post> orderModelList = postService.findAllPosts(0, page.getOffset(), page.getLimit(),orderModel,specialColumn);
        List<Map<String, Object>> posts = new ArrayList<>();
        if (orderModelList != null){
            //遍历得到每一个post
            for (Post post : orderModelList) {
                HashMap<String, Object> map = new HashMap<>();
                //遍历得到的post对象信息放到map里面
                map.put("post",post);
                //根据post获取用户的相关信息
                User user = userService.findUserById(post.getUserId());
                map.put("user",user);
                Column column = columnService.findSpecialColumn(post.getSpecialColumn());
                map.put("column",column);
                posts.add(map);
            }
        }

        model.addAttribute("posts",posts);
        model.addAttribute("orderModel",orderModel);
        model.addAttribute("specialColumn",specialColumn);


        return "index";
    }

    @GetMapping("/error")
    public String getErrorPage(){
        return "other/500";
    }
    @GetMapping(value = "/denied")
    public String getDeniedPage(){
        return "other/404";
    }
}
