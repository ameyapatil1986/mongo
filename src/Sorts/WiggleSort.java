package Sorts;

import java.util.*;


/**
 * http://buttercola.blogspot.com/2016/01/leetcode-wiggle-sort-ii.html
 *
 * O(nlogn)
 */
public class WiggleSort {

    public void wiggleSort(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return;
        }

        Arrays.sort(nums);
        int n = nums.length;

        int[] temp = new int[n];
        int left = (n - 1) / 2;
        int right = n - 1;

        for (int i = 0; i < n; i++) {
            if (i % 2 == 0) {
                temp[i] = nums[left];
                left--;
            } else {
                temp[i] = nums[right];
                right--;
            }
        }

        System.arraycopy(temp, 0, nums, 0, n);
    }
}
