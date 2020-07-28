package com.mutong.mtcommunity.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @description:
 * @Author: gengchen.jing@yoyi.com.cn
 * @Date: 2020-06-11 16:58
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private int id; //id
    private String password;
    private String salt; //password + salt
    private String email; //email
    private int type; //0普通 1管理员
    private int status; //0 未激活
    private String activationCode; //激活码
    private String headerUrl; //头像地址
    private Date createTime; // 创建时间
    private String nickname; //昵称
    private Date modTime; // 修改时间
    private String address; //城市
    private String signature;//个性签名
    private Date loginTime;//登陆时间
    private int score;//分数



}
