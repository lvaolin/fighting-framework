package com.dhy.base;/**
 * Created by lvaolin on 17/10/27.
 */

/**
 * 父类指向子类实例测试
 *
 * @author lvaolin
 * @create 17/10/27 上午11:41
 */
public class B1 extends A1{

    String s1 = "B1";
    String s2 = "static B1";

    public static  void f1(){
        System.out.println("B f1");
    }

    public   void f2(){
        System.out.println("B f2");
    }


    public static void main(String[] args) {
        A1 a1 = new B1();
        a1.f1();//A f1
        a1.f2();//B f2
        System.out.println(a1.s1);//A1
        System.out.println(a1.s2);//static A1


        B1 b1 = new B1();
        b1.f1();//B f1
        b1.f2();//B f2
        System.out.println(b1.s1);//B1
        System.out.println(b1.s2);//static B1
    }
}
