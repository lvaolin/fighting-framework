package com.dhy.timetask;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @Project fighting
 * @Description 验证定时器不会自动被GC回收
 * @Author lvaolin
 * @Date 2021/2/23 6:01 下午
 */
public class LimiterConfigMgr {
    public LimiterConfigMgr() {
        //定时刷新配置
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                synchronized (this) {
                    System.out.println("我还在"+Thread.currentThread().getName());
                }
            }
        }, 10 * 1000, 10 * 1000); //延时5分钟，每5分钟执行更新
    }
}
