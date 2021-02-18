package com.dhy.zookeeper;

import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.ZkClient;
import org.apache.curator.RetryPolicy;
import org.apache.curator.RetrySleeper;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.*;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.data.Stat;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class CuratorTest {
    public static void main(String[] args) throws Exception {
        CuratorFramework curatorFramework = CuratorFrameworkFactory.newClient("127.0.0.1:2181", new RetryPolicy() {
            @Override
            public boolean allowRetry(int i, long l, RetrySleeper retrySleeper) {
                return true;
            }
        });
        curatorFramework.start();
        String path = "/dhy2";
        Stat stat = curatorFramework.checkExists().forPath(path);
        if (stat!=null) {
            //递归删除
            curatorFramework.delete().deletingChildrenIfNeeded().forPath(path);
        }
        //创建
        curatorFramework.create().forPath(path);

        curatorFramework.create().forPath(path+"/a");
        curatorFramework.create().forPath(path+"/b");
        curatorFramework.create().forPath(path+"/c");
        curatorFramework.create().forPath(path+"/d");

        TreeCache treeCache = new TreeCache(curatorFramework,"/");
        treeCache.start();
        treeCache.getListenable().addListener(new TreeCacheListener() {
            @Override
            public void childEvent(CuratorFramework curatorFramework, TreeCacheEvent treeCacheEvent) throws Exception {
                System.out.println("treeCacheEvent:"+treeCacheEvent.toString());
            }
        });

        NodeCache nodeCache = new NodeCache(curatorFramework,path,true);
        nodeCache.start();
        nodeCache.getListenable().addListener(new NodeCacheListener() {
            @Override
            public void nodeChanged() throws Exception {
                System.out.println(path+"节点内容数据变更为:"+nodeCache.getCurrentData());
            }
        });

        PathChildrenCache pathChildrenCache = new PathChildrenCache(curatorFramework,path,true);
        pathChildrenCache.start();
        pathChildrenCache.getListenable().addListener(new PathChildrenCacheListener() {
            @Override
            public void childEvent(CuratorFramework curatorFramework, PathChildrenCacheEvent pathChildrenCacheEvent) throws Exception {
                System.out.println("变化:"+pathChildrenCacheEvent.toString());
                List<ChildData> currentData = pathChildrenCache.getCurrentData();
                for (ChildData currentDatum : currentData) {
                    System.out.println(currentDatum.toString());
                }
            }
        });



        try {
            TimeUnit.SECONDS.sleep(600);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
