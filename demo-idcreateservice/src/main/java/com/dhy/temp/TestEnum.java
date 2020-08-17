package com.dhy.temp;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;

public class TestEnum {

    public static void main(String[] args) throws JsonProcessingException {
        BusinessException businessException = new BusinessException();
        businessException.setCode("1000");
        businessException.setMessage("警告-----");
        businessException.setType(Type.alert);

        Map<String, Object> errMap = new HashMap<String, Object>();

        errMap.put("code", businessException.getCode());

        errMap.put("message", businessException.getMessage());

        errMap.put("type", businessException.getType().name());
        //errMap.put("type", businessException.getType());

        System.out.println(JSON.toJSONString(errMap));

        ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper.writeValueAsString(errMap));
    }




    static class BusinessException extends RuntimeException{
        public String code;
        public String message;
        public Type type;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        @Override
        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public Type getType() {
            return type;
        }

        public void setType(Type type) {
            this.type = type;
        }



    }

    public static enum Type{
        error,		//对应于前端的红叉提示
        warning,	//对应于前端的黄叹号提示
        alert,		//对应于前端的弹出框+详情的提示方式
        notip		//对应于前端不弹出错误提示
    }
}
