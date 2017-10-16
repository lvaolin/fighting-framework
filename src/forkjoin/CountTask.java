package forkjoin;/**
 * 生成一个计算任务，负责计算 1+2+3+4
 * Created by lvaolin on 17/10/16.
 */

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

/**
 * Fork Join 框架的使用
 *
 * @author lvaolin
 * @create 17/10/16 上午9:08
 */
public class CountTask extends RecursiveTask<Long>{

    private static final int THRESHOLD = 10;//最小任务数  阈值
    private long start,end;
    public CountTask(long start,long end){
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        long sum = 0;
        boolean canCompute = (end-start)<=THRESHOLD;
        System.out.println("递归中...");
        if(canCompute){
            System.out.println("递归见底");
            for (long i = start; i <=end ; i++) {
                sum+=i;
            }
        }else{
            long middle = (start+end)/2;
            CountTask leftTask = new CountTask(start,middle);
            CountTask rightTask = new CountTask(middle+1,end);

            leftTask.fork();
            rightTask.fork();

            long leftResult = leftTask.join();
            long rightResult = rightTask.join();

            sum = leftResult+rightResult;

        }

        return sum;
    }


    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        CountTask countTask = new CountTask(1,10000000);
        Future<Long> result =  forkJoinPool.submit(countTask);

        try {
            System.out.println(result.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }
}
