package com.dhy.leetcode.ratelimit;
import java.util.*;
/**
 *给定一个数组和滑动窗口的大小，找出所有滑动窗口里数值的最大值。
 * 例如，如果输入数组{2,3,4,2,6,2,5,1}及滑动窗口的大小3，
 * 那么一共存在6个滑动窗口，他们的最大值分别为{4,4,6,6,6,5}；
 * 针对数组{2,3,4,2,6,2,5,1}的滑动窗口有以下6个： {[2,3,4],2,6,2,5,1}，
 * {2,[3,4,2],6,2,5,1}， {2,3,[4,2,6],2,5,1}， {2,3,4,[2,6,2],5,1}，
 * {2,3,4,2,[6,2,5],1}， {2,3,4,2,6,[2,5,1]}。
 * 窗口大于数组长度的时候，返回空
 */
public class RateLimitForMoveWindow {

    public static void main(String[] args) {
        int[] num = {2,3,4,2,6,2,5,1};
        int size = 3;
        maxInWindows(num,size);
    }
    //[4,4,6,6,6,5]
    public static ArrayList<Integer> maxInWindows(int [] num, int size)
    {
        //双端队列 Deque
        //如果新增的元素比最后一个元素大，则删除最后一个元素，
        //依次向前找，直到找到比他大的元素或者自己成为头结点
        //如果新增的元素比最后一个元素小，则直接追加到尾部
        //队首永远是队列的最大值，单调递减队列
        //存储索引，可以判断最大值是否过期
        //直到入队完毕
        ArrayList<Integer> result = new ArrayList<>();
        if(size>num.length)  return result;
        Deque<Integer> deque = new LinkedList<>();
        int L = 0;
        int R = size -1;
        //第一个窗口入队
        for(int i=L;i<=R;i++){
            if(deque.isEmpty()){
                deque.offerFirst(i);
            }else{
                while(true){
                    if(num[i]<=num[deque.peekLast()] ){
                        deque.offerLast(i);
                        break;
                    }else{
                        deque.pollLast();
                        if(deque.isEmpty()){
                            deque.offerFirst(i);
                            break;
                        }
                    }
                }

            }

        }
        while(R<num.length){
            if(deque.peekFirst()<L){
                deque.pollFirst();
            }else{
                result.add(num[deque.peekFirst()]);
                L++;
                R++;
                if(R>=num.length) break;

                if(deque.isEmpty()){
                    deque.offerFirst(R);
                }else{
                    while(true){
                        if(num[R]<=num[deque.peekLast()] ){
                            deque.offerLast(R);
                            break;
                        }else{
                            deque.pollLast();
                            if(deque.isEmpty()){
                                deque.offerFirst(R);
                                break;
                            }
                        }
                    }

                }
            }
        }

        return result;

    }
}
