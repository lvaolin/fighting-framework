package com.dhy.zookeeper;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * 这是对zookeeper 原生客户端低层次 api的测试
 */
public class Zk01 {


    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        ZooKeeper zooKeeper = new ZooKeeper("localhost:2181", 5000, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                System.out.println("new ZooKeeper:"+watchedEvent.toString());
                countDownLatch.countDown();
            }
        });
        //这是一个异步的过程，所以下面 如果不等待 打印出来是  CONNECTING
        countDownLatch.await();
        System.out.println("zk客户端连接已就绪："+zooKeeper.getState());

        //覆盖默认监视
        zooKeeper.register(new MyWatcher());

        Watcher rootChildrenWatcher = new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                //监视当前path节点的子节点，如果发生 新增 修改 删除 ，则会通知
                System.out.println("zooKeeper.getChildren:" + event.toString());
            }
        };
        //获取子节点
        List<String> children = zooKeeper.getChildren("/",rootChildrenWatcher );
        for (String child : children) {
            System.out.println(child);
        }

        //创建
        String createString = zooKeeper.create("/dhy","dhy".getBytes(), ZooDefs.Ids.READ_ACL_UNSAFE, CreateMode.EPHEMERAL);
        System.out.println(createString);
        //是否存在
        Stat exists = zooKeeper.exists("/dhy", new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                System.out.println("zooKeeper.exists:"+event);
            }
        });
        System.out.println(exists);
        //删除
        zooKeeper.delete("/dhy",0);

        //创建
        zooKeeper.create("/dhy","dhy".getBytes(), ZooDefs.Ids.READ_ACL_UNSAFE, CreateMode.EPHEMERAL);
        //删除
        zooKeeper.delete("/dhy",0);

        TimeUnit.SECONDS.sleep(100);
    }

}
