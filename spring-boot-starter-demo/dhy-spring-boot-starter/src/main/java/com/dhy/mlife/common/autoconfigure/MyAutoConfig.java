package com.dhy.mlife.common.autoconfigure;

import com.dhy.mlife.common.aop.ControllerAop;
import com.dhy.mlife.common.configure.ConfigServer;
import com.dhy.mlife.common.configure.ConfigWebMvc;
import com.dhy.mlife.common.configure.MyFilter;
import com.dhy.mlife.common.configure.MySessionListener;
import com.dhy.mlife.common.configure.config.MlifeConfig;
import com.dhy.mlife.common.core.SpringContextHelper;
import com.dhy.mlife.common.interceptor.GlobalExceptionHandler;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;

/**
 * @Project
 * @Description
 * @Author lvaolin
 * @Date 2022/5/2 下午10:49
 */

@Configuration
@EnableConfigurationProperties({MlifeConfig.class})
@Import({
        GlobalExceptionHandler.class,
        ControllerAop.class,
        SpringContextHelper.class,
        MyFilter.class,
        MySessionListener.class,
        ConfigServer.class,
        ConfigWebMvc.class
})

public class MyAutoConfig {


}
