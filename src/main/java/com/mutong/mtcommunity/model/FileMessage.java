package com.mutong.mtcommunity.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @description:
 * @Author: gengchen.jing@yoyi.com.cn
 * @Date: 2020-07-08 18:28
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileMessage implements Serializable {
    private Integer success;
    private String message;
    private String url;


}
