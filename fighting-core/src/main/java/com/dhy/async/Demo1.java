package com.dhy.async;

import java.util.concurrent.TimeUnit;

/**
 * @Project fighting
 * @Description java 产生一个新线程的两种方式
 * 1、实现  Runnable接口，传入 Thread类，调用start方法启动线程
 * 2、定义一个类，继承Thread类，覆盖 run方法，调用start方法启动线程
 * @Author lvaolin
 * @Date 2021/2/24 5:26 下午
 */
public class Demo1 {
    public static void main(String[] args) {
        //1、实现  Runnable接口，传入 Thread类，调用start方法启动线程
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"mythread");
        thread.start();
        //2、定义一个类，继承Thread类，覆盖 run方法，调用start方法启动线程
        new MyThread("xxx").start();
    }

    static class MyThread extends Thread{

        public MyThread(String name){
            super(name);
        }
        @Override
        public void run() {
            System.out.println("xxx");
            try {
                TimeUnit.SECONDS.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
