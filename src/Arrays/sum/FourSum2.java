package Arrays.sum;

import java.util.*;


/**
 * https://leetcode.com/problems/4sum-ii/
 * https://github.com/varunu28/LeetCode-Java-Solutions/blob/master/Medium/4%20Sum%20II.java
 */
public class FourSum2 {

    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        Map<Integer, Integer> map = new  HashMap<>();
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < B.length; j++) {
                int sum = A[i] + B[j];
                map.put(sum, map.getOrDefault(sum, 0) + 1);
            }
        }

        int ans = 0;

        /**
         * Pretty easy with this example:
         * A (2,   2)
         * B (2,   2)
         * C (-2, -2)
         * D (-2, -2)
         */
        for (int i = 0; i < C.length; i++) {
            for (int j = 0; j < D.length; j++) {
                int sum = C[i] + D[j];
                ans += map.getOrDefault(-sum, 0);
            }
        }

        return ans;
    }
}
