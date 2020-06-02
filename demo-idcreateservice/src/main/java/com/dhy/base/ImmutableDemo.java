package com.dhy.base;/**
 * Created by lvaolin on 17/11/1.
 */

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 对比性能：同步方法与不同步方法
 *
 * @author lvaolin
 * @create 17/11/1 上午10:14
 */
public class ImmutableDemo {

    private static final long COUNT = 1000000000;

    static CyclicBarrier cyclicBarrier = new CyclicBarrier(2);

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
                long starttime = System.currentTimeMillis();
                for (int i = 0; i <COUNT ; i++) {
                    //System.out.println(nosync());
                    nosync();
                }
                System.out.println(Thread.currentThread().getName()+" nosync耗时："+(System.currentTimeMillis()-starttime)/1000+"秒");

            }
        }).start();


        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
                long starttime = System.currentTimeMillis();
                for (int i = 0; i <COUNT ; i++) {
                    //System.out.println(sync());
                    sync();
                }
                System.out.println(Thread.currentThread().getName()+" sync耗时："+(System.currentTimeMillis()-starttime)/1000+"秒");

            }
        }).start();



    }


    static  long nosync(){
        return System.currentTimeMillis();
    }


    static synchronized long  sync(){
        return System.currentTimeMillis();
    }

}
