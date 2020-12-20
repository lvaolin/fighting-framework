package com.dhy.provider;

import com.dhy.common.EnableMyConfig;
import com.dhy.demo2.MyComponent;
import com.dhy.demo.MyConfigDemo;
import com.dhy.common.MyBean;
import com.dhy.common.MyImportBean;
import com.dhy.common.MyImportBean2;
import org.apache.dubbo.spring.boot.context.DubboApplicationContextInitializer;
import org.apache.dubbo.spring.boot.context.event.WelcomeLogoApplicationListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.io.support.SpringFactoriesLoader;

@SpringBootApplication(
        scanBasePackageClasses = MyConfigDemo.class,
        scanBasePackages = {"com.dhy.demo2"}
        )
@EnableMyConfig
public class ProviderSpringApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(ProviderSpringApplication.class, args);
        MyBean bean = run.getBean(MyBean.class);
        System.out.println(bean);
        MyImportBean myImportBean = run.getBean(MyImportBean.class);
        System.out.println(myImportBean);

        System.out.println(run.getBean(MyImportBean2.class));

        System.out.println(run.getBean(MyConfigDemo.User.class));

        System.out.println(run.getBean(MyComponent.class));
    }

}
