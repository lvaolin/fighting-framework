package com.dhy.demo01.configuration;

import com.dhy.demo01.dto.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean
    public User user(){
       return new User();
   }
}
