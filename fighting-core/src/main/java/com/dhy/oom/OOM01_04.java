package com.dhy.oom;

import java.util.concurrent.TimeUnit;

/**
 *  只要有一个用户线程没有退出，则JVM后台的GC相关守护线程就不会退出，整个JVM进程就不会退出
 *
 * @author lvaolin
 * @create 2019/12/26 4:24 PM
 */
public class OOM01_04 {


    public static void main(String[] args){
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    System.out.println("sub线程不退出--isDaemon:"+Thread.currentThread().isDaemon());
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        }).start();


        System.out.println("main线程--退出");


    }
}
