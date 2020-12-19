package com.dhy.common;


import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({MyConfig2.class,MyImportBean2.class}) //可以通过import导入其它配置类
public class MyConfig {

    @Bean
    @ConditionalOnMissingBean
    MyBean myBean(){
        MyBean myBean = new MyBean();
        myBean.setId(1L);
        myBean.setName("bean");
        return myBean;
    }

}
