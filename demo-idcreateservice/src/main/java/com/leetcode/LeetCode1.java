package com.leetcode;

import java.util.HashMap;
import java.util.Map;

class LeetCode1 {
    public int[] twoSum(int[] nums, int target) throws Exception {
        Map<Integer,Integer> map = new HashMap<>();
        for(int i=0;i<nums.length;i++){
            map.put(nums[i],i);
        }
        for(int j=0;j<nums.length;j++){
            int temp = target-nums[j];
            if(map.containsKey(temp)&&map.get(temp)!=j){
                return  new int[]{j,map.get(target-nums[j])};
            }
        }

        throw new Exception("没找到");


    }

    public static void main(String[] args) throws Exception {
       // [2,7,11,15]
       // 9
        int[] init = new int[]{1,3,4,2};
        int target =6;
        LeetCode1 solution =  new LeetCode1();
        int[] result = solution.twoSum(init,target);
        System.out.println(result[0]+","+result[1]);

    }
}