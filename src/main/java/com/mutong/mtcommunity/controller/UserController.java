package com.mutong.mtcommunity.controller;

import com.mutong.mtcommunity.model.Post;
import com.mutong.mtcommunity.model.User;
import com.mutong.mtcommunity.service.PostService;
import com.mutong.mtcommunity.service.UserService;
import com.mutong.mtcommunity.utils.HostHolder;
import com.mutong.mtcommunity.utils.RedisKeyUtil;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @description: 用户相关controller
 * @Author: gengchen.jing@yoyi.com.cn
 * @Date: 2020-06-25 17:33
 */
@Controller
public class UserController extends RedisKeyUtil {
    @Resource
    private UserService userService;
    @Resource
    private PostService postService;
    @Resource
    private HostHolder hostHolder;
    @Resource
    private RedisTemplate redisTemplate;
    @GetMapping("/user/{userId}")
    public String detail(@PathVariable("userId")Integer userId, Model model, HttpServletRequest request){
        User user = userService.findUserById(userId);
        if (user == null){
            throw new RuntimeException("该用户不存在");
        }
        List<Post> posts = postService.findPostByUserId(userId);
        model.addAttribute("user",user);
        model.addAttribute("posts",posts);
        return "user/home";
    }
    @GetMapping("/user")
    public String index(Model model){
        User user = hostHolder.getUser();
        if (user != null){
            List<Post> posts = postService.findAllPosts(user.getId(), 0, 15, 0, 0);
            int rows = postService.findPostRows(user.getId());
            String collectionUserKey = getCollectionUserKey(user.getId());
            Long size = redisTemplate.opsForSet().size(collectionUserKey);
            model.addAttribute("size",size);
            model.addAttribute("rows",rows);
            model.addAttribute("posts",posts);
            model.addAttribute("user",user);
            return "user/index";
        }
        //findAllPosts(int userId,  int offset ,  int limit,int orderModel,int specialColumn){
        return "redirect:/";
    }
    @GetMapping("/user/collection")
    public String collection(Model model){
        User user = hostHolder.getUser();
        if(user != null){
            String collectionUserKey = getCollectionUserKey(user.getId());
            Long size = redisTemplate.opsForSet().size(collectionUserKey);
            Set<Integer> postIds = redisTemplate.opsForSet().members(collectionUserKey);
            List<Post> lists = new ArrayList<>();
            for (Integer postId : postIds) {
                Post post = postService.findPostById(postId);
                lists.add(post);
            }

            int rows = postService.findPostRows(user.getId());
            model.addAttribute("lists",lists);
            model.addAttribute("size",size);
            model.addAttribute("rows",rows);
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


    @PostMapping("/user/update")
    public String updateUser(String email,String nickname,String city,String signature){
        User loginUser = hostHolder.getUser();
        userService.updateUser(loginUser.getId(),email,nickname,city,signature);
        return "redirect:/set";
    }

    @PostMapping("/user/updatepass")
    public String updatePass(String pass,String repass){
        User user = hostHolder.getUser();
        userService.updatePassword(user.getId(),pass);
        return "redirect:/set";
    }

    @PostMapping("/user/updateHeaderUrl")
    @ResponseBody
    public Map<String,Object> updateHeaderUrl(String header){
        Map<String,Object> map = new HashMap<>();
        User user = hostHolder.getUser();
        int i = userService.updateHeaderUrl(user.getId(), header);
        if(i==1) {
            map.put("code",200);
            map.put("msg","恭喜您，头像修改成功！！！");
        }
        else{
            map.put("code",500);
            map.put("msg","修改头像出问题啦！");
        }
        return map;
    }
}
