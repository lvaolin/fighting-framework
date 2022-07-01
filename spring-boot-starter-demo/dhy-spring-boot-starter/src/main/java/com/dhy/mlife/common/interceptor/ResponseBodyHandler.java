package com.dhy.mlife.common.interceptor;

import com.alibaba.fastjson.JSON;
import com.dhy.mlife.common.core.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @Project spring-boot-starter-demo
 * @Description 拦截Controller层的响应报文
 * @Author lvaolin
 * @Date 2022/6/30 下午4:53
 */
@RestControllerAdvice
@Slf4j
public class ResponseBodyHandler implements ResponseBodyAdvice {
    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        if (body instanceof String) {
            log.info((String) body);
        }
        if (body instanceof Result) {
            log.info(JSON.toJSONString(body));
        }
        return body;
    }
}
