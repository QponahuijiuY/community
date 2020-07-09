package com.mutong.mtcommunity.controller;


import com.mutong.mtcommunity.model.FileMessage;
import com.mutong.mtcommunity.provide.AliCloudProvide;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @description:
 * @Author: gengchen.jing@yoyi.com.cn
 * @Date: 2020-07-08 15:25
 */
@Controller
public class FileController {
    private static final Logger logger = LoggerFactory.getLogger(FileController.class);

    @Resource
    private AliCloudProvide aliCloudProvide;
    @ResponseBody
    @RequestMapping(value = "/file/upload",method = RequestMethod.POST)
    public FileMessage uploadImg(HttpServletRequest request, HttpServletResponse response) throws IOException {
        MultipartRequest multipartRequest= (MultipartRequest) request;
        MultipartFile file = multipartRequest.getFile("editormd-image-file");
        String url = aliCloudProvide.UploadFile(file.getInputStream(),file.getContentType(),file.getOriginalFilename());
        FileMessage fileMessage = new FileMessage();
        fileMessage.setMessage("上传成功");
        fileMessage.setSuccess(1);
        fileMessage.setUrl(url);
        return fileMessage;
    }
}
