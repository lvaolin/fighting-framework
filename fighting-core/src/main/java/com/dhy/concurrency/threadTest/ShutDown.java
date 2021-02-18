package com.dhy.concurrency.threadTest;/**
 * Created by lvaolin on 17/9/26.
 */

import java.util.concurrent.TimeUnit;

/**
 * 安全的终止线程,有机会去释放资源
 *
 * @author lvaolin
 * @create 17/9/26 下午8:38
 */
public class ShutDown {



    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                int i=0;
                while (!Thread.currentThread().isInterrupted()){
                    System.out.println(i++);
                }
                System.out.println("thread1收到中断信息退出");
            }
        });
        thread.start();
        TimeUnit.SECONDS.sleep(1);
        thread.interrupt();//发送中断信号


        Runner runner = new Runner();
        Thread thread2 = new Thread(runner);
        thread2.start();
        TimeUnit.SECONDS.sleep(2);

        runner.cancle();

        System.out.println("main退出");
    }


    private static class Runner implements Runnable{
          volatile boolean on = true;
        @Override
        public void run() {
            int i=0;
            while (on){
                System.out.println("On:"+i++);
            }
            System.out.println("thread2收到on为false退出");
        }

        public void cancle(){
            on = false;
        }
    }

}
