package com.dhy.oom;

import java.util.concurrent.TimeUnit;

/**
 *  设置一个守护线程
 *
 *  当没有用户线程时，所有的守护线程都会退出---
 *
 * @author lvaolin
 * @create 2019/12/26 4:24 PM
 */
public class OOM01_05 {


    public static void main(String[] args){
      Thread thread =   new Thread(new Runnable() {
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
        });

      thread.setDaemon(true);
      thread.start();


      System.out.println("main线程--退出");


    }
}
