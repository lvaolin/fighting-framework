package com.concurrency.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Lock1 {

    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        Thread threadA =  new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    System.out.println("A");
                    try {
                        condition.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    condition.signal();
                }


            }
        });

      threadA.start();

      Thread threadB =   new Thread(new Runnable() {
            @Override
            public void run() {

                while (true){
                        System.out.println("B");
                    try {
                        condition.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    condition.signal();

                }
            }
        });

      threadB.start();




    }

}
