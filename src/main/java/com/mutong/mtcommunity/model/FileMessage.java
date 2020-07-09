package com.mutong.mtcommunity.model;

import java.io.Serializable;

/**
 * @description:
 * @Author: gengchen.jing@yoyi.com.cn
 * @Date: 2020-07-08 18:28
 */
public class FileMessage implements Serializable {
    private Integer success;
    private String message;
    private String url;

    public FileMessage() {
    }

    public FileMessage(Integer success, String message, String url) {
        this.success = success;
        this.message = message;
        this.url = url;
    }

    public Integer getSuccess() {
        return success;
    }

    public void setSuccess(Integer success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
