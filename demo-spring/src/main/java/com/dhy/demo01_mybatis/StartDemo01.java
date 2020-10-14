package com.dhy.demo01_mybatis;

import com.dhy.demo01_mybatis.dto.User;
import com.dhy.demo01_mybatis.mapper.UserMapper;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class StartDemo01 {
    public static void main(String[] args) throws Exception {
        //注解配置启动
        AnnotationConfigApplicationContext ac =
                new AnnotationConfigApplicationContext();
        ac.scan("com.dhy.demo01");
        ac.refresh();
        User bean = ac.getBean(User.class);
        System.out.println(bean);
        Object myFactoryBean1 = ac.getBean("userMapperFactoryBean");
        Object myFactoryBean2 = ac.getBean("userMapperFactoryBean");
        System.out.println("myFactoryBean1==myFactoryBean2:"+(myFactoryBean1==myFactoryBean2));
        UserMapper userMapper = ac.getBean("userMapperFactoryBean", UserMapper.class);
        User user = userMapper.getUser();
        System.out.println(user.getName()+user.getAddress());
        System.out.println("okl12");


    }
}
