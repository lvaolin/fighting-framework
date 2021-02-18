package com.dhy.oom;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 *  默认情况下 创建的线程都是 用户线程（非守护线程），但是可以设置为守护线程，
 *  默认情况下 JVM 后台的 GC线程都是守护线程，当用户线程全部退出后，GC线程也会退出
 *  整个jvm进程就退出了
 *
 * @author lvaolin
 * @create 2019/12/26 4:24 PM
 */
public class OOM01_03 {


    public static void main(String[] args){
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    System.out.println("sub线程--isDaemon:"+Thread.currentThread().isDaemon());
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        }).start();


        while (true){
            System.out.println("main线程--isDaemon:"+Thread.currentThread().isDaemon());
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


    }
}
