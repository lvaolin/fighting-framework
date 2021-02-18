package com.dhy.leetcode.sentinel;

import org.apache.commons.lang.math.RandomUtils;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {


    public static void main(String[] args) {


        for (int i = 0; i < 2; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        if (RateLimitFactory.pass) {
                            RateLimitFactory.requestCount.addAndGet(RandomUtils.nextInt(100));
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
