package com.dhy.mlife.cache.impl.mapper;

import com.dhy.mlife.cache.impl.CacheTablePo;
import tk.mybatis.mapper.common.Mapper;

/**
 * @Project spring-boot-starter-demo
 * @Description 主要用途描述
 * @Author lvaolin
 * @Date 2022/5/21 上午10:30
 */
public interface CacheTableMapper extends Mapper<CacheTablePo> {
    int set(String key, String value);
}
