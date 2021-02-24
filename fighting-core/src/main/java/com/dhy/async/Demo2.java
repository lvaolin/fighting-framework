package com.dhy.async;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

/**
 * @Project fighting
 * @Description 线程池执行任务,
 * @Author lvaolin
 * @Date 2021/2/24 5:26 下午
 */
public class Demo2 {
    public static void main(String[] args) {
        //这种可以显示出 线程分组和线程名称
       Thread thread =  new Thread(new ThreadGroup("lval"),new Runnable() {
            @Override
            public void run() {
                System.out.println("xxx");
                try {
                    TimeUnit.SECONDS.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"lvalTest");
        thread.start();

        //线程池中就无法显示线程分组和线程名称
        for (int i = 0; i <10 ; i++) {
            Executors.newFixedThreadPool(10).execute(thread);
        }

        //通过自定义ThreadFactory来实现 group和name的自定义
        for (int i = 0; i <10 ; i++) {
            Executors.newFixedThreadPool(10, new MyThreadFactory("tax","sb")).execute(thread);
        }


    }

}
