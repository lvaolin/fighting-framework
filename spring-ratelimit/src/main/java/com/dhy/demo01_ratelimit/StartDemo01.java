package com.dhy.demo01_ratelimit;

import com.dhy.demo01_ratelimit.dto.User;
import com.dhy.demo01_ratelimit.itf.IOpenApiService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Random;
import java.util.concurrent.TimeUnit;

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
        //apiService.createUser(user);
        //apiService.updateUser(user);
        //apiService.deleteUser(1L);
        for (int i = 0; i <100 ; i++) {
            //TimeUnit.MILLISECONDS.sleep(new Random().nextInt(1000));
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true){
                        apiService.queryUser(1L);
                        try {
                            TimeUnit.MILLISECONDS.sleep(new Random().nextInt(10000));
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();

        }


    }
}
