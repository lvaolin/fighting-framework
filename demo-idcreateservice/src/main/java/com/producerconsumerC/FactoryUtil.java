package com.producerconsumerC; /**
 * Created by lvaolin on 17/9/6.
 */

import java.util.ArrayList;
import java.util.List;

/**
 * @author lvaolin
 * @create 17/9/6 下午2:56
 */
public class FactoryUtil {
    private   int  MAX_SIZE = 100;
    private   int MIN_SIZE = 0;

    private   List buf = new ArrayList();

    public  void put(Object o) throws InterruptedException {
        synchronized (buf){
            while (buf.size() == MAX_SIZE) {
                System.out.println("生产者：生产者仓库已饱和，进行等待消费状态。。。。");
                buf.wait();
            }
            System.out.println("生产者：生产者仓库未饱和，进行生产作业。。。。。:"+buf.size());
            buf.add(o);
            buf.notify();
        }

    }


    public   Object get() throws InterruptedException {
        synchronized (buf){
            while (buf.size() == MIN_SIZE) {
                System.out.println("消费者：仓库已没货，进入等待状态。");
                buf.wait();
            }

            System.out.println("消费者：仓库已补货，进入消费状态....。:"+buf.size());
            Object o = buf.remove(0);
            buf.notify();
            return o;
        }

    }

}
