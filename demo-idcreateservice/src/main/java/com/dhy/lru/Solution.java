package com.dhy.lru;


import java.util.HashMap;
import java.util.Map;

public class Solution {
   /* public static void main(String[] args) {
      //  [[1,1,1],[1,2,2],[1,3,2],[2,1],[1,4,4],[2,2]],3
        int[][] operators = {{1,1,1},{1,2,2},{1,3,2},{2,1},{1,4,4},{2,2}};
        int k = 1;
        Solution solution = new Solution();
        int[] lru = solution.LRU(operators, k);
        for (int i : lru) {
            System.out.println(i);
        }
    }*/
    /**
     * lru design
     * @param operators int整型二维数组 the ops
     * @param k int整型 the k
     * @return int整型一维数组
     */
    public int[] LRU (int[][] operators, int k) {
        // write code here
        LRUCache lruCache = new LRUCache(k);

        int resultLength = 0;
        for (int[] operator : operators) {
            if (operator[0] == 2) {
                resultLength++;
            }
        }
        int[] results = new int[resultLength];
        int index = 0;

        for (int[] operator : operators) {
            switch (operator[0]){
                case 1:
                    lruCache.set(operator[1],operator[2]);
                    break;
                case 2:
                    results[index++]=lruCache.get(operator[1]);
                    break;
            }
        }

        return  results;
    }


    /**
     * hash表+双向链表实现
     * get 达到 O(1) 只有数组或者hash可以做到
     * set 达到 O(1) 只有双向链表可以做到
     */
    static class LRUCache{

        Map<Integer,Node> map = new HashMap<Integer,Node>();
        /**
         * 记录缓存大小
         */
        int maxSize ;
        /**
         * 头节点
         */
        Node head;
        /**
         * 尾节点
         */
        Node tail;
        public LRUCache(int maxSize){
            this.maxSize = maxSize;
        }
        public int get(int key){
            //根据hash找到Node节点
            Node node = map.get(key);
            if(node==null) {
                //如果不存在:则返回-1；
                return  -1;
            }
            //已存在,且不是头节点
            if(node!=head){
                //将Node.next节点连接到Node.prev节点
                //将Node节点移动到head位置
                if(node!=tail){
                    //不是尾结点的情况
                    node.next.prev = node.prev;
                    node.prev.next = node.next;
                    //---
                    node.prev = null;
                    node.next = head;
                    //---
                    head.prev = node;
                    head = node;
                }else{
                    //是尾结点的情况
                    tail = node.prev;
                    tail.next=null;
                    //---
                    node.prev = null;
                    node.next = head;
                    //---
                    head.prev = node;
                    head = node;
                }
            }
            return node.value;
        }

        public void set(int key,int value){
            //根据key找到Node节点
            Node node = map.get(key);
            if(node==null){
                //不存在则产生新节点
                node = new Node(key,value);
            }else{
                //存在则更新旧节点值
                node.value = value;
            }
            //如果为空说明是第一个存入的节点
            if(map.isEmpty()){
                //创建head和tail节点
                map.put(key,node);
                head = node;
                tail = node;
                return;
            }
            //不为空
            if(node.prev!=null||node.next!=null){
                //如果不是head则更新旧节点到head位置
                if(node!=head){
                    if(node!=tail){
                        //不是尾结点
                        node.next.prev = node.prev;
                        node.prev.next = node.next;
                        //---
                        node.prev = null;
                        node.next = head;
                        //---
                        head.prev = node;
                        head = node;
                    }else{
                        //是尾结点
                        tail = node.prev;
                        tail.next=null;
                        //---
                        node.prev = null;
                        node.next = head;
                        //---
                        head.prev = node;
                        head = node;
                    }
                }
            }else{
                //新增节点
                if(map.size()==maxSize){
                    //已满则删除tail节点
                    map.remove(tail.key);
                    if(maxSize==1){
                        head = node;
                        tail = node;
                        return;
                    }else{
                        tail = tail.prev;
                        tail.next.prev = null;
                        tail.next = null;
                    }

                }
                node.next = head;
                head.prev = node;
                head = node;
            }
            map.put(key,node);
        }

    }

    static class Node{
        Node next;
        Node prev;
        int  key;
        int  value;
        public Node(int key,int value){
            this.key = key;
            this.value = value;
        }
    }



}
