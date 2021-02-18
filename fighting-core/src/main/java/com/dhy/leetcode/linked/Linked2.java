package com.dhy.leetcode.linked;

/**
 * 删除单链表中重复的节点（一个也不保留）
 */
public class Linked2 {

    public class ListNode {
        int val;
        ListNode next = null;

        public ListNode(int val) {
            this.val = val;
        }
    }

    /**
     * @param head ListNode类
     * @return ListNode类
     */
    public ListNode deleteDuplicates(ListNode head) {
        // write code here
        if (null == head) {
            return null;
        }

        // 新建一个结点，防止头结点被删除 
        ListNode firstNode = new ListNode(-1);
        firstNode.next = head;
        // 记录前驱结点
        ListNode preHead = firstNode;
        // 记录当前遍历结点
        ListNode cur = head;

        while (null != cur && null != cur.next) {

            // 若结点值出现重复
            if (cur.val == cur.next.val) {
                int val = cur.val;
                // 删除重复结点
                while ((null != cur) && (val == cur.val)) {
                    cur = cur.next;
                }
                // 删除重复结点后，进行链接
                preHead.next = cur;
            } else {
                // 结点不重复，后移
                preHead = cur;
                cur = cur.next;
            }
        }
        return firstNode.next;

    }
}
