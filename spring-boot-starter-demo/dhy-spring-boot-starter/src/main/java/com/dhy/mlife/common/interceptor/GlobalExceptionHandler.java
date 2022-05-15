package com.dhy.mlife.common.interceptor;

import com.dhy.mlife.common.core.BusinessException;
import com.dhy.mlife.common.core.MyResponseData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    /**
     * 系统异常
     */
    @ExceptionHandler(Exception.class)
    public MyResponseData handleException(Exception e, HttpServletRequest request) {
        log.error(e.getMessage(), e);
        if (e instanceof BusinessException) {
            BusinessException b = (BusinessException) e;
            return new MyResponseData(b.getCode(), b.getMessage());
        }
        return new MyResponseData("999", e.getMessage());
    }


}