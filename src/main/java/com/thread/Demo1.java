package com.thread;

/**
 * 交替输出 字母和数字-----------------------
 */
public class Demo1 {

    volatile static boolean flag1 = true;
    volatile static  boolean flag2 = false;
    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    if(flag1){
                        System.out.println("a");
                        flag1 = false;
                        flag2 =true;
                    }

                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    if(flag2){
                        System.out.println("1");
                        flag2 = false;
                        flag1 = true;
                    }

                }

            }
        }).start();
    }


}
