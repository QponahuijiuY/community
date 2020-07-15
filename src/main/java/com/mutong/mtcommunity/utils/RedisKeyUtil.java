package com.mutong.mtcommunity.utils;

/**
 * @description:
 * @Author: gengchen.jing@yoyi.com.cn
 * @Date: 2020-07-08 10:22
 */
public class RedisKeyUtil {

    //分割线
    private static final String SPLIT = ":";
    // redis key
    private static final String PREFIX_COLLECTION_USER = "collection:user";


    //每个用户收藏的key
    public static String getCollectionUserKey(int userId){
        return PREFIX_COLLECTION_USER + SPLIT + userId;
    }

}
