package com.dhy.base;

import java.time.LocalDate;

/**
 * @author lvaolin
 * @create 2019/10/17 5:28 PM
 */
public class LocalDateTest {
    public static void main(String[] args) {
        //当前会计月
        LocalDate dateNow = LocalDate.now();
        System.out.println(dateNow.toString());
        String[] yearMonth = "2018,1".split(",");
        LocalDate datePc = LocalDate.of(Integer.parseInt(yearMonth[0]), Integer.parseInt(yearMonth[1]), dateNow.getDayOfMonth());
        while (!dateNow.isEqual(datePc)){
            System.out.println("dateNow.isAfter(datePc)="+dateNow.isAfter(datePc));
            System.out.println(datePc.toString());
            System.out.println("false");
            datePc = datePc.plusMonths(1);
        }
        System.out.println("dateNow.isAfter(datePc)="+dateNow.isAfter(datePc));
        System.out.println(datePc.toString());
        System.out.println("true");
    }
}
