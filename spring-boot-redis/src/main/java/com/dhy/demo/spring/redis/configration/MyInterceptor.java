package com.dhy.demo.spring.redis.configration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Title 拦截器   认证、鉴权、审计日志、响应数据格式包装
 * @Description
 * @Author lvaolin
 * @Date 2021/4/3 17:20
 **/
@Component
public class MyInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private StringRedisTemplate redisTemplate;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        String resubmitToken = this.getResubmitToken(request);
        if (resubmitToken==null) {
            return true;
        }
        //存在resubmit_token参数时则进行重复提交校验（有效期20分钟）
        if (redisTemplate.delete(resubmitToken)) {
            return true;
        }
        throw new Exception("请勿重复提交");

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    private  String getResubmitToken(HttpServletRequest request) {
        String resubmit_token= request.getHeader("resubmit_token");
        if(resubmit_token == null){
            resubmit_token = request.getParameter("resubmit_token");
        }
        return resubmit_token;
    }
}
