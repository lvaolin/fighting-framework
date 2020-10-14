package com.dhy.demo01.configuration;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 针对 所有 Mapper接口的工厂类
 */
public class MyMapperFactoryBean<T> implements FactoryBean<T> {
    private Class<T> mapperInterface;
    public MyMapperFactoryBean() {
    }

    public MyMapperFactoryBean(Class<T> mapperInterface) {
        this.mapperInterface = mapperInterface;
    }
    @Override
    public T getObject() throws Exception {
        //通过jdk动态代理生成一个代理对象
        return (T)Proxy.newProxyInstance(this.getClass().getClassLoader(),new Class[] {mapperInterface } , new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                String[] value = method.getAnnotation(RequestMapping.class).value();
                String sql = value[0];
                //执行sql语句
                System.out.println("执行了sql："+sql);
                //返回查询结果
                return new Object();
            }
        });
    }

    @Override
    public Class<?> getObjectType() {
        return mapperInterface;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
