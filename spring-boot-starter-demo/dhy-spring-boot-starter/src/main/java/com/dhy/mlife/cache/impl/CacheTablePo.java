package com.dhy.mlife.cache.impl;

import lombok.Data;

import javax.persistence.Table;

/**
 * @Project spring-boot-starter-demo
 * @Description 主要用途描述
 * @Author lvaolin
 * @Date 2022/5/21 上午11:17
 */
@Table(name = "cache_table")
@Data
public class CacheTablePo {
    private String key;
    private String value;
}
