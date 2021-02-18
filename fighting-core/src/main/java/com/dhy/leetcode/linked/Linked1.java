package com.dhy.leetcode.linked;

/**
 * 输入链表头节点，奇数长度返回中点，偶数长度返回上中点
 */
public class Linked1 {


    static class Node {
        public int value;
        public Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    /**
     * 思路：快慢指针解决,快指针一次2步，慢指针一次1步
     * 1-->2-->3-->4-->5
     *
     * @param head
     * @return
     */

    public static Node getMid1(Node head) {
        if (head == null || head.next == null || head.next.next == null) {
            return head;
        }
        Node slow = head;
        Node fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }


    public static void main(String[] args) {
        Node a1 = new Node(1);
        Node a2 = new Node(2);
        Node a3 = new Node(3);
        Node a4 = new Node(4);
        Node a5 = new Node(5);
        Node a6 = new Node(6);
        Node a7 = new Node(7);
        a1.next = a2;
        a2.next = a3;
        a3.next = a4;
        a4.next = a5;
        a5.next = a6;
        a6.next = a7;
        a7.next = null;
        Node mid = getMid1(a1);
        System.out.println(mid.value);
    }
}
