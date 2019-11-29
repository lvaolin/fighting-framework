package com.dhy.dropframe;

/**
 * 调试 idea 的断点在多线程环境下 乱停问题  呵呵
 * @author lvaolin
 * @create 2019/11/29 11:30 AM
 */
public class BreakThreadTest {

    public static int g=0;
    public static void main(String[] args) {
        final BreakThreadTest test = new BreakThreadTest();
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    test.method1();
                    test.method2();
                }
            }).start();

        }
    }

    public void method1(){
        System.out.println("method1:"+Thread.currentThread().getName());
    }
    public void method2(){
        System.out.println("method2:"+Thread.currentThread().getName());
    }
}

