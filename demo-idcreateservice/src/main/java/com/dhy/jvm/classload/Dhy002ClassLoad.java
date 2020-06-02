package com.dhy.jvm.classload;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 反射出来加载的class的信息
 */
public class Dhy002ClassLoad {
    public static void main(String[] args) throws ClassNotFoundException {
        Class clazz = Dhy002ClassLoad.class.getClassLoader().loadClass("com.dhy.Person");
        //打印所有方法名称
        for (Method method : clazz.getDeclaredMethods()) {
            System.out.println(method.getName());
        }
        //打印所有属性名称
        for (Field declaredField : clazz.getDeclaredFields()) {
            System.out.println(declaredField.getName());
        }
        //打印所有接口名称
        for (Class anInterface : clazz.getInterfaces()) {
            System.out.println(anInterface.getName());
        }
    }
}
