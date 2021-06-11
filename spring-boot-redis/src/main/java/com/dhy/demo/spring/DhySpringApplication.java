package com.dhy.demo.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class DhySpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(DhySpringApplication.class, args);
    }

}
