package com.dhy.concurrency.thread;

public class LockSupport {

    public static void main(String[] args) {

      Thread threadA =  new Thread(new Runnable() {
            @Override
            public void run() {

                while (true){

                        System.out.println("A");
                       java.util.concurrent.locks.LockSupport.park();
                }


            }
        });

      threadA.start();

      Thread threadB =   new Thread(new Runnable() {
            @Override
            public void run() {

                while (true){
                        System.out.println("B");
                    java.util.concurrent.locks.LockSupport.park();

                }
            }
        });

      threadB.start();




    }

}
