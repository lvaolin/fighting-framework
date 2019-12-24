package com.dhy.lock;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

/**
 * 原子操作测试
 * @author lvaolin
 * @create 2019/12/24 7:56 PM
 */
public class AtomicTest02 {

    static int i=0;

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName()+":ready");
                for (int j = 0; j <10000 ; j++) {
                    i++;
                    System.out.println(Thread.currentThread().getName()+":"+i);
                }
                System.out.println(Thread.currentThread().getName()+":ok");
            }
        });

        t1.start();

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName()+":ready");
                for (int j = 0; j <10000 ; j++) {
                    i++;
                    System.out.println(Thread.currentThread().getName()+":"+i);
                }
                System.out.println(Thread.currentThread().getName()+":ok");

            }
        });
        t2.start();



    }


}
