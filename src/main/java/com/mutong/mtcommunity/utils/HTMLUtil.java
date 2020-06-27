package com.mutong.mtcommunity.utils;


import org.apache.commons.text.StringEscapeUtils;

/**
 * @description: html转换工具
 * @Author: gengchen.jing@yoyi.com.cn
 * @Date: 2020-06-26 9:19
 */
public class HTMLUtil {
    public static void main(String[] args) {
        String func = func("<p>啊</p><p><pre lay-lang=\"Java\">User user = hostHolder.getUser();\n" +
                "        Post post = new Post();\n" +
                "        post.setUserId(user.getId());\n" +
                "        post.setSpecialColumn(specialColumn);\n" +
                "        post.setContent(content);\n" +
                "        post.setTitle(titile);</pre><br></p><p><br></p><p><b>啊</b></p><p><b><br></b></p><p><b><img src=\"http://localhost:8080/layui/images/face/15.gif\" alt=\"[生病]\">难受啊<br></b></p>");
        System.out.println(func);
    }
    public static String func(String content){
        return StringEscapeUtils.unescapeHtml4(content);
    }
}
