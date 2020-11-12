package com.dhy.zookeeper;

import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.ZkClient;
import org.apache.curator.RetryPolicy;
import org.apache.curator.RetrySleeper;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
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
        Stat stat = curatorFramework.checkExists().forPath(path);
        if (stat==null) {
            curatorFramework.create().forPath(path);
        }
        curatorFramework.watchers().add().usingWatcher(new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                System.out.println("watch /dhy:"+watchedEvent.toString());
            }
        }).forPath(path);

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
