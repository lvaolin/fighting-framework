package com.dhy.leetcode.linked;

import java.util.HashSet;

/**
 * 删除有序单链表中的重复节点
 *
 * 1-1-2-3-3-4-》 2-4
 */
public class Linked2 {
    public class ListNode {
        int val;
        ListNode next = null;
    }

    public ListNode deleteDuplicates(ListNode head) {
        if (head==null||head.next==null) {
            return head;
        }
        //遍历节点放入hashmap
        //每一个节点查询一次map，不存在则增加，存在则删除链表节点
        ListNode returnHead = head;
        ListNode nextHead = head;
        //1、 head 节点重复的情况
        //2、head 节点不重复的情况
        boolean repeat = false;
        while (true){
            repeat = false;
            while (head.next!=null&&head.next.val==head.val) {
                head = head.next;
                repeat = true;
            }

            if(head.next==null){
                return returnHead;
            }
            if (repeat) {
                head = head.next;
                returnHead = head;
            }else{
                break;
            }
        }

        return returnHead;
    }

}
