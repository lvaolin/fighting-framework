package com.dhy.demo03_mybatis;

import com.dhy.demo03_mybatis.service.IUserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SpringConfig.class);

        IUserService iUserService = ac.getBean(IUserService.class);
        System.out.println(iUserService.getUser(1L).toString());

    }
}
