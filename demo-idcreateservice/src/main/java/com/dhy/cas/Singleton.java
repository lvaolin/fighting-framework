package com.dhy.cas;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author lvaolin
 * @create 2019/12/30 9:29 AM
 */
public class Singleton {

    public Object[] arr = new Object[10000];
    {
        for (int i = 0; i < arr.length; i++) {
            arr[i]=i;
        }

    }
    private static final AtomicReference<Singleton> INSTANCE = new AtomicReference();
    private Singleton() {}
    public static Singleton getInstance() {
        for (;;) {

            Singleton singleton = INSTANCE.get();
            if (null != singleton) {
                System.out.println(Thread.currentThread().getName()+"我获取到了instance，退出。");
                return singleton;
            }
            singleton = new Singleton();
            if (INSTANCE.compareAndSet(null, singleton)) {
                System.out.println(Thread.currentThread().getName()+"我创建instance成功。");
                return singleton;
            }else{
                System.out.println(Thread.currentThread().getName()+"我创建instance失败。继续cas尝试。");
            }
        }
    }




}
