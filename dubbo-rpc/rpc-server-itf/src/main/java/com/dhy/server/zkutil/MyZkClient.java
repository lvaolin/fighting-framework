package com.dhy.server.zkutil;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

public class MyZkClient {
    //会话超时时间
    private static final int SESSION_TIMEOUT = 30 * 1000;
    //连接超时时间
    private static final int CONNECTION_TIMEOUT = 3 * 1000;
    //ZooKeeper服务地址
    private static final String CONNECT_ADDR = "127.0.0.1:2181";

    //创建连接实例
    private CuratorFramework client = null;


    public MyZkClient() {
        //1 重试策略：初试时间为1s 重试10次
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 10);
        //2 通过工厂创建连接
        client = CuratorFrameworkFactory.builder()
                .connectString(CONNECT_ADDR).connectionTimeoutMs(CONNECTION_TIMEOUT)
                .sessionTimeoutMs(SESSION_TIMEOUT)
                .retryPolicy(retryPolicy)
                //命名空间           .namespace("super")
                .build();
        //3 开启连接
        client.start();
    }

    public void createNode(String path, String data) {
        //创建永久节点
        try {
            client.create().forPath(path, data.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String queryData(String path) {
        try {
            return new String(client.getData().forPath(path));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void close() {
        client.close();
    }

    private void test() throws Exception {
        System.out.println(ZooKeeper.States.CONNECTED);
        System.out.println(client.getState());

        //创建永久节点
        client.create().forPath("/curator", "data".getBytes());
        //创建永久有序节点
        client.create().withMode(CreateMode.PERSISTENT_SEQUENTIAL)
                .forPath("/curator_sequential", "data".getBytes());

        //创建临时节点
        client.create().withMode(CreateMode.EPHEMERAL)
                .forPath("/curator/ephemeral", "data".getBytes());

        //创建临时有序节点
        client.create().withMode(CreateMode.EPHEMERAL_SEQUENTIAL)
                .forPath("/curator/ephemeral_path1", "data".getBytes());

        client.create().withProtection().withMode(CreateMode.EPHEMERAL_SEQUENTIAL)
                .forPath("/curator/ephemeral_path2", "data".getBytes());

        //测试检查某个节点是否存在
        Stat stat1 = client.checkExists().forPath("/curator");
        Stat stat2 = client.checkExists().forPath("/curator2");


        System.out.println("'/curator'是否存在： " + (stat1 != null ? true : false));
        System.out.println("'/curator2'是否存在： " + (stat2 != null ? true : false));

        //获取某个节点的所有子节点
        System.out.println(client.getChildren().forPath("/"));

        //获取某个节点数据
        System.out.println(new String(client.getData().forPath("/curator")));

        //设置某个节点数据
        client.setData().forPath("/curator", "modified data".getBytes());

//        //创建测试节点
        client.create().orSetData().creatingParentContainersIfNeeded()
                .forPath("/curator/del_key1", "/curator/del_key1 data".getBytes());

        client.create().orSetData().creatingParentContainersIfNeeded()
                .forPath("/curator/del_key2", "/curator/del_key2 data".getBytes());


        client.create().forPath("/curator/del_key2/test_key", "test_key data".getBytes());

        //删除该节点
        client.delete().forPath("/curator/del_key1");

        //级联删除子节点
        client.delete().guaranteed().deletingChildrenIfNeeded().forPath("/curator/del_key2");


        client.close();
    }
}
