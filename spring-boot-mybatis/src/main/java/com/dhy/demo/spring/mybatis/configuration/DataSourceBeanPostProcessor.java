package com.dhy.demo.spring.mybatis.configuration;

import java.lang.reflect.Method;
import java.sql.Connection;
import javax.sql.DataSource;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.config.BeanPostProcessor;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class DataSourceBeanPostProcessor implements BeanPostProcessor {
    public static Map connections = new ConcurrentHashMap();
    public static Map connectionTime = new ConcurrentHashMap();

    public Object postProcessBeforeInitialization(Object object, String name) {
        return object;
    }

    //创建DataSource或DataSource工厂的代理
    public Object postProcessAfterInitialization(Object object, String name) throws BeansException {
        if (!"dataSource".equals(name)) return object;
        System.out.println("****************DataSource postProcessAfterInitialization success ");
        if (object instanceof FactoryBean)
            return createDataSourceFactoryProxy((FactoryBean) object);
        else
            return createDataSourceProxy((DataSource) object);

    }
    
    private FactoryBean createDataSourceFactoryProxy(final FactoryBean factoryBean) {
        if (Enhancer.isEnhanced(factoryBean.getClass())) return factoryBean;
        MethodInterceptor factoryInterceptor = new MethodInterceptor() {
            public java.lang.Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws java.lang.Throwable {
                Object result = method.invoke(factoryBean, args);
                if ("getObject" != method.getName()) return result;
                return createDataSourceProxy((DataSource) result);
            }
        };
        return (FactoryBean) createProxy(FactoryBean.class, factoryInterceptor);
    }
    //拦截DataSource getConnection方法，记录获取的数据库连接
    private DataSource createDataSourceProxy(final DataSource dataSource) {
        if (Enhancer.isEnhanced(dataSource.getClass())) return dataSource;
        MethodInterceptor dataSourceInterceptor = new MethodInterceptor() {
            public java.lang.Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws java.lang.Throwable {
                Object result = method.invoke(dataSource, args);
                if ("getConnection" != method.getName()) return result;
                connections.put(result, new Exception());
                connectionTime.put(result, new java.util.Date());
                System.out.println("****************DataSource Connection  get  size = " + connections.size());
                return createConnectionProxy((Connection) result);
            }
        };
        return (DataSource) createProxy(DataSource.class, dataSourceInterceptor);
    }
    //拦截Connection close方法，清除释放的数据库连接
    private Connection createConnectionProxy(final Connection conn) {
        if (Enhancer.isEnhanced(conn.getClass())) return conn;
        MethodInterceptor connectionProxy = new MethodInterceptor() {
            public java.lang.Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws java.lang.Throwable {
                if ("close" == method.getName()) {
                    connections.remove(conn);
                    connectionTime.remove(conn);
                    System.out.println("****************DataSource Connection close size = " + connections.size());
                }
                return method.invoke(conn, args);
            }
        };
        return (Connection) createProxy(Connection.class, connectionProxy);
    }

    private Object createProxy(Class targetInterfaceClass, MethodInterceptor interceptor) {
        Enhancer enhancer = new Enhancer();
        enhancer.setInterfaces(new Class[]{targetInterfaceClass});
        enhancer.setCallback(interceptor);
        return enhancer.create();
    }

}