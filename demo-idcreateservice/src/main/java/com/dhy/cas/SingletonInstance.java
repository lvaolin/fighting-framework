package com.dhy.cas;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 不带版本的atomic 有ABA问题，但在单例场景下不需要解决ABA问题
 */
public class SingletonInstance {

    private SingletonInstance(){

    }

    private static AtomicReference<SingletonInstance> instance = new AtomicReference<>();

    public static SingletonInstance getInstance(){
        while (true){
            SingletonInstance singletonInstance = instance.get();
            if (singletonInstance!=null) {
                System.out.println("获取到了已有实例");
                return singletonInstance;
            }else{
                System.out.println("创建实例");
                singletonInstance = new SingletonInstance();
                if(instance.compareAndSet(null,singletonInstance)){
                    System.out.println("我创建实例成功");
                    return singletonInstance;
                }else{
                    System.out.println("创建实例失败，继续尝试");
                }

            }
        }
    }


    public static void main(String[] args) {
        SingletonInstance.getInstance();
    }
}
