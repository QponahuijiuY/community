package com.mutong.mtcommunity.service;

import com.mutong.mtcommunity.mapper.CommentMapper;
import com.mutong.mtcommunity.model.Comment;
import com.mutong.mtcommunity.utils.CommunityConstant;
import com.mutong.mtcommunity.utils.SensitiveFilter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @description:
 * @Author: gengchen.jing@yoyi.com.cn
 * @Date: 2020-07-24 10:28
 */
@Service
public class CommentService implements CommunityConstant {
    @Resource
    private CommentMapper commentMapper;
    @Resource
    private SensitiveFilter sensitiveFilter;
    @Resource
    private PostService postService;

    public List<Comment> findCommentsByEntity(int entityType,int entityId,int offset, int limit){
        return commentMapper.selectCommentsByEntity(entityType,entityId,offset,limit);

    }
    public int findCommentCount(int entityType , int entityId){
        return commentMapper.selectCommentByEntity(entityType,entityId);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
    public int addComment(Comment comment){
        if (comment == null) {
            throw new IllegalArgumentException("参数不能为空!");
        }
        //添加评论
//        comment.setContent(HtmlUtils.htmlEscape(comment.getContent()));
        comment.setContent(sensitiveFilter.filter(comment.getContent()));
        int rows = commentMapper.insertComment(comment);

        //更新帖子的评论数量
        if (comment.getEntityType() == ENTITY_TYPE_POST){
            //查询帖子评论数量
            int count = commentMapper.selectCommentByEntity(comment.getEntityType(),comment.getEntityId());
            //根据id更新帖子的评论的数量
            postService.updateCommentCount(comment.getEntityId() , count);
        }
        return rows;
    }

    public Comment findCommentById(int id) {
        return commentMapper.selectCommentById(id);
    }

    public List<Comment> findCommentByEntity(int entityType, Integer entityId, int offset, int limit) {
        return commentMapper.selectCommentsByEntity(entityType,entityId,offset,limit);
    }

    public int deleteCommentById(int id,int status) {
        return commentMapper.updateStatusById(id,status);
    }
}
