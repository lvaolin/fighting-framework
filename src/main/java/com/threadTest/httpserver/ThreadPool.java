package com.threadTest.httpserver;/**
 * Created by lvaolin on 17/10/10.
 */

/**
 * 线程池接口
 *
 * @author lvaolin
 * @create 17/10/10 下午1:45
 */
public interface  ThreadPool<Job extends Runnable> {

    //执行一个job
    void execute(Job job);

    //关闭线程池
    void shutdown();

    //增加工作者线程
    void addWorkers(int num);

    //减少工作者线程
    void removeWorker(int num);

    //返回线程池正在等待执行的任务数量
    int getJobSize();

}
