package com.concurrency.threadTest.shiti;/**
 * Created by lvaolin on 17/10/10.
 */

import java.util.concurrent.TimeUnit;

/**
 * 建立三个线程，A线程打印10次A，B线程打印10次B,C线程打印10次C，要求线程同时运行，交替打印10次ABC
 *
 * @author lvaolin
 * @create 17/10/10 下午3:16
 */
public class ShiTi1 {

    private static Object lock1 = new Object();
    private static Object lock2 = new Object();
    private static Object lock3 = new Object();

    public static void main(String[] args) throws InterruptedException {


        Thread thread_a = new Thread(new Runnable() {
            @Override
            public void run() {

                for (int i=0;i<10;i++){
                        synchronized (lock3){
                            synchronized (lock1){
                                System.out.println("A");

                                try {
                                    TimeUnit.MILLISECONDS.sleep(100);
                                    lock1.notify();

                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }

                            try {
                                lock3.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }

                }





            }
        });

        thread_a.start();
//thread_a.join();
        TimeUnit.SECONDS.sleep(1);


        Thread thread_b = new Thread(new Runnable() {
            @Override
            public void run() {

                for (int i=0;i<10;i++){
                        synchronized (lock1){
                            synchronized (lock2){
                                System.out.println("B");
                                try {
                                    TimeUnit.MILLISECONDS.sleep(100);
                                    lock2.notify();

                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }

                            try {
                                lock1.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }

                }
            }
        });

        thread_b.start();

        //thread_b.join();

        TimeUnit.SECONDS.sleep(1);

        Thread thread_c = new Thread(new Runnable() {
            @Override
            public void run() {

                for (int i=0;i<10;i++){
                        synchronized (lock2){
                            synchronized (lock3){
                                System.out.println("C");
                                try {
                                    TimeUnit.MILLISECONDS.sleep(100);
                                    lock3.notify();

                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                            try {
                                lock2.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }

                }
            }
        });

        thread_c.start();
    }

}
