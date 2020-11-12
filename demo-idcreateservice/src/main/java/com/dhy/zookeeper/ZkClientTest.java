package com.dhy.zookeeper;

import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.ZkClient;
import org.apache.zookeeper.CreateMode;

import java.util.List;

public class ZkClientTest {

    public static void main(String[] args) {

        ZkClient zkClient = new ZkClient("127.0.0.1:2181");
        String path = "/dhy";
        if (zkClient.exists(path)) {
            zkClient.deleteRecursive(path);
        }
        String s = zkClient.create(path, "dhy".getBytes(), CreateMode.PERSISTENT);
        System.out.println("zkClient.create:"+s);

        zkClient.subscribeChildChanges(path, new MyZkChildListener());

        if (zkClient.exists(path+"/a")) {
            zkClient.delete(path+"/a");
        }
        String a = zkClient.create(path+"/a", "a".getBytes(), CreateMode.PERSISTENT);
        System.out.println("zkClient.create:"+a);
        if (zkClient.exists(path+"/b")) {
            zkClient.delete(path+"/b");
        }
        String b = zkClient.create(path+"/b", "b".getBytes(), CreateMode.PERSISTENT);
        System.out.println("zkClient.create:"+b);

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
}
