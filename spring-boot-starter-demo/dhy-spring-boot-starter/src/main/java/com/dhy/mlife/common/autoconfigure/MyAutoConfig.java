package com.dhy.mlife.common.autoconfigure;

import com.dhy.mlife.common.aop.ControllerAop;
import com.dhy.mlife.common.configure.ConfigServer;
import com.dhy.mlife.common.configure.ConfigWebMvc;
import com.dhy.mlife.common.configure.MyFilter;
import com.dhy.mlife.common.configure.MySessionListener;
import com.dhy.mlife.common.configure.config.MlifeConfig;
import com.dhy.mlife.common.core.SpringContextHelper;
import com.dhy.mlife.common.interceptor.GlobalExceptionHandler;
import com.dhy.mlife.common.interceptor.ResponseBodyHandler;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
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
        ResponseBodyHandler.class,
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
