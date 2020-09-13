package com.dhy.sort;

/**
 *
 * 荷兰国旗问题
 *
 * 给定一个整数数组，给定一个值K，这个值在原数组中一定存在，
 * 要求把数组中小于K的元素放到数组的左边，大于K的元素放到数组的右边，
 * 等于K的元素放到数组的中间，最终返回一个整数数组，其中只有两个值，
 * 分别是等于K的数组部分的左右两个下标值。
 *
 * 例如，给定数组：[2, 3, 1, 9, 7, 6, 1, 4, 5]，给定一个值4，
 * 那么经过处理原数组可能得一种情况是：[2, 3, 1, 1, 4, 9, 7, 6, 5]，
 * 需要注意的是，小于4的部分不需要有序，大于4的部分也不需要有序，
 * 返回等于4部分的左右两个下标，即[4, 4]
 *
 */
public class Netherlands {


    public static void main(String[] args) {
        int[] arr = {1,6,3,5,6,7,6,9,2,4,6,8};
        Netherlands netherlands = new Netherlands();
        netherlands.netherlandsFlag(arr,0,arr.length-1,6);
    }

    /**
     *
     * @param arr
     * @param L
     * @param R
     * @param target
     */

    public int[] netherlandsFlag(int[] arr,int L,int R,int target){
        int less = L - 1;
        int more = R + 1;
        while(L < more) {
            if(arr[L] < target) {
                swap(arr, ++less, L++);
            } else if (arr[L] > target) {
                swap(arr, --more, L);
            } else {
                L++;
            }
        }
        print(arr);
        return new int[] { less + 1, more - 1 };
    }
    public  void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }


    void print(int[] arr){
        for (int i : arr) {
            System.out.print(i);
        }
    }
}
