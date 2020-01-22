package com.concurrency.threadTest.lock.readwritelock;/**
 * Created by lvaolin on 17/11/3.
 */

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 提供读写方法的数据
 *
 * @author lvaolin
 * @create 17/11/3 上午10:32
 */
public class Data {

    private  List<String>  list = new ArrayList<String>();
    ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    Lock writeLock = readWriteLock.writeLock();
    Lock readlock = readWriteLock.readLock();
    public void read(){

        readlock.lock();
        System.out.println(Thread.currentThread().getName()+"获取读锁成功");
        try {
            for (int i = 0; i <list.size() ; i++) {
                System.out.println(Thread.currentThread().getName()+"正在遍历："+list.get(i));
            }
            TimeUnit.SECONDS.sleep(1);
            System.out.println(Thread.currentThread().getName()+"遍历完毕");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            readlock.unlock();
            System.out.println(Thread.currentThread().getName()+"释放读锁成功");
        }




    }

    public void write(){
        writeLock.lock();
        System.out.println(Thread.currentThread().getName()+"获取写锁成功");
        try {
                System.out.println(Thread.currentThread().getName()+"正在写数据");
                list.add(System.currentTimeMillis()+"");
                TimeUnit.SECONDS.sleep(5);
                System.out.println(Thread.currentThread().getName()+"写数据结束");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            writeLock.unlock();
            System.out.println(Thread.currentThread().getName()+"释放写锁成功");
        }
    }

}
