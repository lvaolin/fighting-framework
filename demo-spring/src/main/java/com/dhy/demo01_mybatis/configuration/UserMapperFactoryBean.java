package com.dhy.demo01_mybatis.configuration;

import com.dhy.demo01_mybatis.annotation.MySelect;
import com.dhy.demo01_mybatis.dto.User;
import com.dhy.demo01_mybatis.mapper.UserMapper;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 针对 UserMapper接口的工厂类
 * 实际应用中肯定需要多个Mapper接口共享一个FactoryBean类，所以想办法把Mapper接口弄成动态的
 */
@Component
public class UserMapperFactoryBean implements FactoryBean {
    @Override
    public Object getObject() throws Exception {
        //通过jdk动态代理生成一个代理对象
        return Proxy.newProxyInstance(this.getClass().getClassLoader(),new Class[] { UserMapper.class } , new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                String sql = method.getAnnotation(MySelect.class).value();
                //执行sql语句
                //返回查询结果
                User user = new User();
                user.setName("lval");
                user.setAddress("河北省");
                return user;
            }
        });
    }

    @Override
    public Class<?> getObjectType() {
        return UserMapper.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
