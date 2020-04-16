package Arrays;

import java.util.*;


/**
 * https://leetcode.com/problems/missing-ranges/
 */
public class MissingRanges {

    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        List<String> res = new ArrayList<String>();

        // 1. We don't need to add [Integer.MAX_VALUE, ...] to result
        if(lower == Integer.MAX_VALUE) {
            return res;
        }


        int indexOfLower = 0;// findIndexOfLower. if lower is missing then the one which is higher.
        int indexOfHigher = 0;// findIndexOfHigher. if higher is missing then the one which is lesser..

        if (lower < nums[indexOfLower]) {
            res.add(getRange(lower, nums[indexOfLower] - 1));
        }

        for (int i = indexOfLower; i < indexOfHigher; i++) {
            res.add(getRange(nums[i] + 1, nums[i + 1] - 1));
        }

        if (nums[indexOfHigher] < upper) {
            res.add(getRange(nums[indexOfHigher], upper));
        }

        return res;
    }

    private String getRange(int n1, int n2) {
        return n1 == n2 ? String.valueOf(n1) : String.valueOf(n1) + " -> " + String.valueOf(n2);
    }
}
