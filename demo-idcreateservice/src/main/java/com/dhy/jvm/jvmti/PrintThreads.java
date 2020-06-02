package com.dhy.jvm.jvmti;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

public class PrintThreads {

    public static void main(String[] args) {


        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        ThreadInfo[] threadInfos = threadMXBean.dumpAllThreads(true, true);
        for (ThreadInfo threadInfo : threadInfos) {

            System.out.println(threadInfo.getThreadId()+":"+threadInfo.getThreadName());
        }

    }
}
