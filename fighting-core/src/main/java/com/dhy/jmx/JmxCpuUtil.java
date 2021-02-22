package com.dhy.jmx;
 
import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.lang.management.ThreadMXBean;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class JmxCpuUtil {


    private static OperatingSystemMXBean osMxBean = ManagementFactory.getOperatingSystemMXBean();
    private static ThreadMXBean threadBean = ManagementFactory.getThreadMXBean();


    //获取过去t秒钟jvm占用cpu的情况(堵塞时间t秒)
    public static  double getProcessCpu(long t) {

        //t1时刻java线程耗费的cpu时间之和
        long preUsedTime = 0;
        for (long id : threadBean.getAllThreadIds()) {
            preUsedTime += threadBean.getThreadCpuTime(id);
        }
        try {
            //休眠1s
            TimeUnit.SECONDS.sleep(t);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //t2 时刻java线程耗费的cpu时间之和
        long totalTime = 0;
        for (long id : threadBean.getAllThreadIds()) {
            totalTime += threadBean.getThreadCpuTime(id);//nanoseconds
        }

        //t2-t1 期间 java线程耗费的cpu时间增量值
        long usedTime = totalTime-preUsedTime;
        //拥有的cpu时间资源之和
        long totalPassedTime = t*1000*1000*1000*osMxBean.getAvailableProcessors();
        System.out.println("totalPassedTime:"+totalPassedTime);
        //cpu占用率
        System.out.println("usedTime:"+usedTime);
        return (double)usedTime/totalPassedTime ;
    }

    private static volatile boolean flag = false;

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (!flag){
                    System.out.println(UUID.randomUUID().toString());
                }
            }
        }).start();
        System.out.println(JmxCpuUtil.getProcessCpu(10L)*100+"%");
        flag = true;
    }
}