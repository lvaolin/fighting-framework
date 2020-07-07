package com.dhy.interrupted;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * 线程池中任务中断测试
 */
public class Main {

    static ExecutorService executorService = Executors.newFixedThreadPool(5);
    public static void main(String[] args) throws InterruptedException{
        Future<?> future1 = executorService.submit(new Task1());
        Future<?> future2 = executorService.submit(new Task2());
        TimeUnit.SECONDS.sleep(2);
        //如果2秒钟后还没有完成则中断任务
        future1.cancel(true);
        future2.cancel(true);
        System.out.println("任务中断信号已发出");
        TimeUnit.SECONDS.sleep(5);

        System.out.println("main线程退出");
        executorService.shutdown();
    }



    static class Task1 extends Thread{
        public  void run(){
            try {
                System.out.println("任务1开始-----");
                TimeUnit.SECONDS.sleep(10000);
            }catch (InterruptedException e){
                System.out.println("中断异常，我1被中断了");
                return;
            }
            System.out.println("任务正常结束------");
        }
    }

    static class Task2 extends Thread{
        public  void run(){
            try {
                System.out.println("任务2开始-----");
                while (!Thread.currentThread().isInterrupted()){
                    System.out.println("任务2正在执行中--");
                }
                System.out.println("任务2正常结束（检测中断位）------");
            }catch (Exception e){
                System.out.println("任务2异常结束------");
                e.printStackTrace();
            }

        }
    }
}
