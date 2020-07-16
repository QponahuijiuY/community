package com.mutong.mtcommunity.dto;

import com.mutong.mtcommunity.enums.CustomizeErrorCode;

import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @Author: gengchen.jing@yoyi.com.cn
 * @Date: 2020-07-16 9:24
 */
public class ResultTypeDTO {

    private String code;

    private String message;

    private Map<String,Object> extend=new HashMap<>();

    public ResultTypeDTO errorOf(CustomizeErrorCode customizeErrorCode){
        ResultTypeDTO resultTypeDTO = new ResultTypeDTO();
        resultTypeDTO.setCode(customizeErrorCode.getCode());
        resultTypeDTO.setMessage(customizeErrorCode.getMessage());
        return resultTypeDTO;
    }

    public ResultTypeDTO errorOf(String message){
        ResultTypeDTO resultTypeDTO = new ResultTypeDTO();
        resultTypeDTO.setCode("-1");
        resultTypeDTO.setMessage(message);
        return resultTypeDTO;
    }
    public ResultTypeDTO okOf(){
        ResultTypeDTO resultTypeDTO = new ResultTypeDTO();
        resultTypeDTO.setMessage(CustomizeErrorCode.OK.getMessage());
        resultTypeDTO.setCode(CustomizeErrorCode.OK.getCode());
        return resultTypeDTO;
    }

    public Map<String, Object> getExtend() {
        return extend;
    }

    public void setExtend(Map<String, Object> extend) {
        this.extend = extend;
    }

    public ResultTypeDTO addMsg(String key, Object value){
        this.extend.put(key,value);
        return this;
    }
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ResultTypeDTO{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", extend=" + extend +
                '}';
    }
}
