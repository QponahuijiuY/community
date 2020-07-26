package com.mutong.mtcommunity.mapper;

import com.mutong.mtcommunity.model.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @description:
 * @Author: gengchen.jing@yoyi.com.cn
 * @Date: 2020-07-24 11:02
 */
@Repository
@Mapper
public interface CommentMapper {
    List<Comment> selectCommentsByEntity(@Param("entityType") int entityType, @Param("entityId") int entityId, @Param("offset") int offset, @Param("limit") int limit);

    int selectCommentByEntity(@Param("entityType") int entityType, @Param("entityId") int entityId);

    int insertComment(Comment comment);

    Comment selectCommentById(@Param("id") int id);

    int updateStatusById(@Param("id") int id,@Param("status") int status);

    void updateLikeCountById(@Param("id") int id, @Param("i") int i);

    void increaseLikeCount(@Param("id") int id, @Param("i") int i);

    void decreaseLikeCount(@Param("id") int id, @Param("i") int i);

}
