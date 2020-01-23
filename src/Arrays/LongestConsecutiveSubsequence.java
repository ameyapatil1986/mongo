package Arrays;

import java.util.*;


/**
 * Input: arr[] = {1, 9, 3, 10, 4, 20, 2};
 * Output: 4
 * The subsequence 1, 3, 4, 2 is the longest subsequence
 * of consecutive elements
 *
 * Input: arr[] = {36, 41, 56, 35, 44, 33, 34, 92, 43, 32, 42}
 * Output: 5
 * The subsequence 36, 35, 33, 34, 32 is the longest subsequence
 * of consecutive elements.
 */
public class LongestConsecutiveSubsequence {

    public int longestConsecutive(int[] nums) {
        Set<Integer> set = new HashSet(Arrays.asList(nums));

        int result = 0;
        for (int num : nums) {
            int count = 1;

            int down = num - 1;
            while (set.contains(down)) {
                set.remove(down);
                down--;
                count++;
            }

            int up = num + 1;
            while (set.contains(up)) {
                set.remove(up);
                up++;
                count++;
            }

            result = Math.max(result, count);
        }

        return result;
    }
}
