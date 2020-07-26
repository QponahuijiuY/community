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
    private int content;
    //已读1,未读0 ,删除 2
    private int status;
    private String topic;
    private Date createTime;
    private int entityId;
}
