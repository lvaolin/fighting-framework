package com.dhy.servelt4;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Project servlet4
 * @Description 过滤器
 * @Author lvaolin
 * @Date 2022/6/4 下午11:51
 */
@WebFilter(filterName = "helloFilter",urlPatterns = "/*",initParams = {
        @WebInitParam(name = "noLoginPaths",value = "index.jsp;fail.jsp;/LoginServlet"),
        @WebInitParam(name = "charSet",value = "utf-8")
})
public class HelloFilter extends HttpFilter {
    private String noLoginPaths ;
    private String charSet;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println(filterConfig.getFilterName()+" init");

        this.noLoginPaths = filterConfig.getInitParameter("noLoginPaths");
        this.charSet = filterConfig.getInitParameter("charSet");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        long startTime = System.currentTimeMillis();

        System.out.println(noLoginPaths);
        System.out.println(charSet);

        filterChain.doFilter(request,response);
        System.out.println(getFilterName()+":"+request.getRequestURI()+"耗时"+(System.currentTimeMillis()-startTime)+"ms");
    }

    @Override
    public void destroy() {
        System.out.println("HelloFilter destroy");
    }
}
