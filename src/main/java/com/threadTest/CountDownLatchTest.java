package com.threadTest;/**
 * Created by lvaolin on 17/9/22.
 */

/**
 * 等待多线程完成的CountDownLatch
 *
 * @author lvaolin
 * @create 17/9/22 下午1:22
 */
public class CountDownLatchTest {


    public static void main(String[] args) throws InterruptedException {

       final   java.util.concurrent.CountDownLatch countDownLatch = new java.util.concurrent.CountDownLatch(2);

         Thread thread1 = new Thread(new Runnable() {
             @Override
             public void run() {
                 System.out.println("线程1开始");
                 try {
                     Thread.sleep(3000);
                     countDownLatch.countDown();
                 } catch (InterruptedException e) {
                     e.printStackTrace();
                 }
                 System.out.println("线程1结束");
             }
         });
         thread1.start();
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("线程2开始");
                try {
                    Thread.sleep(3000);
                    countDownLatch.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("线程2结束");
            }
        });
        thread2.start();

        countDownLatch.await();

        System.out.println("子线程全部结束。。。");
        System.out.println("主线程结束。。。");

    }


}
