package executor;/**
 * Created by lvaolin on 17/10/18.
 */

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


    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();

        for (int i = 0; i <10 ; i++) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                   // while (true){
                        System.out.println(Thread.currentThread().getName()+"北京时间："+System.currentTimeMillis());
                        /*try {
                            TimeUnit.SECONDS.sleep(1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }*/
                    //}

                }
            });
        }

        executorService.shutdown();
    }



}
