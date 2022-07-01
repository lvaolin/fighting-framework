package com.dhy.mlife.common.servlet;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @Project spring-boot-starter-demo
 * @Description http接口性能监控
 * @Author lvaolin
 * @Date 2022/6/30 下午4:58
 */
@WebFilter
@Slf4j
public class TimeFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest)servletRequest;
        String uri = req.getRequestURI();
        String txnId = req.getParameter("txnId");
        log.info("交易"+txnId+"来了");
        long startTime = System.currentTimeMillis();
        try {
            filterChain.doFilter(servletRequest,servletResponse);
        }finally {
            long timeCost = System.currentTimeMillis() - startTime;
            log.info(uri+"接口（txnId="+txnId+"）耗时："+timeCost+"ms");
        }

    }
}
