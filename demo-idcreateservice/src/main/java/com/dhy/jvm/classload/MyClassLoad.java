package com.dhy.jvm.classload;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Method;
import java.util.regex.Matcher;

/**
 *
 * 1、继承 ClassLoader 类
 * 2、覆盖 findClass 方法
 * （通过 File 类把 class文件读取到内存 ，转换成 字节数组 byte[]，再通过父类的 defineClass方法 实例化后返回 Class）
 * 3、classFilePath 不能和 AppClassLoad的classpath相同，
 * 否则需要的类会被AppClassLoad加载进来（MyClassLoad的parent是AppClassLoad）
 * 4、也就是说自定义类加载器需要把类放到JVM classpath之外的一个目录中
 */
public class MyClassLoad extends ClassLoader{
    private String classFilePath;
    private MyClassLoad(){

    }
    private MyClassLoad(String classFilePath){
        this.classFilePath = classFilePath;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        //实现步骤
        //1、 通过 File 类把 class文件读取到内存 ，转换成 字节数组 byte[]
        //2、 使用 defineClass方法 实例化后返回
        File file = new File(classFilePath,name.replaceAll("\\.", Matcher.quoteReplacement(File.separator)).concat(".class"));

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
            return super.defineClass(name,bytes,0,bytes.length);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return super.findClass(name);
    }


    public static void main(String[] args) throws ClassNotFoundException {
        ClassLoader classLoader = new MyClassLoad("/Users/lvaolin/Downloads/classes/");
        Class<?> aClass = classLoader.loadClass("com.dhy.concurrency.threadTest.A");

        for (Method declaredMethod : aClass.getDeclaredMethods()) {
            System.out.println(declaredMethod);
        }

        System.out.println(aClass.getClassLoader());
        System.out.println(classLoader.getClass().getClassLoader());
        System.out.println(aClass.getClassLoader().getParent());
    }
}
