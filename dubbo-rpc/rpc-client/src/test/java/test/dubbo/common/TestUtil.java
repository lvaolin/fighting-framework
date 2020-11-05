package test.dubbo.common;

import org.apache.dubbo.config.*;
import org.junit.jupiter.api.DisplayName;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author lvaolin
 * @create 2020/4/13 10:55 AM
 */
@DisplayName("单元测试工具类：获取RPC服务")
public class TestUtil {

    /**
     * application信息缓存
     */
    private static Map<String, ApplicationConfig> applicationConfigMap = new ConcurrentHashMap<>();

    /**
     * 注册中心信息缓存
     */
    private static Map<String, RegistryConfig> registryConfigMap = new ConcurrentHashMap<>();

    /**
     * 接口代理缓存
     */
    private static Map<String, ReferenceConfig> referenceConfigMap = new ConcurrentHashMap<>();

    private static Map<String, ServiceConfig> serviceConfigMap = new ConcurrentHashMap<>();

    private static String zkAddress = "zookeeper://127.0.0.1:2181";

    public static <T> T getService(Class<T> clazz) {
        ReferenceConfig<T> referenceConfig = getReferenceConfig(zkAddress, null, null, clazz);
        return referenceConfig.get();
    }

    /**
     * 将服务导出到注册中心
     * @param interfaceClass  服务接口
     * @param object  服务实例
     */
    public static void exportService(Class interfaceClass, Object object) {
        // 当前应用配置
        ApplicationConfig application = new ApplicationConfig();
        application.setName(interfaceClass.getName());
        // 连接注册中心配置
        RegistryConfig registry = new RegistryConfig();
        registry.setAddress(zkAddress);
        // 服务提供者协议配置
        ProtocolConfig protocol = new ProtocolConfig();
        protocol.setName("dubbo");
        protocol.setPort(12345);
        // 注意：ServiceConfig为重对象，内部封装了与注册中心的连接，以及开启服务端口
        // 服务提供者暴露服务配置
        // 此实例很重，封装了与注册中心的连接，请自行缓存，否则可能造成内存和连接泄漏
        ServiceConfig service = new ServiceConfig();
        service.setApplication(getApplicationConfig(interfaceClass.getName()));
        service.setRegistry(getRegistryConfig(zkAddress)); // 多个注册中心可以用setRegistries()
        service.setProtocol(protocol); // 多个协议可以用setProtocols()
        service.setInterface(interfaceClass);
        service.setRef(object);
        // 暴露及注册服务
        service.export();
    }


    /**
     * 获取服务提供者的代理对象
     *
     * @param address
     * @param version
     * @param group
     * @param tClass  服务提供者接口
     * @param <T>
     * @return
     */
    private static <T> ReferenceConfig<T> getReferenceConfig(String address, String version, String group, Class<T> tClass) {
        // 该实例很重，有必要可缓存，否则可能造成内存泄漏和连接泄漏
        if (!referenceConfigMap.containsKey(tClass.getName())) {
            ReferenceConfig<T> referenceConfig = new ReferenceConfig<>();
            referenceConfig.setInterface(tClass);
            referenceConfig.setApplication(getApplicationConfig(tClass.getName()));
            referenceConfig.setRegistry(getRegistryConfig(address));
            referenceConfig.setVersion(version);
            referenceConfigMap.put(tClass.getName(), referenceConfig);
            return referenceConfig;
        } else {
            return referenceConfigMap.get(tClass.getName());
        }


    }

    /**
     * 获取注册中心信息
     *
     * @param address 注册中心地址
     * @return
     */
    private static RegistryConfig getRegistryConfig(String address) {
        String key = address ;
        RegistryConfig registryConfig = registryConfigMap.get(key);
        if (null == registryConfig) {
            registryConfig = new RegistryConfig();
            registryConfig.setAddress(address);
            registryConfigMap.put(key, registryConfig);
        }
        return registryConfig;
    }


    private static ApplicationConfig getApplicationConfig(String name) {
        ApplicationConfig applicationConfig = applicationConfigMap.get(name);
        if (null == applicationConfig) {
            applicationConfig = new ApplicationConfig(name);
            applicationConfigMap.put(name, applicationConfig);
        }
        return applicationConfig;
    }

}
