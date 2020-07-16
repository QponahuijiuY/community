package com.mutong.mtcommunity.service;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.crypto.SecureUtil;
import com.mutong.mtcommunity.mapper.LoginTicketMapper;
import com.mutong.mtcommunity.mapper.UserMapper;
import com.mutong.mtcommunity.model.LoginTicket;
import com.mutong.mtcommunity.model.User;
import com.mutong.mtcommunity.utils.CommunityUtil;
import com.mutong.mtcommunity.utils.MailClient;
import com.mutong.mtcommunity.utils.RedisKeyUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.annotation.Resource;
import java.util.*;

import static com.mutong.mtcommunity.utils.CommunityConstant.*;

/**
 * @description:
 * @Author: gengchen.jing@yoyi.com.cn
 * @Date: 2020-06-12 9:58
 */
@Service
public class UserService extends RedisKeyUtil {

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
    @Resource
    private LoginTicketMapper loginTicketMapper;
    @Resource
    private RedisTemplate redisTemplate;
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
        //密码长度校验
        if (user.getPassword().length() <= 6 || user.getPassword().length() >= 15){
            map.put("passwordMsg","密码的长度需大于6位且小于15位");
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
        user.setAddress(null);
        user.setSignature(null);
        user.setLoginTime(new Date());
        user.setScore(0);
        userMapper.insertUser(user);

        //账号通过邮箱激活
        Context context = new Context();
        context.setVariable("email", user.getEmail());
        // http://localhost:8080/community/activation/101/code 激活链接
        String url = domain + contextPath + "/activation/" + user.getId() + "/" + user.getActivationCode();
        context.setVariable("url", url);
        String content = templateEngine.process("mail/activation", context);
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

        //生成登陆凭证
        LoginTicket loginTicket = new LoginTicket();
        loginTicket.setUserId(user.getId());
        loginTicket.setTicket(CommunityUtil.generateUUID());
        loginTicket.setStatus(0);
        loginTicket.setExpired(new Date(System.currentTimeMillis() + EXPIRED_SECONDS * 1000));
        loginTicketMapper.insertLoginTicket(loginTicket);
//        String redisKey = RedisKeyUtil.getTicketKey(loginTicket.getTicket());
//        redisTemplate.opsForValue().set(redisKey,loginTicket);
        map.put("ticket" ,loginTicket.getTicket());

        //修改登陆时间
        userMapper.updateLoginTime(user.getId(),new Date());
        return map;
    }

    public LoginTicket findLoginTicket(String ticket) {
        return loginTicketMapper.findLoginTicket(ticket);
    }

    public User findUserById(Integer id) {
        return userMapper.selectUserById(id);
    }
    public void logout(String ticket) {
        loginTicketMapper.updateStatus(ticket, 1);

    }

    public void updateUser(Integer id,String email,String nickname ,String address,String signature) {
//        User updateUser(@Param("id") Integer id, @Param("password")String password, @Param("headerUrl") String headerUrl, @Param("email") String email, @Param("nickname") String nickname, @Param("address") String address, @Param("signature") String signature, @Param("type") Integer type, @Param("modTime") Date modTime);

        userMapper.updateUser(id,null,null,email,nickname,address,signature,null,new Date());
    }


    public void updatePassword(int id, String pass) {
        User user = userMapper.selectUserById(id);
        String newpass = SecureUtil.md5(pass + user.getSalt())  ;
        userMapper.updatePassword(id,newpass);
    }

    public int updateHeaderUrl(int id, String url) {
       return userMapper.updateHeaderUrl(id,url);
    }

    public void updateLoginTime(int id, Date date) {
        userMapper.updateLoginTime(id,date);
    }
    public List<User> findUserByTime(int offset,int limit){
        return userMapper.selectUserByTime(offset,limit);
    }
}
