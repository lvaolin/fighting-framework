package com.dhy.lock;

import java.util.concurrent.CountDownLatch;

/**
 * 100个线程同时修改共享变量 i  ，每个线程进行 i++ 操作 1万次。
 * 非原子操作测试问题复现
 * @author lvaolin
 * @create 2019/12/24 7:56 PM
 */
public class AtomicTest02 {

    static int i=0;

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch cdl = new CountDownLatch(100);
        for (int j = 0; j <100 ; j++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    //System.out.println(Thread.currentThread().getName()+":ready");
                    for (int j = 0; j <10000 ; j++) {
                        i++;
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
