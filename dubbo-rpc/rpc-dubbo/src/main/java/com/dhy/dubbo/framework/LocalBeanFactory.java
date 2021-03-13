package com.dhy.dubbo.framework;


import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 服务实现类 本地注册表
 */
public class LocalBeanFactory {
    static LocalBeanFactory myBeanFactory = new LocalBeanFactory();
    Map<String,Object> beanMap = new ConcurrentHashMap<>();
    private LocalBeanFactory(){
        //beanMap.put(IUserServive.class.getName(),new UserServiceImpl());
    }
    public Object getBean(String beanName){
        System.out.println(beanName);
        return beanMap.get(beanName);
    }
    public static LocalBeanFactory getInstance(){
        return myBeanFactory;
    }


    public void addService(String name,Object object){
        beanMap.put(name,object);
    }
}
