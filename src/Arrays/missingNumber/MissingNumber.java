package Arrays.missingNumber;

import java.util.*;

/**
 * https://leetcode.com/problems/missing-number/
 * https://www.youtube.com/watch?v=YMYVYSWL93w
 *
 */
public class MissingNumber {

    public int missingNUmber(int[] nums) {
        int sum = Arrays.stream(nums).sum();
        int n = nums.length + 1;
        return (n * (n - 1) / 2) - sum;
    }
}
