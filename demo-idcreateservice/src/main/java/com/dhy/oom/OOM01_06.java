package com.dhy.oom;

import java.util.concurrent.TimeUnit;

/**
 *
 *  main函数所在线程可以设置为 守护线程吗？  不可以！
 *
 * @author lvaolin
 * @create 2019/12/26 4:24 PM
 */
public class OOM01_06 {


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

      thread.start();


      Thread.currentThread().setDaemon(true);
      System.out.println("main线程-- isDaemon:"+Thread.currentThread().isDaemon());


    }
}
