package threadTest.httpserver;/**
 * Created by lvaolin on 17/10/10.
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * 默认线程池实现
 *
 * @author lvaolin
 * @create 17/10/10 下午1:45
 */
public class DefaultThreadPool<Job extends Runnable> implements ThreadPool<Job>{

    //工作列表
    private final LinkedList<Job> jobs = new LinkedList<Job>();
    //工作者列表
    private final List<Worker> workers = Collections.synchronizedList(new ArrayList<Worker>());


    public  DefaultThreadPool(){

    }

    public DefaultThreadPool(int num){

    }

    @Override
    public void execute(Job job) {

    }

    @Override
    public void shutdown() {
     //关闭所有工作者线程
        for (Worker worker:workers) {
            worker.shutdown();
        }

    }

    @Override
    public void addWorkers(int num) {


    }

    @Override
    public void removeWorker(int num) {

    }

    @Override
    public int getJobSize() {
        return 0;
    }


     class Worker implements Runnable{

        private volatile boolean running = true;
        @Override
        public void run() {
            while (running){
                Job job = null;
                synchronized (jobs){
                    while (jobs.isEmpty()){
                        try {
                            jobs.wait();
                        } catch (InterruptedException e) {
                            //感知到外部对WorkThread的中断操作，返回
                            //e.printStackTrace();
                            Thread.currentThread().interrupt();
                            return;
                        }
                    }

                    job = jobs.removeFirst();
                }

                if(job != null){
                    job.run();
                }

            }
        }

        public void shutdown(){
            running = false;
        }
    }
}
