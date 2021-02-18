package com.dhy.lock.readwritelock;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;

public class TimeTest {

    public static void main(String[] args) throws InterruptedException {
        long defaultExpire = 20*60*1000;    //默认1小时失效-》20分钟
        Long nowMillis = Calendar.getInstance().getTimeInMillis();
        long expire = nowMillis + defaultExpire;
        System.out.println(nowMillis);
        System.out.println(expire);
        TimeUnit.SECONDS.sleep(60);
        System.out.println(Calendar.getInstance().getTimeInMillis());
        System.out.println(expire);

    }
}
