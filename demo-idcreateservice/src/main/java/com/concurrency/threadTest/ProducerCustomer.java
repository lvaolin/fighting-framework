package com.concurrency.threadTest;/**
 * Created by lvaolin on 17/9/27.
 */

import java.util.ArrayList;
import java.util.List;

/**
 * 多线程生产者消费者模式
 *
 * @author lvaolin
 * @create 17/9/27 上午10:09
 */
public class ProducerCustomer {

    static List listLock = new ArrayList();
    static int MAXSIZE = 10;
    static int MINSIZE = 0;

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public  void run() {
                synchronized (listLock){
                    while (true){
                        if(listLock.size()<MAXSIZE){
                            System.out.println("生产线程1正在生产");
                            listLock.add("1");
                        }else{
                            listLock.notifyAll();
                            try {
                                listLock.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }

                    }


                }

            }
        },"p1").start();

        new Thread(new Runnable() {
            @Override
            public  void run() {
                synchronized (listLock){
                    while (true){
                        if(listLock.size()<MAXSIZE){
                            System.out.println("生产线程2正在生产");
                            listLock.add("1");
                        }else{
                            listLock.notifyAll();
                            try {
                                listLock.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }

                    }


                }

            }
        },"p2").start();

        new Thread(new Runnable() {
            @Override
            public  void run() {
                synchronized (listLock){
                    while (true){
                        if (listLock.size()>MINSIZE){
                            System.out.println("消费线程1正在消费");
                            listLock.remove(0);
                        }else{
                            listLock.notifyAll();
                            try {
                                listLock.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }

                    }


                }

            }
        },"c1").start();
        new Thread(new Runnable() {
            @Override
            public  void run() {
                synchronized (listLock){
                    while (true){
                        if (listLock.size()>MINSIZE){
                            System.out.println("消费线程2正在消费");
                            listLock.remove(0);
                        }else{
                            listLock.notifyAll();
                            try {
                                listLock.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }

                    }


                }

            }
        },"c2").start();

    }



}
