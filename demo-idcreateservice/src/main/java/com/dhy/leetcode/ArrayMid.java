package com.dhy.leetcode;
import  java.util.*;

/**
 * 如何得到一个数据流中的中位数？如果从数据流中读出奇数个数值，
 * 那么中位数就是所有数值排序之后位于中间的数值。
 * 如果从数据流中读出偶数个数值，那么中位数就是所有数值排序之后中间两个数的平均值。
 * 我们使用Insert()方法读取数据流，使用GetMedian()方法获取当前读取数据的中位数。
 */
public class ArrayMid {

    public static void main(String[] args) {
        ArrayMid arrayMid = new ArrayMid();
        //[5,2,3,4,1,6,7,0,8]
        arrayMid.Insert(5);
        System.out.println(arrayMid.GetMedian());
        arrayMid.Insert(2);
        System.out.println(arrayMid.GetMedian());
        arrayMid.Insert(3);
        System.out.println(arrayMid.GetMedian());
        arrayMid.Insert(4);
        System.out.println(arrayMid.GetMedian());
    }
    //第一版  每次插入都用插入排序来排序
    List<Integer> list = new ArrayList<>();
    public void Insert(Integer num) {
        if(list.isEmpty()) {
            list.add(num);
            return;
        }
        int size = list.size();
        for(int i=size-1;i>=0;i--){
            if(list.get(i)<=num){
                list.add(i+1,num);
                break;
            }else if(i==0){
                list.add(0,num);
                break;
            }
        }
    }

    public Double GetMedian() {
        if(list.size()==1) return list.get(0)+0d;

        int index = list.size()/2;
        if(list.size()%2==0){
            //偶数个
            return  (list.get(index-1)+list.get(index))/2d;
        }else{
            //奇数个
            return list.get(index)+0d;
        }


    }
}
