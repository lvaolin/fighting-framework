package com.dhy.mlife.billingservice.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class MyResponseDataXML<T> implements Serializable {
    private Head head = new Head("0", "success");
    private T body;

    public MyResponseDataXML(T bizData) {
        this.body = bizData;
    }

    public MyResponseDataXML(String code, String msg) {
        this.head = new Head(code, msg);
    }

    public static class Head {
        private String code;
        private String msg;

        public Head(String code, String msg) {
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
    }
}