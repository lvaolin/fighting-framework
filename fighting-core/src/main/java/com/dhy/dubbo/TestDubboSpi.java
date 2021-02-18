package com.dhy.dubbo;

import com.dhy.spi.Robot;
import org.apache.dubbo.common.extension.ExtensionLoader;

import java.util.Set;

public class TestDubboSpi {
    public static void main(String[] args) {
        ExtensionLoader<Robot> extensionLoader = ExtensionLoader.getExtensionLoader(Robot.class);

        Robot myRobot21 = extensionLoader.getLoadedExtension("MyRobot2");
        if (myRobot21==null) {
            System.out.println("MyRobot2尚未加载");
        }
        //加载MyRobot1
        Robot myRobot1 = extensionLoader.getExtension("MyRobot1");
        myRobot1.sayHello();
        //加载MyRobot2
        Robot myRobot2 = extensionLoader.getExtension("MyRobot2");
        myRobot2.sayHello();

        //已经加载过的key名称
        Set<String> loadedExtensions = extensionLoader.getLoadedExtensions();
        loadedExtensions.forEach(a->{
            System.out.println(a);
        });

        //从缓存中加载
        Robot myRobot22 = extensionLoader.getLoadedExtension("MyRobot2");
        if (myRobot22!=null) {
            myRobot2.sayHello();
        }
        //是否存在某个拓展
        System.out.println(extensionLoader.hasExtension("MyRobot2"));
    }
}
