package com.dhy.demo01_mybatis;

import com.dhy.demo01_mybatis.dto.User;
import com.dhy.demo01_mybatis.itf.IAccountService;
import com.dhy.demo01_mybatis.itf.IUserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * service 层 使用 代理后的 Mapper 获取数据
 */
public class StartDemo03 {
    public static void main(String[] args) throws Exception {
        //注解配置启动
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext();
        ac.scan("com.dhy.demo01_mybatis");
        ac.refresh();
        IAccountService service = ac.getBean(IAccountService.class);
        Object object = service.getAccount(1L);
        System.out.println(object);

        IUserService userService = ac.getBean(IUserService.class);
        Object user = userService.getUser(1L);
        System.out.println(user);
    }
}
