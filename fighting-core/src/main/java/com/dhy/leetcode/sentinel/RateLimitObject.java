package com.dhy.leetcode.sentinel;

/**
 * 滑动窗口算法，计算是否需要限流
 * 1秒为总长度，窗口大小为1秒钟内的窗口数量，如果为10个窗口则窗口单位大小为100毫秒
 */
public class RateLimitObject {

    private int[] arr = null;

    /**
     * 请求并发上限
     */
    private int limit = 100;
    /**
     * 窗口数量越大，精确度越高
     */
    private int windowSize = 10;
    /**
     * 头指针索引
     */
    private int headIndex = 0;
    public RateLimitObject(){
        arr = new int[windowSize];
    }
    /**
     * 1秒钟内的窗口数量
     * @param windowSize
     */
    public RateLimitObject(int windowSize){
        arr = new int[windowSize];
        this.windowSize = windowSize;
    }

    /**
     * 每一个窗口单位都会调用一次，从而实现窗口的滑动
     * @param curCount 当前窗口时间内请求数量
     * @return
     */
    public boolean isOverLimit(int curCount,int limit){

        arr[headIndex] = curCount;
        //计算下一次存放位置（环形数组）
        headIndex++;
        headIndex%=windowSize;
        //计算sum
        int sum = 0;
        for (int i = 0; i <windowSize ; i++) {
            sum += arr[i];
            System.out.print(arr[i]+",");
        }
        //是否限流
        boolean flag = sum>limit?true:false;
        System.out.println("限流："+flag+",sum:"+sum+",limit:"+limit);
        return  flag;
    }

    public boolean isOverLimit(int curCount){
        return isOverLimit(curCount,this.limit);
    }
}
