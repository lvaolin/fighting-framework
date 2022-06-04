package com.dhy.servelt4;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Project servlet4
 * @Description 过滤器
 * @Author lvaolin
 * @Date 2022/6/4 下午11:51
 */
@WebFilter("/*")
public class HelloFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println(filterConfig.getFilterName()+" init");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        long startTime = System.currentTimeMillis();
        filterChain.doFilter(request,response);
        System.out.println(request.getRequestURI()+"耗时"+(System.currentTimeMillis()-startTime)+"ms");
    }

    @Override
    public void destroy() {
        System.out.println("HelloFilter destroy");
    }
}
