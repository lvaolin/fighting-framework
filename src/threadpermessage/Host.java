package threadpermessage;/**
 * Created by lvaolin on 17/11/3.
 */

import java.util.concurrent.*;

/**
 * @author lvaolin
 * @create 17/11/3 下午1:13
 */
public class Host {

    private ScheduledExecutorService executorService ;

    public Host(ScheduledExecutorService executorService){
        this.executorService = executorService;
    }

    public  void printRequest(CountDownLatch countDownLatch,String message){
        new Thread(new Runnable() {
            @Override
            public void run() {
                Helper.printMsg(message);
                countDownLatch.countDown();
            }
        }).start();
    }


    public  void printRequestUsePool(CountDownLatch countDownLatch,String message){

            executorService.schedule(new Runnable() {
                @Override
                public void run() {
                    Helper.printMsg(message);
                    countDownLatch.countDown();
                }
            },10L, TimeUnit.SECONDS);

    }


}
