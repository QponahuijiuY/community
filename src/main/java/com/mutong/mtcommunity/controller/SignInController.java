package com.mutong.mtcommunity.controller;

import com.mutong.mtcommunity.dto.ResultTypeDTO;
import com.mutong.mtcommunity.enums.CustomizeErrorCode;
import com.mutong.mtcommunity.model.User;
import com.mutong.mtcommunity.service.SignInService;
import com.mutong.mtcommunity.utils.HostHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @description:
 * @Author: gengchen.jing@yoyi.com.cn
 * @Date: 2020-07-16 9:21
 */
@Controller
public class SignInController {
    @Resource
    private HostHolder hostHolder;
    @Resource
    private SignInService signInService;

    @ResponseBody
    @GetMapping("/signIn")
    public ResultTypeDTO signIn(HttpServletRequest request){
        User user = hostHolder.getUser();
        if(user == null){
            return new ResultTypeDTO().errorOf(CustomizeErrorCode.USER_NO_LOGIN);
        }
        ResultTypeDTO resultTypeDTO= signInService.signIn(user.getId());
        return resultTypeDTO;
    }
}
