package com.mutong.mtcommunity.enums;

/**
 * @description:
 * @Author: gengchen.jing@yoyi.com.cn
 * @Date: 2020-07-16 9:33
 */
public enum  ScoreType {
    SIGN_IN(10,"签到"),
    COMMENT(5,"被评论"),
    LIKE(2,"被点赞" );
    private int type;
    private String message;

    ScoreType(int type,String message){
        this.type = type;
        this.message = message;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
