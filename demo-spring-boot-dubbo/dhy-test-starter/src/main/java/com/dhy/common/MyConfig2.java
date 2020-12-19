package com.dhy.common;


import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
public class MyConfig2 {

    @Bean
    @ConditionalOnMissingBean
    MyImportBean myImportBean(){
        MyImportBean myBean = new MyImportBean();
        myBean.setId(2L);
        myBean.setName("importbean");
        return myBean;
    }

}
