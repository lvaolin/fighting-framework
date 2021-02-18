package com.dhy.cas;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * 带版本的atomic，防止ABA问题
 */
public class SingletonInstanceVersion {

    private SingletonInstanceVersion(){

    }

    private static AtomicStampedReference<SingletonInstanceVersion> instance = new AtomicStampedReference<>(null,0);

    public static SingletonInstanceVersion getInstance(){
        while (true){
            SingletonInstanceVersion singletonInstance = instance.getReference();
            if (singletonInstance!=null) {
                System.out.println(Thread.currentThread().getName()+"获取到了已有实例");
                return singletonInstance;
            }else{
                System.out.println(Thread.currentThread().getName()+"创建实例");
                singletonInstance = new SingletonInstanceVersion();
                if(instance.compareAndSet(null,singletonInstance,0,1)){
                    System.out.println(Thread.currentThread().getName()+"我创建实例成功");
                    return singletonInstance;
                }else{
                    System.out.println(Thread.currentThread().getName()+"创建实例失败，继续尝试");
                }

            }
        }
    }


    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(10);
        for (int i = 0; i <10 ; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        cyclicBarrier.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                    SingletonInstanceVersion.getInstance();
                }
            }).start();

        }

    }
}
