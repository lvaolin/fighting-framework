package com.dhy.demo01;

import com.dhy.demo01.dto.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class StartDemo01 {
    public static void main(String[] args) {
        //注解配置启动
        AnnotationConfigApplicationContext ac =
                new AnnotationConfigApplicationContext();
        ac.scan("com.dhy.demo01");
        ac.refresh();

        User bean = ac.getBean(User.class);
        System.out.println(bean);
        System.out.println("okl12");


    }
}
