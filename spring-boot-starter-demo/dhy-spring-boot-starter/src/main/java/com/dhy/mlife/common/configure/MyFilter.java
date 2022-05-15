package com.dhy.mlife.common.configure;

import com.dhy.mlife.common.constant.Constants;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.ThreadContext;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@WebFilter
public class MyFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        long start = System.currentTimeMillis();
        String uri = ((HttpServletRequest) servletRequest).getRequestURI();
        ThreadContext.put(Constants.logId, UUID.randomUUID().toString());
        try {
            filterChain.doFilter(servletRequest, servletResponse);
        } finally {
            long timeCost = System.currentTimeMillis() - start;
            log.error(uri + "耗时" + timeCost + "ms");
            ThreadContext.clearAll();
        }

    }

    @Override
    public void destroy() {
        log.info("--- FilterResponseTime destroy ---");
    }
}
