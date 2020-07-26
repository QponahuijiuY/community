package com.mutong.mtcommunity.service;

import com.mutong.mtcommunity.kafka.EventProducer;
import com.mutong.mtcommunity.model.Event;
import com.mutong.mtcommunity.utils.CommunityConstant;
import com.mutong.mtcommunity.utils.HostHolder;
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
public class LikeService extends RedisKeyUtil implements CommunityConstant {


    @Resource
    private RedisTemplate redisTemplate;
    @Resource
    private HostHolder hostHolder;
    @Resource
    private PostService postService;
    @Resource
    private EventProducer eventProducer;
    @Resource
    private CommentService commentService;
    /**
     * key :  like:post:
     * @param userId
     * @param postId
     */
    public void likePost(int userId, int postId) {
        String likePostKey = getLikePostKey(postId);
        boolean hasLike = hasLike(userId, postId);
        if (hasLike){
            redisTemplate.opsForSet().remove(likePostKey,userId);
        }else{
            redisTemplate.opsForSet().add(likePostKey,userId);
            Event event = new Event();
            event.setTopic(TOPIC_LIKE);
            //点赞帖子
            event.setEntityType(1);
            event.setUserId(hostHolder.getUser().getId());
            event.setEntityId(postId);
            event.setEntityUserId(postService.findPostById(postId).getUserId());
            eventProducer.fireEvent(event);
        }
    }

    public void likeComment(int userId,int commentId){
        String likeCommentKey = getLikeCommentKey(commentId);
        Boolean member = redisTemplate.opsForSet().isMember(likeCommentKey, userId);
        if (member){
            redisTemplate.opsForSet().remove(likeCommentKey,userId);
            commentService.decreaseLikeCount(commentId,1);
        }else{
            redisTemplate.opsForSet().add(likeCommentKey,userId);
            commentService.increaseLikeCount(commentId,1);
            Event event = new Event();
            event.setTopic(TOPIC_LIKE);
            //点赞评论
            event.setEntityType(2);
            event.setUserId(hostHolder.getUser().getId());
            event.setEntityId(commentId);
            event.setEntityUserId(commentService.findCommentById(commentId).getUserId());
            eventProducer.fireEvent(event);
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
