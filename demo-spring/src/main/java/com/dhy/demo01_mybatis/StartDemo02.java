package com.dhy.demo01_mybatis;

import com.dhy.demo01_mybatis.mapper.AccountMapper;
import com.dhy.demo01_mybatis.mapper.OrderMapper;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 测试 共享 MyMapperFactoryBean
 */
public class StartDemo02 {
    public static void main(String[] args) throws Exception {
        //注解配置启动
        AnnotationConfigApplicationContext ac =
                new AnnotationConfigApplicationContext();
        ac.scan("com.dhy.demo01_mybatis");
        ac.refresh();
        OrderMapper orderMapper = ac.getBean(OrderMapper.class);
        orderMapper.getOrder();
        AccountMapper accountMapper = ac.getBean(AccountMapper.class);
        accountMapper.getAccount();

        AccountMapper accountMapper2 = ac.getBean(AccountMapper.class);
        accountMapper2.getAccount();

        System.out.println(accountMapper==accountMapper2);

        System.out.println("okl12");


    }
}
