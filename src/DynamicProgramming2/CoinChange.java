package DynamicProgramming2;

import java.util.Arrays;

/**
 * https://www.youtube.com/watch?v=Y0ZqKpToTic
 * https://leetcode.com/problems/coin-change/
 *
 */
public class CoinChange {

    public int coinChange(int[] coins, int amount) {
        if (amount == 0) {
            return 0;
        }

        int[] dp = new int[amount + 1];

        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        for(int i = coins[0]; i <= amount; i++) {
          // If array of coins is sorted then -> for (int j = 0; j < coins.length && i >= coins[j]; j++)
            for (int coin: coins) {
                // this if-check if needed to prevent arrays out of bounds exception.
                if (i >= coin) {
                    dp[i] = Math.min(dp[i - coin] + 1, dp[i]);
                }
            }
        }

        if(dp[amount] == Integer.MAX_VALUE){
            return -1;
        }

        return dp[amount];
    }
}
