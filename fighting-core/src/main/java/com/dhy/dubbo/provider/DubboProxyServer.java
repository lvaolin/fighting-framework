package com.dhy.dubbo.provider;

import org.apache.dubbo.config.*;

/**
 * @Title DubboRPC
 * @Description
 * @Author lvaolin
 * @Date 2021/3/20 18:38
 **/
public class DubboProxyServer<T> {


  public void exportService(T ref){
      RegistryConfig registryConfig = new RegistryConfig();
      registryConfig.setProtocol("zookeeper");
      registryConfig.setAddress("127.0.0.1");
      registryConfig.setPort(2181);

      ApplicationConfig applicationConfig = new ApplicationConfig();
      applicationConfig.setName("user-service-provider");
      applicationConfig.setRegistry(registryConfig);

      ProtocolConfig protocolConfig = new ProtocolConfig();
      protocolConfig.setName("dubbo");
      protocolConfig.setPort(23800);

      ProviderConfig providerConfig = new ProviderConfig();
      providerConfig.setTimeout(10000);

      ServiceConfig<T> serviceConfig = new ServiceConfig();
      serviceConfig.setRegistry(registryConfig);
      serviceConfig.setProtocol(protocolConfig);
      serviceConfig.setProvider(providerConfig);
      serviceConfig.setApplication(applicationConfig);
      serviceConfig.setInterface(ref.getClass().getInterfaces()[0]);
      serviceConfig.setRef(ref);

      serviceConfig.export();


  }

}
