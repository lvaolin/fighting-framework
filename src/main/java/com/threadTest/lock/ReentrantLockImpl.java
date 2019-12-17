package com.threadTest.lock;/**
 * Created by lvaolin on 17/10/27.
 */

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * 自定义reentrantlock实现Lock接口
 *
 * @author lvaolin
 * @create 17/10/27 下午6:59
 */
public class ReentrantLockImpl implements Lock {

    boolean isLocked= false;
    Thread  lockedBy = null;
    int lockedCount=0;

    @Override
    public synchronized void lock() {
        Thread callingThread = Thread.currentThread();
        while (isLocked&&callingThread!=lockedBy){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        isLocked = true;
        lockedBy = callingThread;
        lockedCount++;

    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public synchronized void unlock() {

        Thread callingThread = Thread.currentThread();
        if (isLocked&&callingThread==lockedBy){
            lockedCount--;
            isLocked = false;
            lockedBy = null;
            notify();
        }

    }

    @Override
    public Condition newCondition() {
        return null;
    }
}
