package DynamicProgramming.Longest;

import java.util.Arrays;


/**
 * https://www.geeksforgeeks.org/longest-decreasing-subsequence/
 *
 * Input: arr[] = [15, 27, 14, 38, 63, 55, 46, 65, 85]
 * Output: 3
 * Explanation: The longest decreasing sub sequence is {63, 55, 46}
 *
 *
 * Input: arr[] = {50, 3, 10, 7, 40, 80}
 * Output: 3
 * Explanation: The longest decreasing subsequence is {50, 10, 7}
 *
 */
public class LongestDecreasingSequence {

    static int lds(int arr[], int n) {
        int lds[] = new int[n];
        int i, j, max = 0;

        // Initialize LDS with 1
        // for all index. The minimum
        // LDS starting with any
        // element is always 1
        for (i = 0; i < n; i++)
            lds[i] = 1;

        // Compute LDS from every
        // index in bottom up manner
        for (i = 1; i < n; i++) {
            for (j = 0; j < i; j++) {
                if (arr[i] < arr[j]) {
                    lds[i] = Math.max(lds[i], lds[j] + 1);
                }
            }
        }

        // returns the length
        // of the LDS
        return Arrays.stream(lds).max().orElse(0);
    }

    /**
     * https://leetcode.com/problems/longest-increasing-subsequence/
     */
    static int lis(int arr[],int n) {
        int lis[] = new int[n];
        int i,j,max = 0;

        /* Initialize LIS values for all indexes */
        for ( i = 0; i < n; i++)
            lis[i] = 1;

        /* Compute optimized LIS values in bottom up manner */
        for ( i = 1; i < n; i++ ) {
            for (j = 0; j < i; j++) {
                if (arr[j] < arr[i]) {
                    lis[i] = Math.max(lis[i], lis[j] + 1);
                }
            }
        }

        return Arrays.stream(lis).max().orElse(0);
    }

}


