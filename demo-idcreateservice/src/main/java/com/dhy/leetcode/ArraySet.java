package com.dhy.leetcode;

import java.util.ArrayList;
import java.util.Collections;

/**
 * 美团面试题
 * 题目描述
 * 现在有一个没有重复元素的整数集合S，求S的所有子集
 * 注意：
 * 你给出的子集中的元素必须按升序排列
 * 给出的解集中不能出现重复的元素
 * 例如：
 * 如果S=[1,2,3], 给出的解集应为：
 * [
 * [3],
 * [1],
 * [2],
 * [1,2,3],
 * [1,3],
 * [2,3],
 * [1,2],
 * []
 * ]
 */
public class ArraySet {

    public static void main(String[] args) {
        int[] a = new int[]{1,2,3,4};
        ArrayList<ArrayList<Integer>> subsets = subsets(a);
        System.out.println("");
    }

    public static ArrayList<ArrayList<Integer>> subsets(int[] S) {
        ArrayList<ArrayList<Integer>> result = new  ArrayList<>();
        result.add(new ArrayList<Integer>());
        for(int i=0;i<S.length;i++){
            ArrayList<Integer> temp= new ArrayList<>();
            for(int j=i;j<S.length;j++){
                if(temp.size()>0){
                    temp = new ArrayList<>(temp);
                }
                temp.add(S[j]);
                Collections.sort(temp);
                result.add(temp);
            }
        }
        return result;
    }
}
