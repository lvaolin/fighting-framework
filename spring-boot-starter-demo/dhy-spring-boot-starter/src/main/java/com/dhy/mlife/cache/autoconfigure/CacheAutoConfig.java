package com.dhy.mlife.cache.autoconfigure;

import com.dhy.mlife.cache.impl.CacheService;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @Project
 * @Description
 * @Author lvaolin
 * @Date 2022/5/2 下午10:49
 */

@Configuration
@Import({
        CacheService.class
})
@MapperScan(basePackages = {
        "com.dhy.mlife.cache.impl.mapper"
})
public class CacheAutoConfig {


}
