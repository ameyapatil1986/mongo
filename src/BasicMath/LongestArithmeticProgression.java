package BasicMath;

import java.util.*;


/**
 * https://leetcode.com/problems/longest-arithmetic-sequence/
 * https://xingxingpark.com/Leetcode-1027-Longest-Arithmetic-Sequence/
 */
public class LongestArithmeticProgression {

    // [3, 6, 9, 12]
    public int longestArithSeqLength(int[] A) {
        final Map<Integer, Map<Integer, Integer>> map = new HashMap<>();
        int res = 2;

        for (int i = 0; i < A.length; i++) {
            for (int j = i + 1; j < A.length; j++) {
                int key = A[j] - A[i];
                Map<Integer, Integer> m = map.putIfAbsent(key, new HashMap<>());
                m.put(j, m.getOrDefault(i, 1) + 1);
                res = Math.max(res, m.get(j));
            }
        }
        return res;
    }
}
