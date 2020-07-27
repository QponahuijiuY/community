package com.mutong.mtcommunity.mapper;

import com.mutong.mtcommunity.model.Post;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @description:
 * @Author: gengchen.jing@yoyi.com.cn
 * @Date: 2020-06-22 17:47
 */
@Repository
@Mapper
public interface PostMapper {
    void insertPost(Post post);

    List<Post> selectPosts(@Param("userId") int userId, @Param("offset") int offset, @Param("limit") int limit,@Param("orderModel") int orderModel,@Param("specialColumn")int specialColumn);

    Post selectPostById(@Param("id") int id);

    int selectPostRows(@Param("userId") Integer userId);

    List<Post> selectPostBySpecialColumn(@Param("offset") int offset, @Param("limit") int limit, @Param("specialColumn") Integer specialColumn);

    void updatePageView(@Param("id") int id);

    void increaseCollectionCount(@Param("postId") int postId);

    void decreaseCollectionCount(@Param("postId") int postId);


    void updatePost(@Param("postId") int postId, @Param("title") String title, @Param("content") String content, @Param("specialColumn") Integer specialColumn);

    List<Post> selectPostByUserId(@Param("userId") int userId,@Param("offset") int offset,@Param("limit") int limit);

    List<Post> selectPostByType(@Param("type") int type,@Param("offset") int offset,@Param("limit") int limit);

    List<Post> selectPostByComment();

    void updateCommentCount(@Param("id") int id, @Param("count") int count);
}
