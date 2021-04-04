package com.dhy.demo01_elasticjob.quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import static org.quartz.TriggerBuilder.*;
import static org.quartz.CronScheduleBuilder.*;
import static org.quartz.SimpleScheduleBuilder.*;
import static org.quartz.DateBuilder.*;

public class HelloQuartzDemo2 {
    public static void main(String[] args) throws SchedulerException {

        //创建一个scheduler
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

        //简单触发一次性的任务
        SimpleTrigger simpleTrigger = (SimpleTrigger) newTrigger()
                .withIdentity("trigger1", "group1")
                .startNow()                  // some Date
                .withSchedule(simpleSchedule()
                    .withIntervalInSeconds(10)
                    .withRepeatCount(10))
                .forJob("myjob", "mygroup")
                .build();

        CronTrigger cronTrigger = (CronTrigger) newTrigger()
                .withIdentity("trigger1", "group1")
                .startNow()                  // some Date
                .withSchedule(cronSchedule("0/3 * * * * ?"))
                .build();

        //创建一个job
        JobDetail jobDetail = JobBuilder
                .newJob(HelloJob.class)
                .usingJobData("j1", "jv1")
                .withIdentity("myjob", "mygroup")
                .build();

        //注册trigger并启动scheduler
        scheduler.scheduleJob(jobDetail, cronTrigger);
        //job监听器
        scheduler.getListenerManager().addJobListener(new MyJobListener());
        //trigger监听器
        scheduler.getListenerManager().addTriggerListener(new MyTriggerListener());

        scheduler.start();
    }


}
