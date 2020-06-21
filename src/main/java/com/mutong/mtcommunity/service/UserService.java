package com.mutong.mtcommunity.service;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.crypto.SecureUtil;
import com.mutong.mtcommunity.mapper.UserMapper;
import com.mutong.mtcommunity.model.User;
import com.mutong.mtcommunity.utils.MailClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static com.mutong.mtcommunity.utils.CommunityConstant.*;

/**
 * @description:
 * @Author: gengchen.jing@yoyi.com.cn
 * @Date: 2020-06-12 9:58
 */
@Service
public class UserService {

    @Resource
    private UserMapper userMapper;
    @Value("${community.path.domain}")
    private String domain;
    @Value("${server.servlet.context-path}")
    private String contextPath;
    @Resource
    private TemplateEngine templateEngine;
    @Resource
    private MailClient mailClient;

    /**
     * 用户注册
     * @param user
     * @return
     */
    public Map<String, Object> register(User user) {

        Map<String, Object> map = new HashMap<>();
        // 验证邮箱
        User u = userMapper.selectUserByEmail(user.getEmail());
        if (ObjectUtil.isNotEmpty(u) || ObjectUtil.isNotNull(u)) {
            map.put("emailMsg", "该邮箱已被注册!");
            return map;
        }
        //注册
        user.setSalt(IdUtil.simpleUUID().substring(0, 5));
        user.setPassword(SecureUtil.md5(user.getPassword() + user.getSalt()));
        user.setType(0);
        user.setStatus(0);
        user.setActivationCode(IdUtil.simpleUUID());
        user.setHeaderUrl(String.format("http://images.nowcoder.com/head/%dt.png", new Random().nextInt(1000)));
        user.setCreateTime(new Date());
        user.setModTime(new Date());
        userMapper.insertUser(user);

        //账号通过邮箱激活
        Context context = new Context();
        context.setVariable("email", user.getEmail());
        // http://localhost:8080/community/activation/101/code 激活链接
        String url = domain + contextPath + "/activation/" + user.getId() + "/" + user.getActivationCode();
        context.setVariable("url", url);
        String content = templateEngine.process("/mail/activation", context);
        mailClient.sendMail(user.getEmail(), "激活您的木同社区账号", content);
        return map;
    }

    /**
     * 激活账号
     * @param userId
     * @param code
     * @return
     */
    public int activation(int userId, String code) {
        User user = userMapper.selectUserById(userId);
        if (user.getStatus() == 1) {
            return ACTIVATION_REPEAT;
        } else if (user.getActivationCode().equals(code)) {
            userMapper.updateStatus(userId, 1);
            return ACTIVATION_SUCCESS;
        } else {
            return ACTIVATION_FAILURE;
        }
    }

    public Map<String, Object> login(String email, String password, String code) {
        Map<String,Object> map = new HashMap<>();
        //根据邮箱验证密码
        User user = userMapper.selectUserByEmail(email);
        if (ObjectUtil.isNull(user) || ObjectUtil.isEmpty(user)){
            map.put("emailMsg","邮箱未注册，请您注册~");
            return map;
        }
        if (user.getStatus() == 0){
            map.put("emailMsg","邮箱未激活");
        }
        //验证密码
        password = SecureUtil.md5(password + user.getSalt());
        if (!user.getPassword().equals(password)){
            map.put("passwordMsg","密码不正确");
            return map;
        }

        return map;
    }
}
