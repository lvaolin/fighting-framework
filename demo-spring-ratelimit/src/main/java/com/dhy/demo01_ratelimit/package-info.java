package com.dhy.demo01_ratelimit;

/**
 * 实现对某些 service 类 或者 方法的 动态代理
 * （日志记录、性能统计、限流、权限判断、异常处理）
 *  案例原型：项目内部想对一个第三方的sdk调用实现以上功能
 *
 *  方案思路：
 *  1、自定义注解 ratelimit（限流次数）
 *  2、对需要限流的service层方法添加ratelimit注解
 *  3、定义一个aop切面对带以上注解的方法进行额外限流、日志、性能等
 *
 **/