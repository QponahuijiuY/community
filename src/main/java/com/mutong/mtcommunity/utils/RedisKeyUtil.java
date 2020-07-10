package com.mutong.mtcommunity.utils;

/**
 * @description:
 * @Author: gengchen.jing@yoyi.com.cn
 * @Date: 2020-07-08 10:22
 */
public class RedisKeyUtil {

    //分割线
    private static final String SPLIT = ":";
    //最近访问的人
    private static final String PREFIX_LASTACTIVITY = "lastActivity";
    public static String getLastActivityKey(){
        return PREFIX_LASTACTIVITY + SPLIT ;
    }
}
