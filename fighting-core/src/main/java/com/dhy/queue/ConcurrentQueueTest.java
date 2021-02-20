package com.dhy.queue;

import scala.xml.Atom;

import java.sql.Time;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Project fighting
 * @Description 主要用途描述
 * @Author lvaolin
 * @Date 2021/2/20 3:55 下午
 */
public class ConcurrentQueueTest {


    public static void main(String[] args) {

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    ExecutorService executorService = MyQueue.cachedExecutorService;
                    final AtomicInteger concurrentCount = new AtomicInteger(0);
                    while (true){
                        String bizData = MyQueue.queue.poll();
                        while (bizData==null){
                            System.out.println("没有任务，休眠1s");
                            TimeUnit.MILLISECONDS.sleep(1000);
                            bizData = MyQueue.queue.poll();
                        }

                        while (concurrentCount.intValue()>500){
                            System.out.println("并发大于500时暂停");
                            TimeUnit.MILLISECONDS.sleep(500);
                        }
                        System.out.println("继续消费");
                        executorService.submit(new MyThread(bizData,concurrentCount));

                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();

        for (int i = 0; i <100000 ; i++) {
            while (!MyQueue.queue.offer(UUID.randomUUID().toString())){
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }

        System.out.println("end");

    }

    static class MyThread implements Runnable{

        private String bizData;
        //并发计数
        private AtomicInteger concurrentCount;
        public MyThread(String bizData,AtomicInteger concurrentCount){
            this.bizData = bizData;
            this.concurrentCount = concurrentCount;
        }
        @Override
        public void run() {
            concurrentCount.incrementAndGet();
            System.out.println("consumer：" + bizData);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            concurrentCount.decrementAndGet();
        }
    }

}
