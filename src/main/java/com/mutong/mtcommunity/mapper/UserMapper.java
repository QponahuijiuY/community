package com.mutong.mtcommunity.mapper;

import com.mutong.mtcommunity.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @description:
 * @Author: gengchen.jing@yoyi.com.cn
 * @Date: 2020-06-12 11:11
 */
@Repository
@Mapper
public interface UserMapper {

    void insertUser(User user);

    User selectUserByEmail(String email);

    User selectUserByName(String username);

    User selectUserById(@Param("id") int id);

    void updateStatus(@Param("id") int id, @Param("status") int status);
}
