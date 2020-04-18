package com.dhy.lock;

/**
 * @author lvaolin
 * @create 2019/12/25 11:43 AM
 */
public class JUC01_1 {

    public static void main(String[] args){ //这个线程是用来读取flag的值的
        ThreadDemo threadDemo = new ThreadDemo();
        Thread thread = new Thread(threadDemo);
        thread.start();
        while (true){
            if (threadDemo.flag){
                System.out.println("主线程读取到的flag = " + threadDemo.flag);
                break;
            }
        }
    }

    static class ThreadDemo implements Runnable{ //这个线程是用来修改flag的值的
        public  boolean flag = false;
        @Override
        public void run() {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            flag = true;
            System.out.println("ThreadDemo线程修改后的flag = " + flag);
        }
    }
}
