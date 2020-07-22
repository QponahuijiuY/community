package com.mutong.mtcommunity.controller;

import com.mutong.mtcommunity.model.Column;
import com.mutong.mtcommunity.model.Post;
import com.mutong.mtcommunity.model.User;
import com.mutong.mtcommunity.service.ColumnService;
import com.mutong.mtcommunity.service.PostService;
import com.mutong.mtcommunity.service.SignInService;
import com.mutong.mtcommunity.service.UserService;
import com.mutong.mtcommunity.utils.HostHolder;
import com.mutong.mtcommunity.utils.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
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
    @Resource
    private SignInService signInService;
    public static void main(String[] args) {

    }
    @Resource
    private UserService userService;
    @RequestMapping("/")
    public String index(){
        return "redirect:/index";
    }
    @Resource
    private HostHolder hostHolder;
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
    public String getIndexPage(HttpServletRequest request, Model model ,@RequestParam(value = "pageId",defaultValue = "1") String pageId, Page page, @RequestParam(name = "orderModel", defaultValue = "0") int orderModel, @RequestParam(value = "specialColumn",defaultValue = "0")Integer specialColumn/*,@RequestParam("key") String key*/){
        //方法调用之前,SpringMVC会自动实例化Model和Page,并且将page自动注入给Model
        //所以在thymeleaf中可以直接访问Page对象中的数据.
        page.setRows(postService.findPostRows(0));
        page.setPath("/index?orderModel="+orderModel+"&current=" + specialColumn);

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
        List<Post> topPost = postService.findPostByType(2);
        List<User> lastActivityUserList = userService.findUserByTime(0, 12);
        if (lastActivityUserList != null){
            for (User user : lastActivityUserList) {

            }
        }

        boolean sigined = false;
        if (hostHolder.getUser() != null){
            sigined = signInService.isSigined(hostHolder.getUser().getId());
        }
        model.addAttribute("topPost",topPost);
        model.addAttribute("sigined",sigined);
        model.addAttribute("users",lastActivityUserList);
        model.addAttribute("posts",posts);
        model.addAttribute("orderModel",orderModel);
        model.addAttribute("specialColumn",specialColumn);


        return "index";
    }

    @GetMapping("/errer")
    public String getErrorPage(){
        return "other/404";
    }


}
