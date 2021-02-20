package com.dhy.disruptor;

import java.util.Date;

/**
 * @Project fighting
 * @Description 承载业务数据的DTO
 * @Author lvaolin
 * @Date 2021/2/20 2:11 下午
 */
public class MyDto {
    private int id;
    private String name;

    @Override
    public String toString() {
        return "MyDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", ts=" + ts +
                '}';
    }

    private Date ts;

    public MyDto(int id) {
        this.id = id;
    }
}
