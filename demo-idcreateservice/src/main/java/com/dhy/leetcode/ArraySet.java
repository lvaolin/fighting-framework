package com.dhy.leetcode;

import java.util.ArrayList;
import java.util.Collections;

/**
 * https://www.nowcoder.com/practice/c333d551eb6243e0b4d92e37a06fbfc9?tpId=117&&tqId=34948&rp=1&ru=/ta/job-code-high&qru=/ta/job-code-high/question-ranking
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
//回溯法
public class ArraySet {

    public static void main(String[] args) {
        int[] a = new int[]{1,2,3};

        ArraySet arraySet = new ArraySet();
        ArrayList<ArrayList<Integer>> subsets = arraySet.subsets(a);
        System.out.println("");
    }

    private ArrayList<ArrayList<Integer>> res = new ArrayList<>();
    public ArrayList<ArrayList<Integer>> subsets(int[] S) {
        if(S==null || S.length == 0) return res;
        backTrack(S, 0, new ArrayList<Integer>());
        return res;
    }
    private void backTrack(int[] S, int start, ArrayList<Integer> list){
        res.add(new ArrayList<Integer>(list));
        for(int i = start; i <S.length; i++){
            list.add(S[i]);
            backTrack(S, i + 1, list);
            list.remove(list.size() - 1);
        }
    }
}
