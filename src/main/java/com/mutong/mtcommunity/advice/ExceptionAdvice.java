package com.mutong.mtcommunity.advice;

import com.mutong.mtcommunity.utils.CommunityUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @description:
 * @Author: gengchen.jing@yoyi.com.cn
 * @Date: 2020-06-28 9:09
 */
@ControllerAdvice(annotations = Controller.class)
public class ExceptionAdvice {
    private static final Logger logger = LoggerFactory.getLogger(ExceptionAdvice.class);

    public ExceptionAdvice(){
        System.out.println("ExceptionAdvice");
    }
    @ExceptionHandler({Exception.class})
    public void handleException(Exception e,
                                        HttpServletRequest request,
                                        HttpServletResponse response) throws IOException {
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        logger.error("服务器发送异常: "+ e.getMessage());
        for (StackTraceElement element : e.getStackTrace()){
            logger.error(element.toString());
        }
        String xRequestedWith = request.getHeader("x-requested-with");
        if ("XMLHttpRequest".equals(xRequestedWith)){
            response.setContentType("application/plain;charset=utf-8");
            PrintWriter writer = response.getWriter();
            writer.write(CommunityUtil.getJSONString(1,"服务器异常!"));
        }else {
            System.out.println(request.getContextPath());
            response.sendRedirect(request.getContextPath()+"/errer");
        }
//        return new ModelAndView("404");
    }
}