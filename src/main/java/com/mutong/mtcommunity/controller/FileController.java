package com.mutong.mtcommunity.controller;


import com.mutong.mtcommunity.model.FileMessage;
import com.mutong.mtcommunity.model.User;
import com.mutong.mtcommunity.provide.AliCloudProvide;
import com.mutong.mtcommunity.service.UserService;
import com.mutong.mtcommunity.utils.HostHolder;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @description: 文件传输controller
 * @Author: gengchen.jing@yoyi.com.cn
 * @Date: 2020-07-08 15:25
 */
@Controller
public class FileController {
    private static final Logger logger = LoggerFactory.getLogger(FileController.class);

    @Resource
    private AliCloudProvide aliCloudProvide;
    @Resource
    private HostHolder hostHolder;
    @Resource
    private UserService userService;

    @ResponseBody
    @RequestMapping(value = "/file/upload", method = RequestMethod.POST)
    public FileMessage uploadImg(HttpServletRequest request, HttpServletResponse response) throws IOException {
        MultipartRequest multipartRequest = (MultipartRequest) request;
        MultipartFile file = multipartRequest.getFile("editormd-image-file");
        String url = aliCloudProvide.uploadFile(file.getInputStream(), file.getContentType(), file.getOriginalFilename());
        FileMessage fileMessage = new FileMessage();
        fileMessage.setMessage("上传成功");
        fileMessage.setSuccess(1);
        fileMessage.setUrl(url);
        return fileMessage;
    }

    @ResponseBody
    @RequestMapping(value = "/file/uploadHeaderUrl", method = RequestMethod.POST)
    public Map<String, Object> uploadHeaderUrl(HttpServletRequest request, @RequestParam("file") MultipartFile file) throws IOException {
        Map<String, Object> map = new HashMap<>();
        // String uploadDir = "F:/kdgc_project/Student_Attendence_Application/src/main/webapp/resources/upload/";

            User user = hostHolder.getUser();
            if (user == null) {
                map.put("status", 500);
                map.put("msg", "请登录后再上传图片哦");
                map.put("url", null);
                return map;
            }
            String url = aliCloudProvide.uploadFile(file.getInputStream(), file.getContentType(), getFileName(file.getContentType()));
            map.put("status",0);
            map.put("msg","头像上传成功");
            map.put("url",url);

            return map;

    }


    public String getFileName(String contentType) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String timeStr = sdf.format(new Date());
        String[] ss = contentType.split("/");
        String str = RandomStringUtils.random(5,
                "qwertyuiopasdfghjklzxcvbnm");
        String name = timeStr + "_" +str+"."+ss[1];
        return name;
    }
}
