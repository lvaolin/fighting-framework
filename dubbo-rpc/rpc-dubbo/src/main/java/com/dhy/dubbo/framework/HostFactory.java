package com.dhy.dubbo.framework;

import com.dhy.dubbo.cache.LocalCacheFactory;
import com.dhy.dubbo.dto.RpcRequest;

import java.util.List;

/**
 * @Title HostFactory
 * @Description
 * @Author lvaolin
 * @Date 2021/3/14 17:55
 **/
public class HostFactory {

    public static List<String> getHosts(RpcRequest rpcRequest) {

        List<String> hostList = LocalCacheFactory.hostsCache.getIfPresent("/" + rpcRequest.getApplicationName());
        if (hostList==null) {
            //获取 地址列表
            hostList = LocalCacheFactory.myZkClient.getChildren("/" + rpcRequest.getApplicationName());
            if (hostList!=null&&hostList.size()>0) {
                LocalCacheFactory.hostsCache.put("/"+rpcRequest.getApplicationName(),hostList);
            }
            System.out.println("主机列表来自注册中心");
            LocalCacheFactory.myZkClient.watchChildren("/" + rpcRequest.getApplicationName());

        }else{
            System.out.println("主机列表来自本地缓存");
        }

        hostList.stream().forEach((host)->{
            System.out.println(host);
        });
        return hostList;
    }

}
