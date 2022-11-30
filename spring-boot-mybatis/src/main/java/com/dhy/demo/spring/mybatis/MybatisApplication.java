package com.dhy.demo.spring.mybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.net.Socket;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
//@MapperScan("com.dhy.demo.spring.mybatis.infrastructure.datebase.mybatis.mapper")
//MapperScan指定扫描包与Mapper接口类上的Mapper注解有一方存在即可
//总之让Mybatis知道Mapper接口类的位置即可
public class MybatisApplication {

    public static void main(String[] args) {
        SpringApplication.run(MybatisApplication.class, args);
        new Thread(()->{
            while (true){
                try {
                    TimeUnit.SECONDS.sleep(120);
                    Map<Socket,Exception> map= (Map<Socket, Exception>)Socket.class.getDeclaredField("socketMap").get(null);
                    System.out.println("遗留Socket连接："+map.size()+"个");
                    map.forEach((s,e)->{
                        System.out.println(s.toString()+"---->");
                        System.out.println(e.getMessage() + "---->");
                        e.printStackTrace();
                    });
                } catch (Exception e) {
                    //e.printStackTrace();
                }

            }
        }).start();
    }

}
