package guardedsuspension;/**
 * Created by lvaolin on 17/11/1.
 */

import java.util.concurrent.TimeUnit;

/**
 * 服务器端处理请求  消费端
 *
 * @author lvaolin
 * @create 17/11/1 下午2:15
 */
public class ServerThread  extends Thread{

    private final RequestQueue requestQueue;

    public ServerThread(RequestQueue requestQueue){
        this.requestQueue = requestQueue;
    }

    @Override
    public void run() {

        while (true&&!Thread.currentThread().isInterrupted()){
            try {
                //TimeUnit.MILLISECONDS.sleep(500);
                System.out.println("消费请求："+requestQueue.getRequest());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
