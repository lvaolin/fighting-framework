package com.dhy.proxy.cglib;

import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.Enhancer;

/**
 * @author lvaolin
 * @create 2020/4/14 11:02 AM
 */
public class CglibFactory {
    public static Object getInstance(Class target, Callback callback){
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(target);
        enhancer.setCallback(callback);
        return enhancer.create();
    }
}
