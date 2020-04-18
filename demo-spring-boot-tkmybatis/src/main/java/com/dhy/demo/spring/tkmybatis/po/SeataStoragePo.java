package com.dhy.demo.spring.tkmybatis.po;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Table(name = "product")
public class SeataStoragePo {

    @Id
    private  Integer id;
    @Column(name = "price")
    private Double price;
    private Integer stock;
    @Column(name = "last_update_time")
    private Date lastUpdateTime;

}
