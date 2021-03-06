package DynamicProgramming2;

import java.util.*;


/**
 * Given a positive integer n, find the least number of perfect square numbers (for example, 1, 4, 9, 16, ...) which sum to n.
 * For example, given n = 12, return 3 because 12 = 4 + 4 + 4; given n = 13, return 2 because 13 = 4 + 9.
 *
 * https://www.programcreek.com/2014/05/leetcode-perfect-squares-java/
 */
public class PerfectSquares {

    public int numSquares(int n) {
        int[] dp = new int[n+1]; // i think this n + 1 has been added only for sake of simplicity.
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= Math.sqrt(i); j++) {
                dp[i] = Math.min(dp[i], dp[i - j*j] + 1);
            }
        }

        return dp[n];
    }
}
