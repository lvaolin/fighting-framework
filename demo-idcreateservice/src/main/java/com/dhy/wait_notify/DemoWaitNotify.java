package com.dhy.wait_notify;

public class DemoWaitNotify {

    static Object o = new Object();

    public static void main(String[] args) {



        new Thread(new Runnable() {
            @Override
            public void run() {

                synchronized (o){

                    while (true){
                        try {
                            System.out.println(0);
                            o.wait(5000);
                            System.out.println("0wait之后notify之前");
                            o.notify();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }

            }
        }).start();



       /* new Thread(new Runnable() {
            @Override
            public void run() {

                synchronized (o){
                    while (true){
                        try {
                            System.out.println(1);

                            o.notify();
                            System.out.println("1wait之后notify之前");
                            o.wait();

                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }


            }
        }).start();*/
    }

}
