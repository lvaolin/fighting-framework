package com.dhy.lock.readwritelock;

import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Main {


    public static void main(String[] args) throws InterruptedException {
        ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
        ReentrantReadWriteLock.ReadLock readLock = reentrantReadWriteLock.readLock();
        ReentrantReadWriteLock.WriteLock writeLock = reentrantReadWriteLock.writeLock();
        readLock.lock();
        readLock.lock();
        System.out.println("获取读锁成功"+reentrantReadWriteLock.getReadLockCount());
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("获取写锁中----");
                writeLock.lock();
                System.out.println("获取写锁成功");
            }
        });
        thread.start();
        readLock.unlock();
        System.out.println("释放读锁成功"+reentrantReadWriteLock.getReadLockCount());
        //thread.join();
        System.out.println("获取读锁中"+reentrantReadWriteLock.getReadLockCount());
        readLock.lock();
        System.out.println("获取读锁成功"+reentrantReadWriteLock.getReadLockCount());

    }

}
