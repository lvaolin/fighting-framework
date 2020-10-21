package com.dhy.demo01_elasticjob.quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.concurrent.TimeUnit;

public class HelloQuartzDemo1 {
    public static void main(String[] args) throws SchedulerException {

        //创建一个scheduler
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        scheduler.getContext().put("skey", "svalue");//传递数据方式1

        //创建一个Trigger
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("trigger1", "group1")
                .usingJobData("t1", "tv1")//传递数据方式2
                .withSchedule(SimpleScheduleBuilder
                    .simpleSchedule()
                    .withIntervalInSeconds(3)
                    .repeatForever())
                .build();
        trigger.getJobDataMap().put("t2", "tv2");//传递数据方式3

        //创建一个job
        JobDetail jobDetail = JobBuilder
                .newJob(HelloJob.class)
                .usingJobData("j1", "jv1")//传递数据方式4
                .withIdentity("myjob", "mygroup")
                .build();
        jobDetail.getJobDataMap().put("j2", "jv2");//传递数据方式5

        //注册trigger并启动scheduler
        scheduler.scheduleJob(jobDetail, trigger);

        //job监听器
        scheduler.getListenerManager().addJobListener(new MyJobListener());
        //trigger监听器
        scheduler.getListenerManager().addTriggerListener(new MyTriggerListener());
        //scheduler监听器
        scheduler.getListenerManager().addSchedulerListener(new MySchedulerListener());
        scheduler.start();

        try {
            TimeUnit.SECONDS.sleep(6);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        scheduler.shutdown();
    }


}
