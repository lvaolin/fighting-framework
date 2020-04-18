package com.dhy.aop.demo4cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * 代理类
 *
 * @author lvaolin
 * @create 2020/1/6 4:13 PM
 */
public class CglibProxy implements MethodInterceptor{
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("before-------");
        Object ret = methodProxy.invokeSuper(o,objects);
        System.out.println("after-------");
        return ret;
    }

    public  Object createProxy(Class<?> target){
        Enhancer enhancer = new Enhancer();
        //设置要代理的目标类对象
        enhancer.setSuperclass(target);
        //设置callback
        enhancer.setCallback(this);
        //如果你希望CGLIB创建一个有参数的实例，你应该使用net.sf.cglib.proxy.Enhancer.create(Class[], Object[])。
        //该方法的第一个参数指明参数类型，第二个参数指明参数值。参数中的原子类型需要使用包装类。
        return enhancer.create();
    }
}
