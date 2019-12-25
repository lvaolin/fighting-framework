package com.dhy.lock;

/**
 * 100个线程同时修改共享变量 i  ，每个线程进行 i++ 操作 1万次。
 * 非原子操作测试问题复现
 * @author lvaolin
 * @create 2019/12/24 7:56 PM
 */
public class AtomicTest01_1 {

    static volatile int i=0;
    public static void main(String[] args) throws InterruptedException {

        for (int j = 0; j <100 ; j++) {
            new ThreadDemo().start();
        }
        i=1;
        System.out.println("i已变更为1");
    }

    static class ThreadDemo extends Thread{
        @Override
        public void run() {
              while (i==0){

              }
              System.out.println("检测到i="+i);
        }
    }


}
