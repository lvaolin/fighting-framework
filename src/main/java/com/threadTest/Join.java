package com.threadTest;/**
 * Created by lvaolin on 17/9/27.
 */

import java.util.concurrent.TimeUnit;

/**
 * Thread。join的使用
 *
 * @author lvaolin
 * @create 17/9/27 下午8:21
 */
public class Join {


    public static void main(String[] args) throws InterruptedException {

        Thread lastThread = Thread.currentThread();

        for (int i = 0; i <10 ; i++) {
            Thread thread = new Thread(new DemoThread(lastThread),"线程："+i);
            thread.start();
            lastThread =  thread;
        }

        TimeUnit.SECONDS.sleep(3);
        System.out.println(Thread.currentThread().getName()+"结束了");

    }

    public static class DemoThread implements Runnable{

        Thread thread;
        public DemoThread(Thread lastThread){
            this.thread = lastThread;
        }


        @Override
        public void run() {

            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


            System.out.println(Thread.currentThread().getName()+"结束了");
        }
    }

}
