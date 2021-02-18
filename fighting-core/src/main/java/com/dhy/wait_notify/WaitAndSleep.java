package com.dhy.wait_notify;

import lombok.SneakyThrows;

public class WaitAndSleep {


    public static void main(String[] args) {


        new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                synchronized (WaitAndSleep.class){
                    System.out.println("wait before");
                    wait();
                    System.out.println("wait after");
                }
            }
        }).start();

        new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                synchronized (WaitAndSleep.class){
                    System.out.println("notify before");
                    notifyAll();
                    System.out.println("notify after");
                }
            }
        }).start();

        System.out.println("main exit");

    }
}
