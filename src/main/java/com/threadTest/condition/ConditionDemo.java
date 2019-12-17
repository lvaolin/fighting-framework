package com.threadTest.condition;/**
 * Created by lvaolin on 17/10/19.
 */

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author lvaolin
 * @create 17/10/19 上午9:37
 */
public class ConditionDemo {




    public static void main(String[] args) throws InterruptedException {
        // 获取独占锁
         ReentrantLock lock = new ReentrantLock();
        // 获取条件1
          Condition cd1 = lock.newCondition();
        // 获取条件2
         Condition cd2 = lock.newCondition();
        // 获取条件3
         Condition cd3 = lock.newCondition();

        ConditionDemo demo = new ConditionDemo();
        ExecutorService es = Executors.newCachedThreadPool();//此线程池特点，优先使用空闲线程，空闲线程可以存活60秒,无空闲线程时创建新线程，线程数量无上限，适合任务多而小（处理时间短）场景
        Thread1 tr1 = demo.new Thread1(lock,cd1);
        Thread1 tr2 = demo.new Thread1(lock,cd2);
        Thread1 tr3 = demo.new Thread1(lock,cd3);
        // 启动线程任务123.
        es.execute(tr1);
        es.execute(tr2);
        es.execute(tr3);
        Thread.sleep(2000);
        // 依次唤醒各线程任务.
        signalTest(lock,cd1,cd2,cd3);
        es.shutdown();
    }

    // 依次唤醒各线程任务,以观察condition的作用
    public static void signalTest(Lock lock,Condition ... condition ) throws InterruptedException {
        // 获取独占锁 唤醒cd1的线程
        lock.lock();
        condition[0].signal();
        // 释放独占锁 等待thread1执行完毕.
        lock.unlock();
        Thread.sleep(2000);

        // 获取独占锁 唤醒cd2的线程
        lock.lock();
        condition[1].signal();
        // 释放独占锁 等待thread2执行完毕.
        lock.unlock();
        Thread.sleep(2000);

        // 获取独占锁 唤醒cd3的线程
        lock.lock();
        condition[2].signal();
        // 释放独占锁 等待thread2执行完毕.
        lock.unlock();
        Thread.sleep(2000);
    }

    // 线程任务定义类
    public class Thread1 implements Runnable {

        Condition condition;
        Lock lock;
        public Thread1(Lock lock,Condition condition) {
            this.lock = lock;
            this.condition = condition;
        }

        @Override
        public void run() {
            try {
                // 设置线程名称
                //Thread.currentThread().setName(Thread1.class.getSimpleName());
                System.out.printf("%s线程启动\n", Thread.currentThread().getName());
                lock.lock();
                // 在cd1上阻塞，并且释放独占锁lock.
                condition.await();
                System.out.printf("%s线程被唤醒\n", Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }




}
