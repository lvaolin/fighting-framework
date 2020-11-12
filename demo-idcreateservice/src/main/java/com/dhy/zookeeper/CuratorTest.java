package com.dhy.zookeeper;

import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.ZkClient;
import org.apache.curator.RetryPolicy;
import org.apache.curator.RetrySleeper;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.data.Stat;

import java.util.List;

public class CuratorTest {
    public static void main(String[] args) throws Exception {
        CuratorFramework curatorFramework = CuratorFrameworkFactory.newClient("127.0.0.1:2181", new RetryPolicy() {
            @Override
            public boolean allowRetry(int i, long l, RetrySleeper retrySleeper) {
                return true;
            }
        });
        curatorFramework.start();
        String path = "/dhy";
        curatorFramework.delete().deletingChildrenIfNeeded().forPath(path);
        Stat stat = curatorFramework.checkExists().forPath(path);
        if (stat==null) {
            curatorFramework.create().forPath(path);
        }

        PathChildrenCache pathChildrenCache = new PathChildrenCache(curatorFramework,path,true);
        pathChildrenCache.start();
        pathChildrenCache.getListenable().addListener(new PathChildrenCacheListener() {
            @Override
            public void childEvent(CuratorFramework curatorFramework, PathChildrenCacheEvent pathChildrenCacheEvent) throws Exception {
                System.out.println("变化:"+pathChildrenCacheEvent.toString());
            }
        });

        curatorFramework.create().forPath(path+"/a");
        curatorFramework.create().forPath(path+"/b");
        curatorFramework.create().forPath(path+"/c");
        curatorFramework.create().forPath(path+"/d");
        List<String> list = curatorFramework.getChildren().forPath(path);
        for (String s : list) {
            System.out.println(s);
        }


    }


}
