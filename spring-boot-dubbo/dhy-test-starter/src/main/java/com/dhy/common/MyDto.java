package com.dhy.common;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties("dhy")
@Component
public class MyDto {
    @Override
    public String toString() {
        return "MyDto{" +
                "jobname='" + jobname + '\'' +
                ", scanPath='" + scanPath + '\'' +
                ", cron='" + cron + '\'' +
                ", appname='" + appname + '\'' +
                ", regip='" + regip + '\'' +
                '}';
    }

    private  String jobname;
    private  String scanPath;
    private  String cron;
    private String appname;
    private String regip;


    public String getJobname() {
        return jobname;
    }

    public void setJobname(String jobname) {
        this.jobname = jobname;
    }

    public String getScanPath() {
        return scanPath;
    }

    public void setScanPath(String scanPath) {
        this.scanPath = scanPath;
    }

    public String getCron() {
        return cron;
    }

    public void setCron(String cron) {
        this.cron = cron;
    }

    public String getAppname() {
        return appname;
    }

    public void setAppname(String appname) {
        this.appname = appname;
    }

    public String getRegip() {
        return regip;
    }

    public void setRegip(String regip) {
        this.regip = regip;
    }



}
