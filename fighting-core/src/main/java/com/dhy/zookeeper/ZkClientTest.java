package com.dhy.zookeeper;

import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.IZkStateListener;
import org.I0Itec.zkclient.ZkClient;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.Watcher;

import java.sql.Time;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 对 zk 的高层次封装客户端 zkclient 的测试
 */
public class ZkClientTest {

    public static void main(String[] args) {

        ZkClient zkClient = new ZkClient("127.0.0.1:2181");

        String path = "/dhy";
        if (zkClient.exists(path)) {
            //存在则删除
            zkClient.deleteRecursive(path);
        }
        //创建
        zkClient.create(path, "dhy".getBytes(), CreateMode.PERSISTENT);

        //存在则删除
        if (zkClient.exists(path+"/a")) {
            zkClient.delete(path+"/a");
        }
        //创建
        zkClient.create(path+"/a", "a".getBytes(), CreateMode.PERSISTENT);

        //存在则删除
        if (zkClient.exists(path+"/b")) {
            zkClient.delete(path+"/b");
        }
        zkClient.create(path+"/b", "b".getBytes(), CreateMode.PERSISTENT);

        //递归创建
        String longPath = "/dhy/c/1/2/3/4/5";
        zkClient.createPersistent(longPath,true);
        //递归删除
        zkClient.deleteRecursive("/dhy/c");

        //订阅 监视
        zkClient.subscribeChildChanges(path, new MyZkChildListener());
        //订阅 监视 dubbo
        zkClient.subscribeChildChanges("/dubbo", new MyZkChildListener());
        //订阅 监视 dubbo:com.ttk.edf.operation.manage.itf.IQueryService
        zkClient.subscribeChildChanges("/dubbo/com.ttk.edf.operation.manage.itf.IQueryService", new MyZkChildListener());
        //订阅 监视 dubbo:com.ttk.edf.operation.manage.itf.IQueryService/
        zkClient.subscribeChildChanges("/dubbo/com.ttk.edf.operation.manage.itf.IQueryService/providers", new MyZkChildListener());

        try {
            TimeUnit.SECONDS.sleep(600);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


    static class MyZkChildListener implements IZkChildListener{
        @Override
        public void handleChildChange(String parentPath, List<String> currentChilds) throws Exception {
            System.out.println("MyZkChildListener:"+parentPath);
            for (String currentChild : currentChilds) {
                System.out.println(currentChild);
            }
        }
    }
    static class MyZkDataListener implements IZkDataListener{

        @Override
        public void handleDataChange(String dataPath, Object data) throws Exception {
            System.out.println(dataPath+"数据已经变更");
        }

        @Override
        public void handleDataDeleted(String dataPath) throws Exception {
            System.out.println(dataPath+"已经删除");
        }
    }


    static class MyZkStateListener implements IZkStateListener{

        @Override
        public void handleStateChanged(Watcher.Event.KeeperState state) throws Exception {

        }

        @Override
        public void handleNewSession() throws Exception {

        }

        @Override
        public void handleSessionEstablishmentError(Throwable error) throws Exception {

        }
    }
}
