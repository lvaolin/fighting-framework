package com.dhy.mlife.common.core;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class SpringContextHelper implements ApplicationContextAware {

    private static ApplicationContext context;

    public static Object getBean(String beanName) {
        return context.getBean(beanName);
    }

    public static ApplicationContext getContext() {
        return context;
    }

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        SpringContextHelper.context = context;
    }

}