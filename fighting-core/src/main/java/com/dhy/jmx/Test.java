package com.dhy.jmx;

import java.lang.management.ManagementFactory;

/**
 * @Project fighting
 * @Description 主要用途描述
 * @Author lvaolin
 * @Date 2021/2/22 6:11 下午
 */
public class Test {
    public static void main(String[] args) {
        int t = 1;
        int core = ManagementFactory.getOperatingSystemMXBean().getAvailableProcessors();
        long totalPassedTime = t*1000*1000*core;
        System.out.println(totalPassedTime);
        double l = (double) 400000 / totalPassedTime;
        System.out.println(l);
    }
}
