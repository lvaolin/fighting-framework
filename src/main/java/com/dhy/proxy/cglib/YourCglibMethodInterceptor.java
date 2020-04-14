package com.dhy.proxy.cglib;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author lvaolin
 * @create 2020/4/14 10:50 AM
 */
public class YourCglibMethodInterceptor implements MethodInterceptor {
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("你饭前洗手");
        Object result = methodProxy.invokeSuper(o,objects);
        System.out.println("你饭后漱口");
        return result;
    }

}
