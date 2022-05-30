package com.dhy.mlife.billingservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan(basePackages = {
        "com.dhy.mlife.billingservice.gateway.db.impl.mapper"
})
public class BillingApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(BillingApplication.class);
    }
    public static void main(String[] args) {
        SpringApplication.run(BillingApplication.class, args);
    }

}
