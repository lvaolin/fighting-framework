package com.dhy.base;/**
 * Created by lvaolin on 17/10/27.
 */

/**
 * 子类父类实例化过程
 *
 * @author lvaolin
 * @create 17/10/27 上午11:38
 */
public class A {
    int g_a;
    static {
        System.out.println("1");
    }

    public A(){
        System.out.println("2");
    }

    public void m(){
        int l_a;
        //类变量可以不用显示初始化，因为系统会自动初始化
        System.out.println(g_a);
        //局部变量必须由程序员显示初始化，否则编译报错
        //System.out.println(l_a);
    }

}
