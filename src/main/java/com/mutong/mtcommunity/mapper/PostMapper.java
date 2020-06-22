package com.mutong.mtcommunity.mapper;

import com.mutong.mtcommunity.model.Post;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @description:
 * @Author: gengchen.jing@yoyi.com.cn
 * @Date: 2020-06-22 17:47
 */
@Repository
@Mapper
public interface PostMapper {
    void insertPost(Post post);
}
