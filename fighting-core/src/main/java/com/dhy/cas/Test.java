package com.dhy.cas;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Test {


    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(500);
        for (int i = 0; i <500 ; i++) {
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
                    Singleton instance = Singleton.getInstance();
                    System.out.println(instance.arr[0].hashCode());
                }
            }).start();
        }
    }
}
