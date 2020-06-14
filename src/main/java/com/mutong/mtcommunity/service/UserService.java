package com.mutong.mtcommunity.service;

import com.mutong.mtcommunity.mapper.UserMapper;
import com.mutong.mtcommunity.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import org.apache.maven.surefire.shade.org.apache.commons.lang3.StringUtils;
/**
 * @description:
 * @Author: gengchen.jing@yoyi.com.cn
 * @Date: 2020-06-12 9:58
 */
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper
    //用户注册
    public Map<String, Object> register(User user) {
        if (user == null) {
            throw new IllegalArgumentException("参数不能为空!");
        }
        Map<String, Object> map = new HashMap<>();
        if (StringUtils.isBlank(user.getUsername())) {
            map.put("usernameMsg", "账号不能为空!");
            return map;
        }
        if (StringUtils.isBlank(user.getPassword())) {
            map.put("passwordMsg", "密码不能为空!");
            return map;
        }
        if (StringUtils.isBlank(user.getEmail())) {
            map.put("emailMsg", "邮箱不能为空!");
            return map;
        }
        //验证账号

        userMapper
        User u = userMapper.selectByName(user.getUsername());
        if (u != null){
            map.put("usernameMsg", "该账号已存在!");
            return map;
        }
        // 验证邮箱
        u = userMapper.selectByEmail(user.getEmail());
        if (u != null) {
            map.put("emailMsg", "该邮箱已被注册!");
            return map;
        }

    }
}
