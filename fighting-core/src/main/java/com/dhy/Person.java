package com.dhy;

/**
 * @author lvaolin
 * @create 18/4/17 上午11:03
 */
public class Person {
    public  String name = "p1";
    public static String nameStatic = "static p1";
    {
        System.out.println("person非静态代码块");
    }
    static {
        System.out.println("person静态代码块");
    }
    public Person(){
        System.out.println("person 无参构造方法");
    }

    public String show(){
        return "Person:"+name;
    }
    public static String showStatic(){
        return "Person："+nameStatic;
    }

    public static void main(String[] args) {
        int _1MB=1000;
        byte[] bytes = new byte[5*_1MB];
        //System.out.println((5*_1)+"");
    }

}
