package com.dhy.concurrency.thread;

public class WaitNotify {

    static  volatile boolean flag = true;
    public static void main(String[] args) {

        new Thread(new Runnable() {
            @Override
            public void run() {

                while (true){
                    if (flag) {
                        System.out.println("A");
                        flag = false;
                    }
                }


            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {

                while (true){
                    if (!flag) {
                        System.out.println("B");
                        flag=true;
                    }
                }
            }
        }).start();

    }

}
