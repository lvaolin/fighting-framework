package com.dhy.demo.spring.redis.configration;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
        String submitToken = this.getSubmitToken(request);
        if (submitToken==null) {
            return true;
        }
        //存在resubmit_token参数时则进行重复提交校验
        if (redisTemplate.delete(submitToken)) {
            //存在并删除成功
            return true;
        }
        returnErrorInfo(response);
        return false;
    }


    private  String getSubmitToken(HttpServletRequest request) {
        String submitToken= request.getHeader("submitToken");
        if(submitToken == null){
            submitToken = request.getParameter("submitToken");
        }
        return submitToken;
    }

    private void returnErrorInfo(HttpServletResponse response) throws IOException {
        response.setHeader("Content-Type","application/json;charset=utf-8");
        response.setCharacterEncoding("UTF-8");
        response.setStatus(200);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("errorCode","100");
        jsonObject.put("errorMsg","请勿重复提交");
        response.getWriter().print(jsonObject.toJSONString());
    }
}
