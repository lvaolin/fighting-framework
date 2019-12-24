package com.dhy.lock;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

/**
 * 原子操作测试
 * @author lvaolin
 * @create 2019/12/24 7:56 PM
 */
public class AtomicTest01 {

    static int i=0;

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch cdl = new CountDownLatch(2);

        CyclicBarrier cb = new CyclicBarrier(2);
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName()+":ready");
                try {
                    cb.await();
                    for (int j = 0; j <1000 ; j++) {
                        i++;
                        System.out.println(Thread.currentThread().getName()+":"+i);
                    }

                    cdl.countDown();
                    System.out.println(Thread.currentThread().getName()+":ok");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }
        });

        t1.start();

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName()+":ready");
                try {
                    cb.await();
                    for (int j = 0; j <1000 ; j++) {
                        i++;
                        System.out.println(Thread.currentThread().getName()+":"+i);
                    }

                    cdl.countDown();
                    System.out.println(Thread.currentThread().getName()+":ok");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }
        });
        t2.start();

        cdl.await();

        System.out.println(i);



    }


}
