package com.dhy.base;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * @author lvaolin
 * @create 2019/10/17 8:10 PM
 */
public class CalendarTest {
    public static void main(String[] args) throws InterruptedException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        calendar.set(2018,1,1,0,0,0);
        calendar.set(Calendar.DAY_OF_MONTH,1);//1:本月第一天
        String beginTime= sdf.format(calendar.getTime());
        calendar.add(Calendar.MONTH,1);//加1个月
        //calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        String engTime= sdf.format(calendar.getTime());
        System.out.println(beginTime);
        System.out.println(engTime);
        Thread.sleep(1);
        Thread.yield();
        Thread.currentThread().stop();
        engTime.wait();
        engTime.notify();

    }
}
