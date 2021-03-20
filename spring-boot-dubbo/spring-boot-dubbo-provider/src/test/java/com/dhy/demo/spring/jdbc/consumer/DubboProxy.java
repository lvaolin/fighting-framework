package com.dhy.demo.spring.jdbc.consumer;

import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;

/**
 * @Title DubboRPC
 * @Description
 * @Author lvaolin
 * @Date 2021/3/20 18:38
 **/
public class DubboProxy<T> {

    public  T getService(Class<T> clazz){
        RegistryConfig registryConfig = new RegistryConfig();
        registryConfig.setProtocol("zookeeper");
        registryConfig.setAddress("127.0.0.1");
        registryConfig.setPort(2181);

        ApplicationConfig applicationConfig = new ApplicationConfig();
        applicationConfig.setName("user-service");
        applicationConfig.setRegistry(registryConfig);

        ReferenceConfig<T> referenceConfig = new ReferenceConfig<>();
        referenceConfig.setInterface(clazz);
        referenceConfig.setRegistry(registryConfig);
        referenceConfig.setApplication(applicationConfig);

        return referenceConfig.get();
    }

}
