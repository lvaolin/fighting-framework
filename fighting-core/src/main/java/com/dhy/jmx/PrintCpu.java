package com.dhy.jmx;

import com.sun.management.OperatingSystemMXBean;

import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.sql.Time;
import java.util.concurrent.TimeUnit;

/**
 * @Project idcreateservice
 * @Description 主要用途描述
 * @Author lvaolin
 * @Date 2021/2/10 5:10 下午
 */
public class PrintCpu {

    public static void main(String[] args) throws IOException, InterruptedException {
        OperatingSystemMXBean opMXbean = (OperatingSystemMXBean)ManagementFactory.getOperatingSystemMXBean();
        long lastTime = opMXbean.getProcessCpuTime();
        long startTime = System.nanoTime();
        for (int i = 0; i <10000 ; i++) {
            double systemLoadAverage = opMXbean.getSystemLoadAverage();
            int availableProcessors = opMXbean.getAvailableProcessors();
            long thisTime = opMXbean.getProcessCpuTime();
            long stopTime = System.nanoTime();
            double x = systemLoadAverage*100/availableProcessors;
            //过去这段时间内jvm占用的cpu时间（纳秒）
            long l1 = thisTime - lastTime;
            //过去的这段时间内 cpu拥有的时间总和（4核心之和）纳秒
            long l2 = (stopTime - startTime) * availableProcessors;
            long l = l1*100/l2;
            System.out.println("jvm cpu使用情况："+l+"%");
            System.out.println("cpu核心数量："+availableProcessors);
            System.out.println("OS系统负载："+systemLoadAverage);
            System.out.println("cpu整体使用率（用户+系统）："+x+"%");
            System.out.println(opMXbean.getProcessCpuLoad());
            System.out.println("-------------------------------");
            TimeUnit.SECONDS.sleep(5);
        }


        //Process top = Runtime.getRuntime().exec("ls");
        //top.getInputStream()
//        Long start = System.currentTimeMillis();
//        long startT = opMXbean.getProcessCpuTime();
//        /**    Collect data every 5 seconds      */
//        try {
//            TimeUnit.SECONDS.sleep(5);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        Long end = System.currentTimeMillis();
//        long endT = opMXbean.getProcessCpuTime();
////end - start 即为当前采集的时间单元，单位ms
////endT - startT 为当前时间单元内cpu使用的时间，单位为ns
//        double ratio = (entT-startT)/1000000.0/(end-start)/opMXbean.getAvailableProcessors();
//        System.out.println(ratio);
    }
}
