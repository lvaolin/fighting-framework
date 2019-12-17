package com.threadTest;/**
 * Created by lvaolin on 17/9/11.
 * 使用 Volatile 测试
 */

/**
 * @author lvaolin
 * @create 17/9/11 下午1:09
 */
public class TestWithVolatile {


    private static volatile boolean bChanged;

    public static void main(String[] args) throws InterruptedException {
        new Thread() {

            @Override
            public void run() {
                for (;;) {
                    System.out.println("bbbbbb");
                    if (bChanged == !bChanged) {
                        System.out.println("!=");
                        System.exit(0);
                    }
                }
            }
        }.start();
        Thread.sleep(1);
        new Thread() {

            @Override
            public void run() {
                for (;;) {
                    bChanged = !bChanged;
                    System.out.println("aaaaaaa");
                }
            }
        }.start();
    }
}
