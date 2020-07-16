package com.mutong.mtcommunity.service;

import com.mutong.mtcommunity.utils.RedisKeyUtil;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @description:
 * @Author: gengchen.jing@yoyi.com.cn
 * @Date: 2020-07-15 14:32
 */
@Service
public class LikeService extends RedisKeyUtil {


    @Resource
    private RedisTemplate redisTemplate;
    /**
     * key :  like:post:
     * @param userId
     * @param postId
     */
    public void like(int userId, int postId) {
        String likePostKey = getLikePostKey(postId);
        boolean hasLike = hasLike(userId, postId);
        if (hasLike){
            redisTemplate.opsForSet().remove(likePostKey,userId);
        }else{
            redisTemplate.opsForSet().add(likePostKey,userId);
        }


    }

    /**
     * 点赞状态
     * @param userId
     * @param postId
     * @return
     */
    public boolean hasLike(int userId,int postId){
        String likePostKey = getLikePostKey(postId);
        return  redisTemplate.opsForSet().isMember(likePostKey,userId);
    }

    /**
     * 点赞的数量
     * @param postId
     * @return
     */
    public long findLikePostKeySize(int postId){
        String likePostKey = getLikePostKey(postId);
        return redisTemplate.opsForSet().size(likePostKey);
    }
}
