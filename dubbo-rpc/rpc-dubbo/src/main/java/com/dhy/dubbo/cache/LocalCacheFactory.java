package com.dhy.dubbo.cache;

import com.dhy.dubbo.register.zookeeper.zkutil.MyZkClient;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;

import java.util.List;

/**
 * @Title LocalCacheFactory
 * @Description
 * @Author lvaolin
 * @Date 2021/3/14 17:38
 **/
public class LocalCacheFactory {
    public static Cache<String, List<String>> hostsCache = CacheBuilder.newBuilder().build();
    public static MyZkClient myZkClient = new MyZkClient();
    public static Cache<String, PathChildrenCache> pathChildrenCacheCache = CacheBuilder.newBuilder().build();
}
