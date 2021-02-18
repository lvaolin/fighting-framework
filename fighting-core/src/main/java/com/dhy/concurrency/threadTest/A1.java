package com.dhy.concurrency.threadTest;/**
 * Created by lvaolin on 17/10/13.
 */

/**
 * @author lvaolin
 * @create 17/10/13 下午4:41
 */
public class A1 {

    int a1 = 8;
    int a2 = getA2();
    {
        int a3 = 9;
        System.out.println("top of A() a1=" + a1 + " a2=" + a2 + "  a3=" + a3);
    }

    public A1() {
        this(66);
        System.out.print("A 构造函数\n");
    }

    {
        System.out.println("below A()..has start");
    }

    public A1(int num) {
        System.out.print("A 带参数构造函数: " + num + "\n");
    }

    static {
        System.out.println("I`m a static {} from class A..");
    }

    int getA2() {
        System.out.println("getA2..");
        return 7;
    }

    public void methodA() {
        System.out.println("methodA");
    }
}
