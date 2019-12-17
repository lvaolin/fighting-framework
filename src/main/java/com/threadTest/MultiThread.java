package com.threadTest;/**打印出所有线程
 * Created by lvaolin on 17/9/21.
 */

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

/**
 * @author lvaolin
 * @create 17/9/21 下午6:00
 */
public class MultiThread {


    public static void main(String[] args) {
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        ThreadInfo[] threadInfos =   threadMXBean.dumpAllThreads(true,true);

        for (ThreadInfo threadInfo:threadInfos) {
            System.out.println("线程ID:"+threadInfo.getThreadId());
            System.out.println("线程name："+threadInfo.getThreadName());
        }

        try {
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
