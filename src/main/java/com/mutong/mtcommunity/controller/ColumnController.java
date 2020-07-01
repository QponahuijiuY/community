//package com.mutong.mtcommunity.controller;
//
//import com.mutong.mtcommunity.model.Post;
//import com.mutong.mtcommunity.model.User;
//import com.mutong.mtcommunity.service.PostService;
//import com.mutong.mtcommunity.service.UserService;
//import com.mutong.mtcommunity.utils.Page;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import javax.annotation.Resource;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
///**
// * @description: 分专栏显示
// * @Author: gengchen.jing@yoyi.com.cn
// * @Date: 2020-06-25 17:32
// */
//
//@Controller
//public class ColumnController {
//    @Resource
//    private PostService postService;
//    @Resource
//    private UserService userService;
//
//    @GetMapping("/post")
//    public String column(@RequestParam("specialColumn") Integer specialColumn, Model model, Page page){
////list返回一个查询列表
//        List<Post> orderModelList = postService.findPostBySpecialColumn( page.getOffset(), page.getLimit(),specialColumn);
//        List<Map<String, Object>> posts = new ArrayList<>();
//        if (orderModelList != null){
//            //遍历得到每一个post
//            for (Post post : orderModelList) {
//                HashMap<String, Object> map = new HashMap<>();
//                //遍历得到的post对象信息放到map里面
//                map.put("post",post);
//                //根据post获取用户的相关信息
//                User user = userService.findUserById(post.getUserId());
//                map.put("user",user);
//                posts.add(map);
//            }
//        }
//
//        model.addAttribute("posts",posts);
//        model.addAttribute("specialColumn",specialColumn);
//        return "redirect:/post";
//    }
//}
