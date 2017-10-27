package executor;/**
 * Created by lvaolin on 17/10/18.
 */

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 模拟心跳机制
 *
 * @author lvaolin
 * @create 17/10/18 下午1:48
 */
public class HeartBeat {


    public static void main(String[] args) {

        ScheduledExecutorService executor = Executors.newScheduledThreadPool(5);

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("HeartBeat......");
            }
        };
        //5秒后第一次执行，之后每隔1秒执行一次
        executor.scheduleAtFixedRate(runnable,5,1, TimeUnit.SECONDS);


    }



}
