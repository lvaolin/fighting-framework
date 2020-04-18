package com.dhy.base;

/**
 * @author lvaolin
 * @create 2019/10/15 9:41 AM
 */
public class JvmTest001 {
    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<5;i++){
                    try {
                        Thread.sleep(i*10000);
                        System.out.println("睡了"+i*10+"秒");
                    } catch (InterruptedException e) {
                        System.out.println("干嘛吵醒我");
                    }
                }
            }
        }).start();

        for(int i=0;i<50;i++){
            System.out.print(i);
        }
    }
}
