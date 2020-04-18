package com.concurrency.threadTest.shiti;/**
 * Created by lvaolin on 17/10/10.
 */

/**
 * 建立一个线程，子线程循环5次，主线程循环10次，一共执行10次
 *
 * @author lvaolin
 * @create 17/10/10 下午7:06
 */
public class ShiTi2 {


    public static void main(String[] args) {

        Thread thread = new Thread(new MyThread());
        thread.start();



    }


    static class MyThread implements Runnable{

        @Override
        public void run() {

        }
    }
}
