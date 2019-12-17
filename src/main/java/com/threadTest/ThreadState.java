package com.threadTest;/**
 * Created by lvaolin on 17/9/21.
 */

import java.util.concurrent.TimeUnit;

/**
 * 线程的状态
 *
 * @author lvaolin
 * @create 17/9/21 下午7:19
 */
public class ThreadState {

    public static void main(String[] args) {
        new Thread(new TimeWaiting(),"TimeWaitingThread-1").start();
        new Thread(new TimeWaiting(),"TimeWaitingThread-2").start();
        new Thread(new Waiting(),"WaitingThread-1").start();
        new Thread(new Waiting(),"WaitingThread-2").start();
        new Thread(new Blocked(),"BlockedThread-1").start();
        new Thread(new Blocked(),"BlockedThread-2").start();
    }


    static class TimeWaiting implements Runnable{
        public void run(){
            while (true){
                try {
                    TimeUnit.SECONDS.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class Waiting implements Runnable{
        @Override
        public void run() {
            while (true){
                synchronized (Waiting.class){
                    try {
                        Waiting.class.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    static class Blocked implements  Runnable{
        @Override
        public void run() {
            synchronized (Blocked.class){
                while (true){
                    try {
                        Thread.sleep(100000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

}
