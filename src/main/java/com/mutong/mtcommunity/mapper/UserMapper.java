package com.mutong.mtcommunity.mapper;

import com.mutong.mtcommunity.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

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

    void updateUser(@Param("id") Integer id, @Param("password")String password, @Param("headerUrl") String headerUrl, @Param("email") String email, @Param("nickname") String nickname, @Param("address") String address, @Param("signature") String signature, @Param("type") Integer type, @Param("modTime") Date modTime);

    User selectPassById(@Param("id") Integer id);

    void updatePassword(@Param("id") int id, @Param("password") String password);

    Integer updateHeaderUrl(@Param("id") Integer id, @Param("headerUrl") String headerUrl);
}
