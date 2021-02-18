package com.dhy.jvm.classload;

/**
 * 双亲委派模型
 * bootstrap             c++ 实现，所以 不在java 内存控制范围内  打印出来是null
 * ext class load
 * app class load
 * 自定义 class load (覆盖 findClass 方法)
 */
public class Dhy003ClassLoad {
    public static void main(String[] args) throws ClassNotFoundException {
        System.out.println(String.class.getClassLoader());//null
        System.out.println(Dhy003ClassLoad.class.getClassLoader());//app class load
        System.out.println(Dhy003ClassLoad.class.getClassLoader().getClass().getClassLoader()); //null

    }
}
