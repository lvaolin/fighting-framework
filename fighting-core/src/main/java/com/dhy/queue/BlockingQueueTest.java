package com.dhy.queue;

import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Project fighting
 * @Description 主要用途描述
 * @Author lvaolin
 * @Date 2021/2/20 3:55 下午
 */
public class BlockingQueueTest {


    public static void main(String[] args) {

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    ExecutorService executorService = MyQueue.cachedExecutorService;
                    final AtomicInteger count = new AtomicInteger(0);
                    while (true){
                        String take = MyQueue.blockingQueue.take();

                        while (count.intValue()>500){
                            System.out.println("并发大于500时暂停");
                            TimeUnit.MILLISECONDS.sleep(500);
                        }
                        System.out.println("继续消费");
                        Future<?> submit = executorService.submit(new Runnable() {
                            @Override
                            public void run() {
                                count.incrementAndGet();
                                System.out.println("consumer：" + take);
                                try {
                                    TimeUnit.SECONDS.sleep(1);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                count.decrementAndGet();
                            }
                        });

                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();

        for (int i = 0; i <1000 ; i++) {
            try {
                MyQueue.blockingQueue.put(UUID.randomUUID().toString());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

//        try {
//            thread.join();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        System.out.println("end");

    }

}
