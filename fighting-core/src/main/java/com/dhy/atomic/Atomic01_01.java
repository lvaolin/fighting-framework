package com.dhy.atomic;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 100个线程同时修改共享变量 i  ，每个线程进行 i++ 操作 1万次。
 * 非原子操作测试问题复现
 * @author lvaolin
 * @create 2019/12/24 7:56 PM
 */
public class Atomic01_01 {

    static AtomicInteger i=new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch cdl = new CountDownLatch(100);
        for (int j = 0; j <100 ; j++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    //System.out.println(Thread.currentThread().getName()+":ready");
                    for (int j = 0; j <10000 ; j++) {
                        i.addAndGet(1);
                        i.getAndSet(1);
                        i.getAndIncrement();
                        System.out.println(Thread.currentThread().getName()+":"+i);
                    }
                    System.out.println(Thread.currentThread().getName()+":ok");
                    cdl.countDown();
                }
            }).start();
        }

        cdl.await();
        System.out.println("result:"+i);





    }


}
