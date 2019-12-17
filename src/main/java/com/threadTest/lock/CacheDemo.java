package com.threadTest.lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by admin on 2017/10/14.
 */
public class CacheDemo {

    public static void main(String[] args) throws InterruptedException {

        CacheDemo.setData("name","lvaolin");

        for (int i = 0; i <10 ; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true){
                        CacheDemo.getData("name");
                    }

                }
            }).start();
        }

        for (int i = 0; i <2 ; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true){
                        try {
                            CacheDemo.setData("name",System.currentTimeMillis());
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        try {
                            TimeUnit.SECONDS.sleep(3);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                }
            }).start();
        }

    }

    private static volatile Map<String,Object> map = new HashMap<String,Object>();
    private static volatile ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public static Object getData(String key){
        System.out.println("我要读，先获取读锁");
        readWriteLock.readLock().lock();
        System.out.println("获取读锁成功");
        Object object = null;
        try{
            object = map.get(key);
            System.out.println("获取数据成功，数据："+object.toString());
        }catch (Exception e){
              throw e;
        }finally {

            readWriteLock.readLock().unlock();
            System.out.println("释放读锁成功");
        }
        return object;
    }

    public static void setData(String key,Object object) throws InterruptedException {
       // System.out.println("我要更新value了谁也别读了");
        readWriteLock.writeLock().lock();
        try {
            System.out.println("获取写锁成功");
            System.out.println("写入期间谁也别读。。。");
            TimeUnit.SECONDS.sleep(3);
            map.put(key,object);
            System.out.println("写入的数据："+object.toString());
            System.out.println("写入成功");
        }catch (Exception e){
            throw  e;
        }finally {
            readWriteLock.writeLock().unlock();
            System.out.println("释放写锁成功");
        }
    }

}


