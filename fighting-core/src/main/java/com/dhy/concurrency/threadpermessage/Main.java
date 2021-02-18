package com.dhy.concurrency.threadpermessage;/**
 * Created by lvaolin on 17/11/3.
 */

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * 测试类
 *
 * @author lvaolin
 * @create 17/11/3 下午1:16
 */
public class Main {

    public static void main(String[] args) throws InterruptedException {
        String message = "消息";
        int count = 50000;
        CountDownLatch countDownLatch1 = new CountDownLatch(count);
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(10);
        Host host = new Host(executorService);
        try {
            long starttime = System.currentTimeMillis();
            for (int i = 0; i <count ; i++) {
                host.printRequest(countDownLatch1,message+i);
            }

            countDownLatch1.await();
            System.out.println("1用时："+(System.currentTimeMillis()-starttime)/1000);

            CountDownLatch countDownLatch2 = new CountDownLatch(count);
            starttime = System.currentTimeMillis();
            for (int i = 0; i <count ; i++) {
                host.printRequestUsePool(countDownLatch2,message+i);
            }
            countDownLatch2.await();
            System.out.println("2用时："+(System.currentTimeMillis()-starttime)/1000);
        }finally {
            executorService.shutdown();
        }






    }
}
