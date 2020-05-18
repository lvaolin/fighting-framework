package com.leetcode;

public class LeetCode2 {

    /**
     * 转字符串解法
     * @param x
     * @return
     */
        public boolean isPalindrome(int x) {
            char[] xa = String.valueOf(x).toCharArray();
            for (int i = 0; i <=xa.length/2-1 ; i++) {
                if(xa[i]!=xa[xa.length-1-i]){
                    return false;
                }
            }
            return true;
        }

    /**
     * 翻转数字解法
     * @param x
     * @return
     */
    public boolean isPalindrome2(int x) {
        //边界判断
        if (x < 0) return false;
        int div = 1;
        //
        while (x / div >= 10) div *= 10;
        while (x > 0) {
            int left = x / div;
            int right = x % 10;
            if (left != right) return false;
            x = (x % div) / 10;
            div /= 100;
        }
        return true;
    }


    public static void main(String[] args) {
        LeetCode2 leetCode2 = new LeetCode2();
        System.out.println(leetCode2.isPalindrome(103301));
    }
}
