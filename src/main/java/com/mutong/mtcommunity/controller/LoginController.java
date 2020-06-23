package com.mutong.mtcommunity.controller;

import com.google.code.kaptcha.Producer;
import com.mutong.mtcommunity.model.User;
import com.mutong.mtcommunity.service.UserService;
import com.mutong.mtcommunity.utils.CommunityConstant;
import org.apache.maven.surefire.shade.org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.imageio.ImageIO;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;


/**
 * @description:
 * @Author: gengchen.jing@yoyi.com.cn
 * @Date: 2020-06-12 10:49
 */
@Controller
public class LoginController implements CommunityConstant {
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired(required=true)
    private UserService userService;

    @Autowired
    private Producer kaptchaProducer;
    @Value("${server.servlet.context-path}")
    private String contextPath;

    /**
     * 跳转到注册页面
     * @return 注册页面
     */
    @GetMapping("/reg")
    public String getRegisterPage() {
        return "user/reg";
    }

    /**
     * 跳转到登陆页面
     * @return 登陆页面
     */
    @GetMapping("/login")
    public String getLoginPage() {
        return "user/login";
    }

    /**
     * 登陆
     * @param email 邮箱
     * @param password 密码
     * @param code 验证码
     * @param model 视图
     * @param response 响应
     * @param session session信息
     * @return
     */
    @PostMapping("/login")
    public String login(String email, String password, String code, Model model , HttpServletResponse response,HttpSession session){
        //从session里面获取验证码，校验验证码
        String kaptcha = (String) session.getAttribute("kaptcha");
        if (StringUtils.isBlank(code) || StringUtils.isBlank(kaptcha) || !kaptcha.equalsIgnoreCase(code)){
            model.addAttribute("codeMsg","验证码不正确");
            return "user/login";
        }
        //登陆逻辑
        Map<String, Object> map = userService.login(email,password,code);
        if (map.containsKey("ticket")){
            Cookie cookie = new Cookie("ticket",map.get("ticket").toString());
            cookie.setPath(contextPath);
            cookie.setMaxAge(EXPIRED_SECONDS);
            response.addCookie(cookie);
            return "redirect:/";
        }else {
            model.addAttribute("emailMsg",map.get("emailMsg"));
            model.addAttribute("passwordMsg",map.get("passwordMsg"));
            return "user/login";
        }

    }

    /**
     * 注册
     * @param model 视图，前端获取视图里面的信息
     * @param user 用户对象
     * @return 注册成功返回到激活成功页面，注册失败返回到注册页面，并且页面显示失败原因
     */
    @PostMapping("/register")
    public String register(Model model, User user,String code,HttpSession session){
        //从session里面获取验证码
        String kaptcha = (String) session.getAttribute("kaptcha");
        if (StringUtils.isBlank(code) || StringUtils.isBlank(kaptcha) || !kaptcha.equalsIgnoreCase(code)){
            model.addAttribute("codeMsg","验证码不正确");
            return "user/reg";
        }
        Map<String, Object> map = userService.register(user);
        if (map == null || map.isEmpty()) {
            model.addAttribute("msg", "注册成功,我们已经向您的邮箱发送了一封激活邮件,请尽快激活!");
            model.addAttribute("target", "/");
            return "user/operate-result";
        } else {
            model.addAttribute("nicknameMsg", map.get("usernameMsg"));
            model.addAttribute("passwordMsg", map.get("passwordMsg"));
            model.addAttribute("emailMsg", map.get("emailMsg"));
            return "user/reg";
        }
    }

    /**
     * 激活账号
     * @param model 视图，前端从视图里面获取信息
     * @param userId 用户id
     * @param code 激活码
     * @return 激活成功 返回到注册成功页面，失败返回到失败页面
     */
    @GetMapping(path = "/activation/{userId}/{code}")
    public String activation(Model model, @PathVariable("userId") int userId, @PathVariable("code") String code) {
        int result = userService.activation(userId, code);
        if (result == ACTIVATION_SUCCESS) {
            model.addAttribute("msg", "激活成功,您的账号已经可以正常使用了!");
            model.addAttribute("target", "/login");
        } else if (result == ACTIVATION_REPEAT) {
            model.addAttribute("msg", "无效操作,该账号已经激活过了!");
            model.addAttribute("target", "/index");
        } else {
            model.addAttribute("msg", "激活失败,您提供的激活码不正确!");
            model.addAttribute("target", "/index");
        }
        return "user/operate-result";
    }

    /**
     *
     * @param response
     */
    @GetMapping(path = "/kaptcha")
    public void getKaptcha(HttpServletResponse response, HttpSession session) {
        // 生成验证码
        String text = kaptchaProducer.createText();
        System.out.println(text);
        BufferedImage image = kaptchaProducer.createImage(text);
        session.setAttribute("kaptcha", text);
        response.setContentType("image/png");
        try {
            OutputStream os = response.getOutputStream();
            ImageIO.write(image, "png", os);
        } catch (IOException e) {
            logger.error("响应验证码失败:" + e.getMessage());
        }
    }

    @GetMapping("/logout")
    public String logout(@CookieValue("ticket") String ticket){
        userService.logout(ticket);
        return "redirect:/login";
    }
}
