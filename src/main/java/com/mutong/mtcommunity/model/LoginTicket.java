package com.mutong.mtcommunity.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @description:
 * @Author: gengchen.jing@yoyi.com.cn
 * @Date: 2020-06-22 9:16
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginTicket {
    private Integer id;//主键
    private Integer userId;// 用户id
    private String ticket;// 登陆凭证
    private Integer status;// 登陆状态
    private Date expired;//失效时间


}
