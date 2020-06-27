package com.mutong.mtcommunity.service;

import com.mutong.mtcommunity.mapper.PostMapper;
import com.mutong.mtcommunity.model.Post;
import com.mutong.mtcommunity.utils.SensitiveFilter;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * @description:
 * @Author: gengchen.jing@yoyi.com.cn
 * @Date: 2020-06-22 17:43
 */
@Service
public class PostService {

    @Resource
    private SensitiveFilter sensitiveFilter;
    @Resource
    private PostMapper postMapper;




    /**
     * 插入帖子
     * @param post
     */
    public void insertPost(Post post){
        if (post == null){
            throw new IllegalArgumentException("参数不能为空");
        }
        //转移HTML标记
        post.setTitle(HtmlUtils.htmlEscape(post.getTitle()));
        post.setContent(HtmlUtils.htmlEscape(post.getContent()));
        //过滤敏感词
        post.setTitle(sensitiveFilter.filter(post.getTitle()));
        post.setContent(sensitiveFilter.filter(post.getContent()));
        postMapper.insertPost(post);
    }

    /**
     * 首页显示所有的帖子
     * @param userId 用户id
     * @param offset sql 限制
     * @param limit sql 限制
     * @return
     */
    public List<Post> findAllPosts(int userId,  int offset ,  int limit,int orderModel,int specialColumn){
        return postMapper.selectPosts(userId, offset, limit,orderModel,specialColumn);

    }

    public Post findPostById(int postId) {
        return postMapper.selectPostById(postId);
    }

    public int findPostRows(int userId) {
//        if (userId == 0){
//            return postRowsCache.get(userId);
//        }
//        logger.debug("从数据库里面取总行数数据");
        return postMapper.selectPostRows(userId);
    }

    public List<Post> findPostBySpecialColumn(int offset, int limit, Integer specialColumn) {

        return postMapper.selectPostBySpecialColumn(offset,limit,specialColumn);
    }
}
