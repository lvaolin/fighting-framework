package com.concurrency.threadTest.exchanger;/**
 * Created by lvaolin on 17/10/18.
 */

import java.util.concurrent.Exchanger;

/**
 * Exchanger可以在两个线程之间交换数据，只能是2个线程，他不支持更多的线程之间互换数据。 当线程A调用Exchange对象的exchange()方法后，他会陷入阻塞状态，直到线程B也调用了exchange()方法，然后以线程安全的方式交换数据，之后线程A和B继续运行
 *
 * @author lvaolin
 * @create 17/10/18 上午11:40
 */
public class ExchangerTest {

    static Exchanger<String> exchanger = new Exchanger<String>();
    public static void main(String[] args) {

        new Thread(new Runnable() {

            String myname="lval";

            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName()+"你好这是我的名片："+myname);
                try {
                    myname = exchanger.exchange(myname);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+"交互完毕："+myname);
            }
        }).start();


        new Thread(new Runnable() {

            String myname="zhangsan";

            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName()+"你好这是我的名片："+myname);
                try {
                    myname = exchanger.exchange(myname);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+"交互完毕："+myname);
            }
        }).start();


    }


}
