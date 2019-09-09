package com.thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * 交替输出 字母和数字-----------------------
 */
public class Demo2 {

        static Thread t1 = null;
        static Thread t2 = null;
        public static void main(String[] args) {

           t1 =  new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                        System.out.println("a");
                        LockSupport.unpark(t2);
                        LockSupport.park();
                }
            }
        });

         t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    LockSupport.park();
                    System.out.println("1");
                    LockSupport.unpark(t1);


                }

            }
        });

        t2.start();
        t1.start();
    }


}
