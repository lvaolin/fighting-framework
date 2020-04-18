package com.dhy.demo.spring.mybatis.service;

import lombok.Data;

import java.util.Date;

@Data
public class SeataStoragePo {

    private  Integer id;
    private Double price;
    private Integer stock;
    private Date lastUpdateTime;

}
