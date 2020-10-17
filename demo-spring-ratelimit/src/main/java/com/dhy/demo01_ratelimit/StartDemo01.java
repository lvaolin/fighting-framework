package com.dhy.demo01_ratelimit;

import com.dhy.demo01_ratelimit.dto.User;
import com.dhy.demo01_ratelimit.itf.IOpenApiService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class StartDemo01 {
    public static void main(String[] args) throws Exception {
        //注解配置启动
        AnnotationConfigApplicationContext ac =
                new AnnotationConfigApplicationContext();
        ac.scan("com.dhy.demo01_ratelimit");
        ac.refresh();

        IOpenApiService apiService = ac.getBean(IOpenApiService.class);
        User user = new User();
        user.setId(1L);
        user.setName("admin");
        apiService.createUser(user);


    }
}
