package com.dhy.mlife.common.autoconfigure;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * @Project billing-service-new
 * @Description session创建与注销监听
 * @Author lvaolin
 * @Date 2022/5/5 下午3:38
 */
@WebListener
public class MySessionListener implements HttpSessionListener {
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        System.out.println(se.getSession().getId()+"会话创建");
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        System.out.println(se.getSession().getId()+"会话注销");
    }
}
