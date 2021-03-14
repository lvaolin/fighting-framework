package com.dhy.zookeeper;

import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.List;

/**
 * 实现对 zk 存储内容的打印
 * 原生zk api
 */
public class OutPutAll {
    static ZooKeeper zk;

    static {
        try {
            zk = new ZooKeeper("localhost:2181",5000,null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        //lsr("/");
    }

    /**
     * 列出指定path下所有孩子
     */
    public static void lsr(String path) throws Exception {
        System.out.println(path);

        List<String> list = zk.getChildren(path,null);
        //判断是否有子节点
        if(list.isEmpty() || list == null){
            return;
        }
        for(String s : list){
            //判断是否为根目录
            if(path.equals("/")){
                lsr(path + s);
            }else {
                lsr(path +"/" + s);
            }
        }
    }

}
