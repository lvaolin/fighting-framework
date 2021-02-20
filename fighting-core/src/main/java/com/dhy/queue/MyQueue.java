package com.dhy.queue;

import java.util.Collection;
import java.util.Iterator;
import java.util.Queue;
import java.util.concurrent.*;

/**
 * @Project fighting
 * @Description 队列
 * @Author lvaolin
 * @Date 2021/2/20 3:56 下午
 */
public class MyQueue {
    public static BlockingQueue<String> blockingQueue = new LinkedBlockingQueue<>();
    public static Queue<String> queue = new ConcurrentLinkedQueue<>();
    public static int nThreads = 10;
    public static ExecutorService fixedExecutorService = new ThreadPoolExecutor(nThreads, nThreads,
            0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>(100));
    public static ExecutorService cachedExecutorService = Executors.newCachedThreadPool();
}
