package Strings.zeroAndOne;

import java.util.*;

/**
 * https://yeqiuquan.blogspot.com/2017/05/525-contiguous-array.html
 *
 * Given a binary array, find the maximum length of a contiguous subarray with equal number of 0 and 1.
 * Example 1:
 * Input: [0,1]
 * Output: 2
 *
 * Explanation: [0, 1] is the longest contiguous subarray with equal number of 0 and 1.
 * Example 2:
 * Input: [0,1,0]
 * Output: 2
 * Explanation: [0, 1] (or [1, 0]) is a longest contiguous subarray with equal number of 0 and 1.
 *
 *
 * Sample example: [0 1 0]
 */
public class LongestEqualContigousZeroAndOne {

    public int findMaxLength(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                nums[i] = -1;
            }
        }

        Map<Integer, Integer> sumIndex = new HashMap<>();
        sumIndex.put(0, -1);
        int sum = 0;
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];

            if (sumIndex.containsKey(sum)) {
                max = Math.max(max, i - sumIndex.get(sum));
            } else {
                sumIndex.put(sum, i);
            }
        }
        return max;
    }
}
