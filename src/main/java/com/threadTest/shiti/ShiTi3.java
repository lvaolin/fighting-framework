package com.threadTest.shiti;/**
 * Created by lvaolin on 17/10/11.
 */


import java.util.concurrent.CountDownLatch;

/**
 * 有个boss在做年度计划，然后他要求三名员工做完各自的报表交给他后，他才能继续做年度计划。用Java多线程模拟实现。
 *
 * @author lvaolin
 * @create 17/10/11 上午10:25
 */
public class ShiTi3 {


    public static void main(String[] args) throws InterruptedException {
        final CountDownLatch countDownLatch = new CountDownLatch(3);

        System.out.println("我正在做年度计划，需要ABC三名员工提交报表才能继续，等待中");

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("A员工提交了报表");
                countDownLatch.countDown();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("B员工提交了报表");
                countDownLatch.countDown();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("C员工提交了报表");
                countDownLatch.countDown();
            }
        }).start();

        countDownLatch.await();

        System.out.println("员工都提交了，我该继续做年度计划了");
    }


}
