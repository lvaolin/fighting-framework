package com.dhy.demo01_elasticjob;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * service 层 使用 代理后的 Mapper 获取数据
 */
public class StartDemo03 {
    public static void main(String[] args)  {
        //注解配置启动
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext();
        ac.scan("com.dhy.demo01_elasticjob");
        ac.refresh();
        synchronized (StartDemo03.class){
            try {
                StartDemo03.class.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
