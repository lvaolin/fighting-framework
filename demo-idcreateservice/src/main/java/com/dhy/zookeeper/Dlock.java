package com.dhy.zookeeper;

import org.I0Itec.zkclient.ZkClient;
import org.apache.zookeeper.CreateMode;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/**
 * 使用zookeeper实现分布式锁
 * /dhy 目录是一把分布式锁，多线程并发抢这把锁，抢到的可以开展业务操作，用完 释放。
 * 没抢到的一直等待释放后继续抢锁
 */
public class Dlock {

    public static void main(String[] args) {

        ZkClient zkClient = new ZkClient("127.0.0.1:2181");

        //
        String basePath = "/dlock";
        String lockName = "dhy";
        if (!zkClient.exists(basePath)) {
            //创建
            zkClient.create(basePath, "dlock".getBytes(), CreateMode.PERSISTENT);
        }

        CyclicBarrier cyclicBarrier = new CyclicBarrier(10);
        for (int i = 0; i <10 ; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println(Thread.currentThread().getName()+"is ready");
                        cyclicBarrier.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                    String myEphemeralSequential = zkClient.createEphemeralSequential(basePath + "/"+lockName, null);
                    String myLockName = standardFixForSorting(myEphemeralSequential,basePath + "/");
                    System.out.println(Thread.currentThread().getName()+"注册了临时节点："+myEphemeralSequential);
                    System.out.println("myLockName:"+myLockName);
                    System.out.println(Thread.currentThread().getName()+"获取所有竞争者");
                    List<String> children = zkClient.getChildren(basePath);
                    for (String child : children) {
                        System.out.println(child);
                    }
                    System.out.println(Thread.currentThread().getName()+"所有竞争者客户端本地排序");
                    getQueueList(children,lockName);
                    String first= basePath + "/"+children.get(0);
                    if (first.equals(myEphemeralSequential)) {
                        System.out.println(Thread.currentThread().getName()+"恭喜你获得了锁");
                        try {
                            TimeUnit.SECONDS.sleep(3);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        if (zkClient.delete(myEphemeralSequential)) {
                            System.out.println(Thread.currentThread().getName()+"释放锁成功");
                        }
                    }else{
                        int myIndex = children.indexOf(myLockName);
                        int myPreIndex = myIndex -1;
                        System.out.println(Thread.currentThread().getName()+"没有竞争到锁，你前面的竞争者为："+children.get(myPreIndex));

                    }
                }
            }).start();
        }




    }

    public static void getQueueList(List<String> list,String lockName){
        Collections.sort(list, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return standardFixForSorting(o1,lockName).compareTo(standardFixForSorting(o2,lockName));
            }
        });
    }



    public static String standardFixForSorting(String str, String lockName)
    {
        int index = str.lastIndexOf(lockName);
        if ( index >= 0 )
        {
            index += lockName.length();
            return index <= str.length() ? str.substring(index) : "";
        }
        return str;
    }


}
