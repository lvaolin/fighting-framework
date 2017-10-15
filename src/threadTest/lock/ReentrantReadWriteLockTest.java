package threadTest.lock;/**
 * 读写锁
 * Created by lvaolin on 17/10/12.
 */

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author lvaolin
 * @create 17/10/12 上午11:08
 */
public class ReentrantReadWriteLockTest {

    static volatile ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    static volatile int i = 0;

    public static void main(String[] args) {

        CyclicBarrier cyclicBarrier = new CyclicBarrier(3);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
                while (true){
                    readWriteLock.readLock().lock();
                    try {
                        TimeUnit.SECONDS.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    try {
                        //i++;
                        System.out.println("thread1 i="+i);

                    }catch (Exception e){
                        e.printStackTrace();
                    }finally {
                        readWriteLock.readLock().unlock();
                    }
                }



            }
        },"thread1").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
                while (true){
                    readWriteLock.readLock().lock();
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    try {
                        //i++;
                        System.out.println("thread2 i="+i);
                    }catch (Exception e){
                        e.printStackTrace();
                    }finally {
                        readWriteLock.readLock().unlock();
                    }
                }



            }
        },"thread2").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
                while (true){

                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    readWriteLock.writeLock().lock();


                    try {
                        i++;
                        System.out.println("thread3 i="+i);
                    }catch (Exception e){
                        e.printStackTrace();
                    }finally {
                        readWriteLock.writeLock().unlock();
                    }
                }



            }
        },"thread3").start();



       // TimeUnit.SECONDS.sleep(5);

    }



}
