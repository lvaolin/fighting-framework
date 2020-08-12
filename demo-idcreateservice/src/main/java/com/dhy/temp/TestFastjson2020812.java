package com.dhy.temp;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.time.LocalDate;
import java.util.Date;

public class TestFastjson2020812 {

    public static void main(String[] args) {
        Long x = JSON.parseObject("300017", Long.class);
        LocalDate now = LocalDate.now();
        System.out.println(now.getDayOfYear());
        LocalDate of = LocalDate.of(2020, 7, 31);
        System.out.println(of.getDayOfYear());
    }

    static int queryDay(Date date){
        return date.getDay();
    }

}
