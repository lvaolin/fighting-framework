package com.dhy.lock;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 100个线程同时修改共享变量 i  ，每个线程进行 i++ 操作 1万次。
 * 使用ReadWriteLock获取类锁来同步不同线程对i的操作，使其串行化，最终达到 操作的原子性 的目的。
 * 原子操作测试
 * @author lvaolin
 * @create 2019/12/24 7:56 PM
 */
public class AtomicTest07 {
    static  ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    static  int i=0;

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch cdl = new CountDownLatch(100);
        for (int j = 0; j <100 ; j++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Lock lock = readWriteLock.writeLock();
                    lock.lock();
                    //System.out.println(Thread.currentThread().getName()+":ready");
                        for (int j = 0; j <10000 ; j++) {
                            i++;
                            System.out.println(Thread.currentThread().getName()+":"+i);
                        }
                        System.out.println(Thread.currentThread().getName()+":ok");
                        cdl.countDown();
                    lock.unlock();

                }
            }).start();
        }

        cdl.await();
        System.out.println("result:"+i);





    }


}
