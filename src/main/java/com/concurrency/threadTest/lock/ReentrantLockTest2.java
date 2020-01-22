package com.concurrency.threadTest.lock;/**
 * 展现锁的可重入性
 * Created by lvaolin on 17/10/12.
 */

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author lvaolin
 * @create 17/10/12 上午11:08
 */
public class ReentrantLockTest2 {


    static int i = 0;
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(10);
         Lock lock = new ReentrantLock();
        for (int i=1;i<=100;i++){
            new Thread(new MyThread("thread"+i,cyclicBarrier,lock)).start();
        }

    }


    static class MyThread implements  Runnable{

        String name;
        CyclicBarrier cyclicBarrier;
        Lock lock ;
        public MyThread(String name,CyclicBarrier cyclicBarrier,Lock lock ){
            this.name = name;
            this.cyclicBarrier = cyclicBarrier;
            this.lock = lock;
        }

        public void hello(){
            lock.lock();
            try {
                System.out.println(name+":重入...");

            }catch (Exception e){

            }finally {
                lock.unlock();
            }
        }

        @Override
        public void run() {
            try {
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            while (true){
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                lock.lock();
                try {
                    i++;
                    hello();//在此方法中需要再次获取lock，正常执行 体现了 ReentrantLock 的可重入性：同一个线程可以多次获取释放同一把锁
                    System.out.println(name+" i="+i);

                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    lock.unlock();
                }
            }
        }



    }

}
