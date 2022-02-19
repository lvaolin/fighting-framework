package com.dhy.demo.spring.mybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@MapperScan("com.dhy.demo.spring.mybatis.infrastructure.datebase.mybatis.mapper")
//MapperScan指定扫描包与Mapper接口类上的Mapper注解有一方存在即可
//总之让Mybatis知道Mapper接口类的位置即可
public class MybatisApplication {

    public static void main(String[] args) {
        SpringApplication.run(MybatisApplication.class, args);
    }

}
