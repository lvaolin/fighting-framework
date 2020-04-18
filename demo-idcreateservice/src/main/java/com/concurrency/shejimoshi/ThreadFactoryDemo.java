package com.concurrency.shejimoshi;/**
 * Created by lvaolin on 17/10/30.
 */

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * ThreadFactory使用
 *
 * @author lvaolin
 * @create 17/10/30 下午1:28
 */
public class ThreadFactoryDemo {


    public static void main(String[] args) {

        ThreadFactory factory = Executors.defaultThreadFactory();
        Thread thread =factory.newThread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    System.out.println(System.currentTimeMillis());
                }

            }
        });

        thread.start();
    }

}
