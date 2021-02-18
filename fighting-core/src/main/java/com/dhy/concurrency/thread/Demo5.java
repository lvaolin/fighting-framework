package com.dhy.concurrency.thread;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * 交替输出 字母和数字----------------------------
 */
public class Demo5 {

        static Semaphore semaphore = new Semaphore(1);
        public static void main(String[] args) {


            Thread t1 =  new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    try {
                            semaphore.acquire();
                            print1();
                            semaphore.release();
                            TimeUnit.SECONDS.sleep(1);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

            Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    try {
                        semaphore.acquire();
                        print2();
                        semaphore.release();
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
                System.out.println("a");
    }
    public static void print2() throws InterruptedException {
            System.out.println("1");
    }

}
