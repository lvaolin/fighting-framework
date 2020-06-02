package com.dhy.jvm.classload;

/**
 * 使用app class load 加载指定的类
 */
public class Dhy001ClassLoad {
    public static void main(String[] args) throws ClassNotFoundException {
        System.out.println(Dhy001ClassLoad.class.getClassLoader());
        Class clazz = Dhy001ClassLoad.class.getClassLoader().loadClass("com.dhy.Person");
        System.out.println(clazz.getName());
    }
}
