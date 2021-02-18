package com.dhy.jmx;

import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
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
        OperatingSystemMXBean opMXbean = ManagementFactory.getOperatingSystemMXBean();
        for (int i = 0; i <10000 ; i++) {
            double systemLoadAverage = opMXbean.getSystemLoadAverage();
            int availableProcessors = opMXbean.getAvailableProcessors();
            double x = systemLoadAverage/availableProcessors;
            System.out.println("cpu核心数量："+availableProcessors);
            System.out.println("系统负载："+systemLoadAverage);
            System.out.println("cpu使用率："+x);
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
