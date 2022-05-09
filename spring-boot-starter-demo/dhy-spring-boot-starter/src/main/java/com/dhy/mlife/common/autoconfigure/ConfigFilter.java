package com.dhy.mlife.common.autoconfigure;

import com.dhy.mlife.common.autoconfigure.config.MlifeConfig;
import com.dhy.mlife.common.constant.Constants;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.ThreadContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@Configuration
public class ConfigFilter {
    @Autowired
    ApplicationEventPublisher publisher;
    @Autowired
    MlifeConfig mlifeConfig;
    @Autowired
    FilterResponseTime filterResponseTime;
    @Bean
    @ConditionalOnMissingBean
    public FilterRegistrationBean registFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(filterResponseTime);
        registration.addUrlPatterns("/*");
        registration.setName("FilterResponseTime");
        registration.setOrder(1);
        return registration;
    }

    @Bean
    @ConditionalOnMissingBean
    public FilterRegistrationBean corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOrigin("*");
        config.setAllowCredentials(true);
        config.setMaxAge(3600L);

        config.addAllowedMethod("OPTIONS");
        config.addAllowedMethod("HEAD");
        config.addAllowedMethod("GET");
        config.addAllowedMethod("PUT");
        config.addAllowedMethod("POST");
        config.addAllowedMethod("DELETE");
        config.addAllowedMethod("PATCH");

        config.addAllowedHeader(CorsConfiguration.ALL);
        config.addExposedHeader("Access-Control-Allow-Origin");
        config.addExposedHeader("Access-Control-Allow-Credentials");

        UrlBasedCorsConfigurationSource configSource = new UrlBasedCorsConfigurationSource();
        configSource.registerCorsConfiguration("/**", config);

        FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(configSource));
        bean.setOrder(0);//利用FilterRegistrationBean，将拦截器注册靠前,避免被其它拦截器首先执行
        return bean;
    }


    static class FilterResponseTime implements Filter {
        @Autowired
        ApplicationEventPublisher publisher;
        @Autowired
        MlifeConfig mlifeConfig;
        @Override
        public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
            long start = System.currentTimeMillis();
            String uri = ((HttpServletRequest) servletRequest).getRequestURI();
            ThreadContext.put(Constants.logId, UUID.randomUUID().toString());
            try {
                filterChain.doFilter(servletRequest, servletResponse);
            } finally {
                long timeCost  = System.currentTimeMillis() - start;
                if (timeCost>mlifeConfig.getTimeCost()) {
                    log.error(uri + "耗时" + timeCost + "ms,慢接口,请关注");
                    eventMonitor(timeCost,uri);
                }else {
                    log.error(uri + "耗时" + timeCost + "ms");
                }

                ThreadContext.clearAll();
            }

        }

        private void eventMonitor(long timeCost, String uri) {
            try {
                RpcCallBlockEvent.RpcCallBlockDto dto = new RpcCallBlockEvent.RpcCallBlockDto("HTTP慢接口", timeCost, ThreadContext.get(Constants.logId), uri + ",耗时:" + timeCost);
                dto.setUrl(uri);
                RpcCallBlockEvent rpcCallBlockEvent = new RpcCallBlockEvent(dto);
                publisher.publishEvent(rpcCallBlockEvent);
            }catch (Throwable e){
                e.printStackTrace();
            }
        }

        @Override
        public void destroy() {
            log.info("--- FilterResponseTime destroy ---");
        }
    }
}
