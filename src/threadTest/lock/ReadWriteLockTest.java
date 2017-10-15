package threadTest.lock;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by admin on 2017/10/14.
 */
public class ReadWriteLockTest {

    static Object data;
    static volatile boolean cacheValid;
    static final ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
    static CyclicBarrier cyclicBarrier = new CyclicBarrier(10);
    public static void main(String[] args) {

        cacheValid = true;

        for (int i = 0; i <10 ; i++) {
            new Thread(new MyThread("thread"+i),""+i).start();
        }

        cacheValid = false;


    }

    static class MyThread implements  Runnable{

        String name;
        public MyThread(){

        }
        public MyThread(String name){
            this.name = name;
        }
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
                    processCachedData(name);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

   static void processCachedData(String threadname) throws InterruptedException {
         rwl.readLock().lock();
       System.out.println(threadname+"我获取到了读锁");
         if (!cacheValid) {
             System.out.println(threadname+"检测到数据已过期，我要去获取写锁然后更新数据");
               // Must release read lock before acquiring write lock
               rwl.readLock().unlock();
             System.out.println(threadname+"我先释放了readLock，准备获取writeLock");
             System.out.println(threadname+"正在努力获取写锁，等待所有读锁释放完毕");
               rwl.writeLock().lock();
             System.out.println(threadname+"获取writeLock成功，休息3秒钟");
             TimeUnit.SECONDS.sleep(3);
             System.out.println(threadname+"休息完毕");
               try {
                     // Recheck state because another thread might have
                     // acquired write lock and changed state before we did.
                     if (!cacheValid) {
                         System.out.println(threadname+"更新缓存数据@@@");
                           data = "2017"+System.currentTimeMillis();
                           cacheValid = true;
                         System.out.println(threadname+"更新成功");
                     }else{
                         System.out.println(threadname+"缓存数据已经被其他线程更新，不需要我重写了");
                     }
                        // Downgrade by acquiring read lock before releasing write lock
                        rwl.readLock().lock();
                        System.out.println(threadname+"获取读锁成功");
                   } finally {
                        rwl.writeLock().unlock(); // Unlock write, still hold read
                        System.out.println(threadname+"释放写锁成功");
                   }
             }

         try {
             System.out.println(threadname+"正在使用data:"+data);
         } finally {
             rwl.readLock().unlock();
             System.out.println(threadname+"使用data完毕，释放读锁并退出");
         }
    }
}

