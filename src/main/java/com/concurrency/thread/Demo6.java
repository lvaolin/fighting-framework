package com.concurrency.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 交替输出 字母和数字----------------------------
 */
public class Demo6 {

        static Thread t1 = null;
        static Thread t2 = null;
        static Lock lock = new ReentrantLock();
        public static void main(String[] args) {

           t1 =  new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    try {
                        synchronized (Demo6.class){
                            print1();
                            Demo6.class.notify();
                            Demo6.class.wait();
                        }

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

         t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    try {
                        synchronized (Demo6.class){
                            print2();
                            Demo6.class.notify();
                            Demo6.class.wait();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        });

        t1.start();
        t2.start();
    }


    public static void print1() throws InterruptedException {
                System.out.println("a");
    }
    public static void print2() throws InterruptedException {
            System.out.println("1");
    }

}
