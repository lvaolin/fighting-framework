package com.dhy.concurrency.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 交替输出 字母和数字----------------------------
 */
public class BlockQueue {

    static BlockingQueue<String> queue1 = new ArrayBlockingQueue(1);
    static BlockingQueue<String> queue2 = new ArrayBlockingQueue(1);
        public static void main(String[] args) {

            final char[] a = "abcde".toCharArray();
            final char[] b = "12345".toCharArray();

            Thread t1 =  new Thread(new Runnable() {
            @Override
            public void run() {
                for (char x:a) {
                    System.out.println(x);
                    try {
                        queue1.put("ok");
                        queue2.take();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

            Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (char x:b) {

                    try {
                        queue1.take();
                        System.out.println(x);
                        queue2.put("ok");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        t2.start();
        t1.start();
    }


    public static void print1() throws InterruptedException {
                System.out.println("a");
    }
    public static void print2() throws InterruptedException {
            System.out.println("1");
    }

}
