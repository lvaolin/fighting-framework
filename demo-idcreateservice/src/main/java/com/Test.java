package com;

import java.util.concurrent.TimeUnit;

/**
 * wait、notify只能用在synchronized 代码块内部
 * 可以考虑这样一个场景：有10个线程去消费一个队列里的任务，
 * 当队列里有任务时这10个线程要不停的轮询去拉取任务执行任务
 * 当队列里没有任务时，要求只有一个线程轮询拉取任务，其它9个要暂停，不要做无用功浪费cpu时间
 * 当队列里有任务时，正在轮询的那个线程要唤醒其它9个线程，一起工作
 * 首先获取任务的方法要使用 synchronized 来进行多线程同步
 * @author lvaolin
 * @create 18/4/17 上午11:03
 */
public class Test {

    public static void main(String[] args) {
        for (int i = 0; i <10 ; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    synchronized (Test.class){
                        try {
                            System.out.println(Thread.currentThread().getName()+"我要进入wait状态了");
                            Test.class.wait();
                            System.out.println(Thread.currentThread().getName()+"我被唤醒了");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                }
            }).start();


        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (Test.class){

                        Test.class.notifyAll();
                        System.out.println(Thread.currentThread().getName()+"唤醒wait状态的线程");
                }

            }
        }).start();


    }





}
