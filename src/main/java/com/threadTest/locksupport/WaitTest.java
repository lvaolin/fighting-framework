package com.threadTest.locksupport;/**
 * Created by lvaolin on 17/10/19.
 */

import java.util.concurrent.TimeUnit;

/**
 * @author lvaolin
 * @create 17/10/19 上午9:00
 */
public class WaitTest {


    public static void main(String[] args) throws InterruptedException {

        ThreadA threadA = new ThreadA("threadA");

        synchronized (threadA){
            //获取了thradA对象的监视器锁
            System.out.println(Thread.currentThread().getName()+" 获取了 thradA对象的监视器锁 "+System.currentTimeMillis());
            System.out.println(Thread.currentThread().getName()+"start threadA "+System.currentTimeMillis());
            threadA.start();
            System.out.println(Thread.currentThread().getName()+"block threadA "+System.currentTimeMillis());
            //wait期间会释放 thradA对象的监视器锁，别的线程有机会获取此锁

            System.out.println(Thread.currentThread().getName()+" 释放 thradA对象的监视器锁 "+System.currentTimeMillis());
            threadA.wait();
            //其它线程释放了 thradA对象的监视器锁  唤醒了此线程
            System.out.println(Thread.currentThread().getName()+"continue threadA "+System.currentTimeMillis());
        }

    }



    static class ThreadA extends Thread{
        public ThreadA(String name){
            super(name);
        }

        public void run(){
            synchronized (this){
                //获取了 thradA对象的监视器锁
                System.out.println(Thread.currentThread().getName()+" 获取了 threadA对象的监视器锁 "+System.currentTimeMillis());
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+" wakeup others "+System.currentTimeMillis());

                notify();
            }
            System.out.println(Thread.currentThread().getName()+" 释放 threadA对象的监视器锁 "+System.currentTimeMillis());

        }
    }
}
