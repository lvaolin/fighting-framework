package com.concurrency.thread;

import java.util.concurrent.TimeUnit;

/**
 * 交替输出 字母和数字----------------------------
 */
public class Demo3 {

        static Thread t1 = null;
        static Thread t2 = null;
        static Object object1 = new Object();
        public static void main(String[] args) {

           t1 =  new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    try {
                        print1();
                        TimeUnit.SECONDS.sleep(1);
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
                        print2();
                        TimeUnit.SECONDS.sleep(1);
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
            synchronized (object1){
                System.out.println("a");
            }

    }
    public static void print2() throws InterruptedException {
        synchronized (object1){
            System.out.println("1");
        }

    }

}
