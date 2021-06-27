package com.dhy.xml;

import com.dhy.xml.mylabel.Apple;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * @Title Main
 * @Description
 * @Author lvaolin
 * @Date 2021/6/27 22:58
 **/
public class Main {

    public static void main(String[] args) throws IOException {

        ClassPathXmlApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("spring/spring-context.xml");
        applicationContext.start();
        // 从容器中获取bean
        Apple apple = (Apple) applicationContext.getBean("apple");
        System.out.println(apple);
        System.in.read();

    }
}
