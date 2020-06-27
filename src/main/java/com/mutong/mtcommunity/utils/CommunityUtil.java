package com.mutong.mtcommunity.utils;

import com.alibaba.fastjson.JSONObject;
import org.apache.maven.surefire.shade.org.apache.commons.lang3.StringUtils;
import org.springframework.util.DigestUtils;

import java.util.Map;
import java.util.UUID;

/**
 * @description: 字符串util
 * @Author: gengchen.jing@yoyi.com.cn
 * @Date: 2020-06-15 17:27
 */

public class CommunityUtil {// 生成随机字符串
    public static String generateUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    /**
     * md5加密
     * @param key 用户密码
     * @return 加密后的算法
     */
    public static String md5(String key) {
        if (StringUtils.isBlank(key)) {
            return null;
        }
        return DigestUtils.md5DigestAsHex(key.getBytes());
    }

    public static String getJSONString(int code , String msg, Map<String , Object> map){
        JSONObject json = new JSONObject();
        json.put("code" , code);
        json.put("msg" , msg);
        if (map != null){
            for (String key : map.keySet()){
                json.put(key,map.get(key));
            }
        }
        return json.toJSONString();
    }

    public static String getJSONString(int code,String msg){
        return getJSONString(code, msg, null);

    }
    public static String getJSONString(int code){
        return getJSONString(code, null, null);
    }






}
