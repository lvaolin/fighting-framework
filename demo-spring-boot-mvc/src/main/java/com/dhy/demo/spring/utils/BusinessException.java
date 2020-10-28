package com.dhy.demo.spring.utils;

import lombok.Data;

@Data
public class BusinessException  extends RuntimeException{

    private String code;
    private String msg;

    public BusinessException(String code,String msg){
        super(msg);
        this.code = code;
        this.msg = msg;
    }

}
