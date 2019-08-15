package guardedsuspension;/**
 * Created by lvaolin on 17/11/1.
 */

import java.util.LinkedList;
import java.util.Queue;

/**
 * 请求缓冲队列   先进先出算法
 *
 * @author lvaolin
 * @create 17/11/1 下午2:06
 */
public class RequestQueue {

    private Queue<String> queue = new LinkedList<String>();

    public synchronized void putRequest(String name){
        queue.add(name);
        notifyAll();
        System.out.println(Thread.currentThread().getName()+"请求入队");
    }

    public synchronized String getRequest() throws InterruptedException {

        while (queue.size()==0){
            System.out.println(Thread.currentThread().getName()+"没有请求 进入wait");
            wait();
        }
        System.out.println("请求出队："+queue.size());
        return queue.remove();

    }


}
