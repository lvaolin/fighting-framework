package executor;/**
 * Created by lvaolin on 17/10/18.
 */

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * CachedThreadPoolTest 创建可缓存的线程池，如果线程池中的线程在60秒未被使用就将被移除，在执行新的任务时，当线程池中有之前创建的可用线程就重      用可用线程，否则就新建一条线程 方法签名：
 *
 * @author lvaolin
 * @create 17/10/18 下午2:11
 */
public class CachedThreadPoolTest {

   static ExecutorService executorService = Executors.newFixedThreadPool(10);


    public static void main(String[] args) throws InterruptedException {
         CountDownLatch countDownLatch = new CountDownLatch(5);
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                // 初始化基础档案数据
                System.out.println("1");
                countDownLatch.countDown();
            }
        });
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("2");
                countDownLatch.countDown();
            }
        });

        executorService.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println("3");
                countDownLatch.countDown();
            }
        });
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("4");
                countDownLatch.countDown();
            }
        });
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("5");
                countDownLatch.countDown();
            }
        });


        countDownLatch.await();
        System.out.println("执行完毕");
        executorService.shutdown();
    }



}
