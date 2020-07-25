package com.mutong.mtcommunity.controller;

import com.mutong.mtcommunity.model.User;
import com.mutong.mtcommunity.service.LikeService;
import com.mutong.mtcommunity.utils.CommunityUtil;
import com.mutong.mtcommunity.utils.HostHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @description: 点赞collection
 * @Author: gengchen.jing@yoyi.com.cn
 * @Date: 2020-07-15 14:29
 */
@Controller
public class LikeController {
    @Resource
    private HostHolder hostHolder;
    @Resource
    private LikeService likeService;
    /**
     * @param postId
     * @return
     */
    @PostMapping("/likePost")
    @ResponseBody
    public String likePost(int postId){
        User user = hostHolder.getUser();
        likeService.likePost(user.getId(),postId);

        return CommunityUtil.getJSONString(0,"操作成功!");
    }


    @PostMapping("/likeComment")
    @ResponseBody
    public String likeComment(int commentId){
        User user = hostHolder.getUser();
        likeService.likeComment(user.getId(),commentId);
        return CommunityUtil.getJSONString(0,"操作成功！");
    }
}
