package com.threadTest;/**
 * Created by lvaolin on 17/9/11.
 * 不使用 Volatile 测试
 */

/**
 * @author lvaolin
 * @create 17/9/11 上午10:52
 */
public class TestWithoutVolatile {

    private static boolean bChanged = false;

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
        Thread.sleep(1000);
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
