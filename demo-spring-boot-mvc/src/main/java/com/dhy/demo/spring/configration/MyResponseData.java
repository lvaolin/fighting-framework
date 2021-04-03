package com.dhy.demo.spring.configration;

import java.io.Serializable;
import java.util.HashMap;

/**
 * @Title MyResponseData
 * @Description
 * @Author lvaolin
 * @Date 2021/4/3 18:36
 **/
public class MyResponseData implements Serializable {
    private HashMap<String, Object> resultMap = new HashMap<String, Object>();
    public MyResponseData(Object bizData){
        resultMap.put("head",new Head("0","success"));
        resultMap.put("body",bizData);
    }

    public HashMap<String, Object> getResult(){
        return resultMap;
    }

    public static class Head{
        public Head(String code,String msg){
            this.code = code;
            this.msg = msg;
        }
        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        private String code;
        private String msg;
    }
}
