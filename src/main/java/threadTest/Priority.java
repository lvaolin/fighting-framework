package threadTest;/**
 * Created by lvaolin on 17/9/21.
 */

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 线程优先级
 *
 * @author lvaolin
 * @create 17/9/21 下午6:30
 */
public class Priority {

    private  static volatile boolean notStart = true;
    private  static volatile boolean  notEnd = true;

    public static void main(String[] args) {
        List<Job> jobs = new ArrayList<Job>();
        for (int i = 0; i <10 ; i++) {
            int priority=i<5?Thread.MIN_PRIORITY:Thread.MAX_PRIORITY;
            Job job = new Job(priority);
            jobs.add(job);

            Thread thread = new Thread(job,"Thread:"+i);
            thread.setPriority(priority);
            thread.start();
        }


        notStart = false;
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        notEnd = false;

        for (Job job:jobs) {
            System.out.println("Job priority:"+job.priority+"，Count："+job.jobCount);
        }

    }

    //场景：启动10个线程，前5个线程优先级为1，后五个线程优先级为10
    //jobCount进行递增操作，运行时间10毫秒
    //打印所有线程的优先级和jobCount的值，看看是否是优先级高的线程jobCount较大，发现没有明显的差异，这说明程序的正确性不能依赖于线程的优先级

    static class Job implements Runnable{
        private int priority;
        private long jobCount;
        public Job(int priority){
            this.priority = priority;
        }

        public void run(){
            while (notStart){
                Thread.yield();
            }

            while (notEnd){
                Thread.yield();
                jobCount++;
            }
        }
    }

}
