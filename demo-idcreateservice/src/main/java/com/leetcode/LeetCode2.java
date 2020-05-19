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
        //x=123454321  div=10000000
        //找到相同位数的除数，神奇的地方在 除它可以得到最左位，余它可以得到最右位
        while (x / div >= 10) {
            div =div * 10;
        }
        //123454321
        //2345432
        //34543
        //454
        //5
        //利用的特性 取一个数的最左一位用 除法，取一个数的最右一位用求余法
        //对比最左位与最右位
        //去除最左位与最右位  用先余法后除法，真神奇
        //接着重复，只要出现 最左位与最右位 不相等 则退出
        while (x > 0) {
            int left = x / div;
            int right = x % 10;
            if (left != right) {
                return false;
            }
            x = (x % div) / 10;
            div = div/100;
        }
        return true;
    }


    public static void main(String[] args) {
        LeetCode2 leetCode2 = new LeetCode2();
        System.out.println(leetCode2.isPalindrome2(123454321));
        //leetCode2.fanzhuan(123456);
    }

    public void fanzhuan(int x){
        int div = 1;
        while (x / div >= 1){
            System.out.println(x%div);
            div *= 10;
        }


        //System.out.println(x%100);
        //System.out.println(x%1000);
    }
}
