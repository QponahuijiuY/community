package com.mutong.mtcommunity.controller;

import com.mutong.mtcommunity.model.User;
import com.mutong.mtcommunity.service.CollectionService;
import com.mutong.mtcommunity.service.PostService;
import com.mutong.mtcommunity.utils.CommunityUtil;
import com.mutong.mtcommunity.utils.HostHolder;
import com.mutong.mtcommunity.utils.RedisKeyUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @description: 收藏controller
 * @Author: gengchen.jing@yoyi.com.cn
 * @Date: 2020-07-11 15:36
 */
@Controller
public class CollectionController extends RedisKeyUtil {

    @Resource
    private CollectionService collectionService;
    @Resource
    private HostHolder hostHolder;
    @Resource
    private PostService postService;
    /**
     * 收藏
     * @param postId 帖子id
     */
    @PostMapping("/collection")
    @ResponseBody
    public String collection(int postId){
        User user = hostHolder.getUser();
        collectionService.collection(user.getId(),postId);
        return CommunityUtil.getJSONString(0,"操作成功!");
    }


}
