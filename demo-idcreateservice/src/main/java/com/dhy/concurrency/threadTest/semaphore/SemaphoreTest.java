package com.dhy.concurrency.threadTest.semaphore;/**
 * Created by lvaolin on 17/10/18.
 */

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * Semaphore实现的功能就类似厕所有5个坑，假如有10个人要上厕所，那么同时只能有多少个人去上厕所呢？同时只能有5个人能够占用，当5个人中 的任何一个人让开后，其中等待的另外5个人中又有一个人可以占用了。另外等待的5个人中可以是随机获得优先机会，也可以是按照先来后到的顺序获得机会，这取决于构造Semaphore对象时传入的参数选项。
 *
 * @author lvaolin
 * @create 17/10/18 上午10:40
 */
public class SemaphoreTest {


    static Semaphore semaphore = new Semaphore(5);

    public static void main(String[] args) {


        for (int i = 0; i <20 ; i++) {

            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName()+"我正在准备获取许可,目前剩余许可数："+semaphore.availablePermits());
                    try {
                        semaphore.acquire();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName()+"我获得了许可"+System.currentTimeMillis()+" 剩余许可："+semaphore.availablePermits());
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    semaphore.release();
                    System.out.println(Thread.currentThread().getName()+"任务完成我释放了许可,"+" 剩余许可："+semaphore.availablePermits());
                }
            }).start();

        }
    }






}
