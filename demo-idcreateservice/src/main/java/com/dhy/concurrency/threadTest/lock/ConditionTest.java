package com.dhy.concurrency.threadTest.lock;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by admin on 2017/10/15.
 */
public class ConditionTest {

    public static void main(String[] args) {
        final List<String> list = new ArrayList<String>();
        final Lock lock = new ReentrantLock();
        final Condition condition = lock.newCondition();

        for (int i = 0; i <1 ; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true){
                        lock.lock();
                        try {
                            while (list.size()==0){
                                try {
                                    System.out.println(Thread.currentThread().getName()+"仓库没货了需要补货"+list.size());
                                    condition.await();//仓库没货了需要补货
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                            list.remove(0);
                            try {
                                TimeUnit.SECONDS.sleep(1);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            System.out.println(Thread.currentThread().getName()+"仓库不满，可以补货"+list.size());
                            condition.signalAll();
                        }finally {
                            lock.unlock();
                        }
                    }
                }
            },"thread-remove-"+i).start();
        }



        for (int i = 0; i <3 ; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true){
                        lock.lock();
                        try {
                            while (list.size()>=10){
                                try {
                                    System.out.println(Thread.currentThread().getName()+"仓库满了"+list.size());
                                    condition.await();//仓库满了尽快消费
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                            list.add(""+System.currentTimeMillis());
                            try {
                                TimeUnit.SECONDS.sleep(1);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            System.out.println(Thread.currentThread().getName()+"仓库有货，可以消费"+list.size());
                            condition.signalAll();
                        }finally {
                            lock.unlock();
                        }


                    }
                }
            },"thread-add-"+i).start();
        }




    }

}
