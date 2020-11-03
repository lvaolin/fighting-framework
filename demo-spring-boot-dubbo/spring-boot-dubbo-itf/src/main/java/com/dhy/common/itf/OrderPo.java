package com.dhy.common.itf;

import lombok.Data;

import java.util.Date;

@Data
public class OrderPo {

    private  Integer id;
    private Double price;
    private Integer stock;
    private Date lastUpdateTime;

}
