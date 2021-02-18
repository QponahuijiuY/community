package com.mutong.mtcommunity.utils;


import com.mutong.mtcommunity.enums.ExcepConstants;

public class CommonResult<T> {
    private Integer code;
    private String msg;
    private T data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    private static final int SUCCESS_CODE = 200;
    private static final int FAIL_CODE = 500;

    private static final String SUCCESS_MSG = "success";
    private static final String FAIL_MSG = "fail";
    public CommonResult(){

    }
    public CommonResult(Integer code, String msg, T data){
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
    public CommonResult(Integer code, String msg){
        this(code,msg,null);
    }

    public CommonResult success(T data){
        return new CommonResult(SUCCESS_CODE,SUCCESS_MSG,data);
    }
    public CommonResult success(){
        return new CommonResult(SUCCESS_CODE,SUCCESS_MSG);
    }

    public CommonResult error(T data){
        return new CommonResult(FAIL_CODE,FAIL_MSG,data);
    }
    public CommonResult error(int code,String msg){
        return new CommonResult(code,msg,null);
    }
    public CommonResult error(ExcepConstants e){
        return new CommonResult(e.getCode(),e.getMsg());
    }


}