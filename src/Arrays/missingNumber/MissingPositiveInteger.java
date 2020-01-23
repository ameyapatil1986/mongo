package Arrays.missingNumber;

import java.util.*;


/**
 * https://leetcode.com/problems/first-missing-positive/
 * https://github.com/Nideesh1/Algo/blob/master/leetcode/L_41.java
 * https://www.youtube.com/watch?v=2QugZILS_Q8
 * https://www.youtube.com/watch?v=K8qVpQfNoy0
 */
public class MissingPositiveInteger {

    public int firstMissingPositive(int[] nums) {
        int n = nums.length;
        boolean oneExist = false;

        if (Arrays.stream(nums).noneMatch(e -> e == 1)) {
            return 1;
        }

        // making sure we will never see a number in the
        // array apart from 1...n
        for(int i = 0; i < nums.length; i++) {
            if(nums[i] <= 0 || nums[i] > n) {
                nums[i] = 1;
            }
        }

        for(int i = 0; i < nums.length; i++) {
            int v = Math.abs(nums[i]);

            //simply invalidating an index v and it's content
            //because we found a value v
            // [1, 2, 3, 4 ]
            if (v == nums.length) {
                nums[0] = -1 * Math.abs(nums[0]);
            } else {
                nums[v] = -1 * Math.abs(nums[v]);
            }
        }


        for(int i = 1; i < n; i++) {
            if(nums[i] > 0) {
                return i;
            }
        }

        if(nums[0] > 0) {
            return n;
        }

        // [ 1, 2, 0 ]
        return n + 1;
    }

}
