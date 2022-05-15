package com.dhy.mlife.common.core;

import lombok.Data;

import java.io.Serializable;

@Data
public class MyResponseData<T> implements Serializable {
    private Head head = new Head("0", "success");
    private T body;

    public MyResponseData(T bizData) {
        this.body = bizData;
    }

    public MyResponseData(String code, String msg) {
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