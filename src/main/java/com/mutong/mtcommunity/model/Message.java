package com.mutong.mtcommunity.model;

import lombok.Data;

import java.util.Date;

/**
 * @description:
 * @Author: gengchen.jing@yoyi.com.cn
 * @Date: 2020-07-25 8:45
 */
@Data
public class Message {
    private int id;
    private int fromId;
    private int toId;
    private String conversationId;
    private String content;
    //已读,未读,删除
    private int status;
    private Date createTime;
}
