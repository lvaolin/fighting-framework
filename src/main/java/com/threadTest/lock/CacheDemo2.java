package com.threadTest.lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by admin on 2017/10/14.
 */
public class CacheDemo2 {

    public static void main(String[] args) throws InterruptedException {

        for (int i = 0; i <2 ; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true){
                        try {
                            CacheDemo2.getData("name");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                }
            }).start();
        }



    }
    static volatile Object  object = null;
    private static volatile Map<String,Object> map = new HashMap<String,Object>();
    private static volatile ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    public static Object getData(String key) throws InterruptedException {
        System.out.println("我要读，先获取读锁");
        readWriteLock.readLock().lock();
        System.out.println("获取读锁成功");

        try{
            object = map.get(key);
            if(object==null){
                System.out.println("没有获取到数据，先从db载入到cache");
                readWriteLock.readLock().unlock();
                System.out.println("释放读锁成功。。。。");
                System.out.println("尝试获取写锁。。。。");
                TimeUnit.SECONDS.sleep(3);
                readWriteLock.writeLock().lock();
                try {
                    System.out.println("获取写锁成功");
                    if(object==null){
                        System.out.println("写入期间谁也别读。。。");
                        TimeUnit.SECONDS.sleep(3);
                         object = System.currentTimeMillis();
                        map.put(key,object);
                        System.out.println("写入的数据："+object);
                        System.out.println("写入成功");
                    }else{
                        System.out.println("已经被其他线程更新");
                    }

                }catch (Exception e){
                    throw  e;
                }finally {
                    readWriteLock.writeLock().unlock();
                    System.out.println("释放写锁成功");
                }

                readWriteLock.readLock().lock();

            }

            System.out.println("获取数据成功，数据："+object.toString());
        }catch (Exception e){
              throw e;
        }finally {

            readWriteLock.readLock().unlock();
            System.out.println("释放读锁成功");
        }
        return object;
    }



}


