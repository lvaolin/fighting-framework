package com.dhy.jvm.jvmti;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;

import java.lang.management.*;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Slf4j
public class PrintThreads {

    public static void main(String[] args) {


        //记录线程堆栈信息
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        ThreadInfo[] threadInfos = threadMXBean.dumpAllThreads(true, true);
        for (ThreadInfo threadInfo : threadInfos) {
            printStackTrace(threadInfo);
        }

        //记录GC次数与耗时
        List<GarbageCollectorMXBean> garbageCollectorMXBeans = ManagementFactory.getGarbageCollectorMXBeans();
        for (GarbageCollectorMXBean garbageCollectorMXBean : garbageCollectorMXBeans) {
            log.info("GC次数："+garbageCollectorMXBean.getCollectionCount());
            log.info("GC耗费时间："+garbageCollectorMXBean.getCollectionTime());
        }

        //记录os信息
        OperatingSystemMXBean os = ManagementFactory.getOperatingSystemMXBean();
        log.info("os架构："+os.getArch()+"\n os名称："+os.getName()+"\n os版本:"+os.getVersion()+"\n cpu核心数目："+os.getAvailableProcessors()+"\n os系统负载："+os.getSystemLoadAverage());
        RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();

        log.info("JVM启动时间：" +runtimeMXBean.getStartTime()+"\n 运行时间："+runtimeMXBean.getUptime()+"\n 名称："+runtimeMXBean.getName());

        Map<String, String> systemProperties = runtimeMXBean.getSystemProperties();

        Set<Map.Entry<String, String>> entries = systemProperties.entrySet();
        for (Map.Entry<String, String> entry : entries) {
            System.out.println(entry.getKey()+"");
        }

    }

    static void  printStackTrace(ThreadInfo threadInfo){
        try {
            StringBuilder sb = new StringBuilder(threadInfo.getThreadId()+":"+threadInfo.getThreadName()+":"+threadInfo.getThreadState());
            sb.append(":"+threadInfo.getBlockedTime());
            sb.append(":"+threadInfo.getWaitedTime());
            sb.append("\n");
            for (StackTraceElement stackTraceElement : threadInfo.getStackTrace()) {
                sb.append(stackTraceElement.getClassName()).append(".")
                        .append(stackTraceElement.getMethodName())
                        .append("(").append(stackTraceElement.getFileName()).append(":")
                        .append(stackTraceElement.getLineNumber()).append(")").append("\n");
            }
            log.error(sb.toString());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }



}
