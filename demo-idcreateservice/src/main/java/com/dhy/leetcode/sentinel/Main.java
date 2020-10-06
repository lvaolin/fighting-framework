package com.dhy.leetcode.sentinel;

import org.apache.commons.lang.math.RandomUtils;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    //当前窗口请求数量
    public static  final AtomicInteger requestCount = new AtomicInteger();
    //能否通过的标志
    public static volatile boolean pass = true;

    public static void main(String[] args) {
        //定时刷新窗口数据
        Executors.newScheduledThreadPool(1).scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                if (RateLimitFactory.isOverLimit("http://xxx.com/a/b", requestCount.intValue())) {
                    pass = false;
                } else {
                    pass = true;
                }
                requestCount.set(0);
            }
        }, 0L, 1000 / RateLimitFactory.defaultWinSize, TimeUnit.MILLISECONDS);

        for (int i = 0; i < 2; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        if (pass) {
                            requestCount.addAndGet(RandomUtils.nextInt(100));
                            System.out.println(Thread.currentThread().getName()+"  passed");
                        }else{
                            System.out.println(Thread.currentThread().getName()+" no passed");
                        }

                        try {
                            TimeUnit.MILLISECONDS.sleep(RandomUtils.nextInt(1000));
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }

                }
            }).start();
        }

    }
}
