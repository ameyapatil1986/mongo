package Strings.substringsum;

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
public class CountAllSubstringsSumEqualsK {

    public int countSubarraySum(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);

        int count = 0;
        int sum = 0;

        /**
         * https://zxi.mytechroad.com/blog/hashtable/leetcode-560-subarray-sum-equals-k/
         */
        //eg.1 : 1 1 2 1 1,   k=2
        //eg.2 : 1 1 1        k=2
        //eg.3 : 2 -2 2 2     k=2
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            count += map.getOrDefault(sum - k, 0);
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }

        return count;
    }
}





































