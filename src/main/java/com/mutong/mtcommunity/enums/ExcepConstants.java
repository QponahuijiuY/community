package com.mutong.mtcommunity.enums;

public enum ExcepConstants {
    IO_ERROR(40001,"验证码加载异常异常，请联系管理员～"),
    CAPTCHA_EXPIRE(40002,"验证码已经失效啦，请刷新重试～"),
    CAPTCHA_ERROR(40003,"验证码输入错误啦，请刷新重试～"),
    PHONE_UN_REGISTER(40004,"手机号未注册，点击注册按钮赶快注册吧～"),
    EMAIL_UN_REGISTER(40005,"邮箱未注册，点击注册按钮赶快注册吧～"),
    PASSWORD_ERROR(40006,"密码输入有误，请刷新重试～"),
    CODE_EXPIRE(40007,"验证码超时，请点击按钮重新发送"),
    REGISTER_ERROR(40008,"系统维护中，注册失败，请联系管理员～"),
    CODE_ERROR(40007,"验证码输入有误或者超时，请点击按钮重新发送"),
    SMS_SEND_ERROR(40008,"验证码发送失败，请确认提交信息是否正确"),
    REG_PASSWORD_ERROR(40009,"密码必须包含字母和数字，且长度为[6,16]"),
    PHONE_UNIPUE_ERROR(40010,"该手机号已经注册了，请换一个手机号注册或者使用邮箱注册，或者找回密码～"),
    EMAIL_UNIPUE_ERROR(40011,"该邮箱已经注册了，请换一个邮箱注册或者使用手机注册，或者找回密码～"),
    LOGIN_ERROR(40012,"系统维护中，登陆失败，请联系管理员～"),
    USER_NOT_FOUNT(40013,"用户不存在"),
    NEWS_ERROR(40014,"获取咨询信息有误"),


    ;

    private int code;
    private String msg;

    ExcepConstants(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }


}

