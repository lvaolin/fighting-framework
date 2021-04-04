package com.dhy.demo.spring.springdatajpa.po;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "product")
public class SeataStoragePo {

    @Id
    private  Integer id;
    @Column(name = "price")
    private Double price;
    private Integer stock;
    @Column(name = "last_update_time")
    private Date lastUpdateTime;
    //版本号 行并发控制使用
    @Version
    private  Integer version;


}
