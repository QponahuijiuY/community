package com.mutong.mtcommunity.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @description:
 * @Author: gengchen.jing@yoyi.com.cn
 * @Date: 2020-07-08 10:22
 */
public class RedisKeyUtil {
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    //分割线
    private static final String SPLIT = ":";
    // collection key :每一个用户都对应一个key，当收藏的时候吧帖子id放到set集合里面，方面查询每一个用户收藏了多少帖子
    // collection:user:userId - postId
    private static final String PREFIX_COLLECTION_USER = "collection:user";
    // like key：每一个帖子都对应一个key，当用户点赞的时候，把用户id放到set集合里面
    //like:post:postId - userId
    private static final String PREFIX_LIKE_POST = "like:post";

    private static final String PREFIX_SIGN_IN_DAY = "sign:in:day";

    //每个用户收藏的key
    public static String getCollectionUserKey(int userId){
        return PREFIX_COLLECTION_USER + SPLIT + userId;
    }

    //每个帖子点赞的key
    public static String getLikePostKey(int postId){
        return PREFIX_LIKE_POST + SPLIT + postId;
    }

    public static String getSignInDay(Date day){
        String format = simpleDateFormat.format(day);
        return PREFIX_SIGN_IN_DAY + SPLIT + format;

    }

}
