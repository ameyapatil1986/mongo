package Strings.substring;

import java.util.*;

/**
 * https://www.youtube.com/watch?v=_Au4qE39VbU
 * https://leetcode.com/problems/binary-subarrays-with-sum/
 *
 * Input: A = [1,0,1,0,1], S = 2
 * Output: 4
 * Explanation:
 * The 4 subarrays are bolded below:
 * ["1,0,1,"0,1]
 * ["1,0,1,0,"1]
 * [1,"0,1,0,1"]
 * [1,0,"1,0,1"]
 */

/**
 * https://leetcode.com/problems/subarray-sum-equals-k/
 */
public class CountSubstringSumEqualsK {

    public int countSubarraySum(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);

        int count = 0;
        int sum = 0;

        //eg.1 : 1 1 2 1 1,   k=2
        //eg.2 : 1 1 1        k=2
        //eg.3 : 2 -2 2 2     k=2
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            //map.put(sum, map.getOrDefault(sum, 0) + 1);

            count += map.getOrDefault(sum - k, 0);

            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }

        return count;
    }

    /**
     * snow-flake.
     * https://www.geeksforgeeks.org/longest-sub-array-sum-k/
     */
    public int lenOfLongSubarr(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);

        int count = 0;
        int sum = 0;
        int max = 0;

        //eg.1 : 1 1 2 1 1,   k=2
        //eg.2 : 1 1 1        k=2
        //eg.3 : 2 -2 2 2     k=2
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            //map.put(sum, map.getOrDefault(sum, 0) + 1);

            max = Math.max(i - map.getOrDefault(sum - k, 0), max);

            map.put(sum, i);
        }

        return count;
    }

}





































