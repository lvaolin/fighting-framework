package com.dhy.jvm.classload;

import javax.servlet.Servlet;
import java.sql.DriverManager;

/**
 * 使用app class load 加载指定的类
 */
public class Dhy001ClassLoad {
    public static void main(String[] args) throws ClassNotFoundException {
        System.out.println(Dhy001ClassLoad.class.getClassLoader());
        System.out.println(Thread.currentThread().getContextClassLoader());
        System.out.println(String.class.getClassLoader());//null 表示是 BootstreapClassLoad
        System.out.println(DriverManager.class.getClassLoader());
        System.out.println(Servlet.class.getClassLoader());
        System.out.println(Dhy001ClassLoad.class.getClassLoader().getParent());
        System.out.println(Dhy001ClassLoad.class.getClassLoader().getParent().getParent());
        Class clazz = Dhy001ClassLoad.class.getClassLoader().loadClass("com.dhy.Person");
        Class<?> aClass1 = Class.forName("com.dhy.Person");//默认使用当前类的类加载器
        Class<?> aClass2 = Servlet.class.getClassLoader().loadClass("com.dhy.Person");
       // Class<?> aClass = String.class.getClassLoader().loadClass("com.dhy.Person");

    }
}
