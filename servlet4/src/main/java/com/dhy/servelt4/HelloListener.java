package com.dhy.servelt4;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * @Project servlet4
 * @Description 监听器
 * @Author lvaolin
 * @Date 2022/6/4 下午11:58
 */
@WebListener
public class HelloListener implements HttpSessionListener {
    public void sessionCreated(HttpSessionEvent se) {
        System.out.println(se.getSession().getId()+" sessionCreated");
    }

    public void sessionDestroyed(HttpSessionEvent se) {
        System.out.println(se.getSession().getId()+" sessionDestroyed");
    }
}
