package com.dhy.sort;

/**
 * 快速排序
 * 1、算法思想
 * 快速排序的基本思想：通过一趟排序将待排记录分隔成独立的两部分，
 * 其中一部分记录的关键字均比另一部分的关键字小，则可分别对这两部分记录继续进行排序，
 * 以达到整个序列有序。
 *
 * 2、实现原理
 *
 * 2.1、设置两个变量 low、high，排序开始时：low=0，high=size-1。
 * 2.2、整个数组找基准正确位置，所有元素比基准值小的摆放在基准前面，所有元素比基准值大的摆在基准的后面
 *
 * 默认数组的第一个数为基准数据，赋值给key，即key=array[low]。
 * 因为默认数组的第一个数为基准，所以从后面开始向前搜索（high–），找到第一个小于key的array[high]，
 * 就将 array[high] 赋给 array[low]，即 array[low] = array[high]。（循环条件是 array[high] >= key；
 * 结束时 array[high] < key）
 * 此时从前面开始向后搜索（low++），找到第一个大于key的array[low]，就将 array[low] 赋给 array[high]，
 * 即 array[high] = array[low]。（循环条件是 array[low] <= key；结束时 array[low] > key）
 * 循环 2-3 步骤，直到 low=high，该位置就是基准位置。
 * 把基准数据赋给当前位置。
 * 2.3、第一趟找到的基准位置，作为下一趟的分界点。
 * 2.4、递归调用（recursive）分界点前和分界点后的子数组排序，重复2.2、2.3、2.4的步骤。
 * 2.5、最终就会得到排序好的数组。
 */

public class QuickSort {

    /**
     *
     * @param arr
     * @return
     */
    public void quickSort(int[] arr){
         if(arr==null||arr.length<=1) {
             return;
         }

         process(arr,0,arr.length-1);

    }


    public void process(int[] arr,int L,int R){

         int base = arr[L];
         int leftIndex = L;
         int rightIndex = R;

         while (leftIndex!=rightIndex){
             if(arr[rightIndex]<base){
                 arr[leftIndex++]=arr[rightIndex];

             }else{
                 rightIndex--;
             }

         }


    }

}
