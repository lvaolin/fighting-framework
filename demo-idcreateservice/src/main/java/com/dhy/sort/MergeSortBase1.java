package com.dhy.sort;

/**
 * 归并排序基础：合并两个升序数组
 */
public class MergeSortBase1 {

    public static void main(String[] args) {

        int[] a = {10, 30, 50, 70, 90};
        int[] b = {2, 4, 6, 8, 10};
        MergeSortBase1 mergeSortBase1 = new MergeSortBase1();
        int[] ints = mergeSortBase1.mergeArray(a, b);
        for (int anInt : ints) {
            System.out.print(anInt+",");
        }

    }


    public int[] mergeArray(int[] arr1, int[] arr2) {
        if (arr1 == null || arr1.length == 0) {
            return arr2;
        }
        if (arr2 == null || arr2.length == 0) {
            return arr1;
        }
        int[] result = new int[arr1.length + arr2.length];
        int resultIndex = 0;
        int arr1Index = 0;
        int arr2Index = 0;
        while (true) {
            if (arr1Index > arr1.length - 1) {
                //arr1 结束了
                break;
            }
            if (arr2Index > arr2.length - 1) {
                //arr2 结束了
                break;
            }
            if (arr1[arr1Index] <= arr2[arr2Index]) {
                //arr1元素过去
                result[resultIndex++] = arr1[arr1Index++];
            } else {
                //arr2元素过去
                result[resultIndex++] = arr2[arr2Index++];
            }
        }

        if (arr1Index > arr1.length - 1) {
            //arr1 结束了,合并剩下的arr2
            while (arr2Index <= arr2.length - 1) {
                result[resultIndex++] = arr2[arr2Index++];
            }
        }
        if (arr2Index > arr2.length - 1) {
            //arr2 结束了,合并剩下的arr1
            while (arr1Index <= arr1.length - 1) {
                result[resultIndex++] = arr1[arr1Index++];
            }
        }
        return result;
    }
}
