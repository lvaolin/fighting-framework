package com.dhy.demo.spring.redis.configration;

import com.alibaba.fastjson.JSONObject;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Project parent
 * @Description 异常处理，返回json串
 * @Author lvaolin
 * @Date 2021/5/7 10:18 上午
 */
@Component
public class MyHandlerExceptionResolver implements HandlerExceptionResolver {
    @SneakyThrows
    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        httpServletResponse.setHeader("Content-Type","application/json;charset=utf-8");
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setStatus(200);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("errorCode","100");
        jsonObject.put("errorMsg",e.getMessage());
        httpServletResponse.getWriter().print(jsonObject.toJSONString());
        return null;
    }
}
