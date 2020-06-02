package com.dhy.databasepool;/**
 * Created by lvaolin on 17/9/29.
 */

import java.sql.Connection;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 数据库连接池测试
 *
 * @author lvaolin
 * @create 17/9/29 下午8:01
 */
public class ConnectionPoolTest {

    volatile static AtomicInteger success = new AtomicInteger(0);//获取连接成功的数量
    volatile static AtomicInteger fail = new AtomicInteger(0);//获取连接失败的数量

    public static void main(String[] args) throws InterruptedException {

        final ConnectionPool connectionPool = new ConnectionPool(10);
        final CountDownLatch countDownLatch = new CountDownLatch(100);
        int i = 0;
        while (i<100){
            new Thread(new Runnable() {
                @Override
                public void run() {

                    try {
                        Connection connection = connectionPool.getConnection(1000);
                        if(connection!=null){
                            success.incrementAndGet();
                            System.out.println("获取db连接成功:"+Thread.currentThread().getName());

                        }else{
                            fail.incrementAndGet();
                            System.out.println("获取db连接失败:"+Thread.currentThread().getName());
                        }

                        TimeUnit.SECONDS.sleep(10);
                        connectionPool.releaseConnection(connection);

                        countDownLatch.countDown();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            },"conn"+i++).start();

            Thread.sleep(500);

        }

        countDownLatch.wait();

        System.out.println("成功率："+success.intValue()+"/"+(success.intValue()+fail.intValue())+"="+(success.floatValue()/(success.floatValue()+fail.floatValue())));


    }



}
