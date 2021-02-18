package com.dhy.concurrency.threadTest;/**
 * Created by lvaolin on 17/9/22.
 */

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 线程同步屏障：本质是 等待所有线程到位后一起开始执行，比如 同步屏障设置为2，则必须至少有2个线程调用了 await 方法，才能继续1
 *
 * @author lvaolin
 * @create 17/9/22 下午1:57
 */
public class CyclicBarrierTest {


    public static void main(String[] args) throws BrokenBarrierException, InterruptedException {

        final CyclicBarrier cyclicBarrier = new CyclicBarrier(3);

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("线程1已到达指定位置");
                try {
                    cyclicBarrier.await();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println("线程1结束");
            }
        });
        thread1.start();
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("线程2已到达指定位置");
                try {

                    Thread.sleep(5000);
                    cyclicBarrier.await();

                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println("线程2结束");
            }
        });
        thread2.start();

        cyclicBarrier.await();


        System.out.println("主线程开始。。");
        System.out.println("主线程结束。。");

    }
}
