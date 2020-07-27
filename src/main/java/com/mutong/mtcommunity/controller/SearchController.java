package com.mutong.mtcommunity.controller;

import com.mutong.mtcommunity.model.Column;
import com.mutong.mtcommunity.model.Post;
import com.mutong.mtcommunity.model.User;
import com.mutong.mtcommunity.service.*;
import com.mutong.mtcommunity.utils.HostHolder;
import com.mutong.mtcommunity.utils.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @Author: gengchen.jing@yoyi.com.cn
 * @Date: 2020-07-27 14:07
 */
@Controller
public class SearchController {
    @Resource
    private UserService userService;
    @Resource
    private ElasticSearchService elasticSearchService;
    @Resource
    private LikeService likeService;
    @Resource
    private ColumnService columnService;
    @Resource
    private PostService postService;
    @Resource
    private HostHolder hostHolder;
    @Resource
    private SignInService signInService;

    @GetMapping("/search")
    public String search(String keyword, Page page, Model model){
        //搜索帖子:
        org.springframework.data.domain.Page<Post> searchResult = elasticSearchService.searchPost(keyword, page.getCurrent()-1,page.getLimit());
        List<Map<String, Object>> posts = new ArrayList<>();
        if (searchResult != null){
            for (Post post : searchResult){
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
        List<Post> topPost = postService.findPostByType(2,0,5);
        List<User> lastActivityUserList = userService.findUserByTime(0, 12);
        boolean sigined = false;
        if (hostHolder.getUser() != null){
            sigined = signInService.isSigined(hostHolder.getUser().getId());
        }
        model.addAttribute("posts",posts);
        model.addAttribute("keyword",keyword);
        page.setPath("/search?keyword=" + keyword);
        page.setRows(searchResult == null ? 0 : (int) searchResult.getTotalElements());
        return "/site/search";
    }
}
