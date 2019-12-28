package com.jvm.classload;

import java.io.*;
import java.lang.reflect.Method;
import java.util.regex.Matcher;

/**
 * 双亲委派模型
 * 自定义 class load (覆盖 findClass 方法)
 */
public class Dhy004ClassLoad extends ClassLoader {
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        //实现步骤 1、 通过 File 类把 class文件读取到内存 ，转换成 字节数组 byte[]
        //2、 使用 defineClass方法 实例化后返回
        File file = new File("C:\\code_github\\idcreateservice\\target\\classes",name.replaceAll("\\.", Matcher.quoteReplacement(File.separator)).concat(".class"));

        if (!file.exists()) {
            System.out.println("不存在file"+file.getPath()+file.getName());
        }

        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            int b = 0;
            while ((b = fileInputStream.read()) != 0) {
                byteArrayOutputStream.write(b);
            }

            byte[] bytes = byteArrayOutputStream.toByteArray();
            byteArrayOutputStream.close();
            fileInputStream.close();
            System.out.println(name);
            return defineClass(name,bytes,0,bytes.length);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return super.findClass(name);
    }


    public static void main(String[] args) throws ClassNotFoundException {
        ClassLoader classLoader = new Dhy004ClassLoad();
        Class<?> aClass = classLoader.loadClass("threadTest.A");

        for (Method declaredMethod : aClass.getDeclaredMethods()) {
            System.out.println(declaredMethod);
        }

        System.out.println(aClass.getClassLoader());
        System.out.println(classLoader.getClass().getClassLoader());
        System.out.println(aClass.getClassLoader().getParent());
    }
}
