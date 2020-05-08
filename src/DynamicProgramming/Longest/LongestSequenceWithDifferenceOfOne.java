package DynamicProgramming.Longest;

import java.util.Arrays;
import java.util.*;


/**
 * https://www.geeksforgeeks.org/longest-subsequence-such-that-difference-between-adjacents-is-one/
 *
 * Input :  arr[] = {10, 9, 4, 5, 4, 8, 6}
 * Output :  3
 * As longest subsequences with difference 1 are, "10, 9, 8",
 * "4, 5, 4" and "4, 5, 6"
 *
 * Input :  arr[] = {1, 2, 3, 2, 3, 7, 2, 1}
 * Output :  7
 * As longest consecutive sequence is "1, 2, 3, 2, 3, 2, 1"
 */
public class LongestSequenceWithDifferenceOfOne {

    public static int longestSubseqWithDiffOne(int arr[], int n) {
        // Initialize the dp[] array with 1 as a
        // single element will be of 1 length
        int dp[] = new int[n];
        for (int i = 0; i< n; i++)
            dp[i] = 1;

        // Start traversing the given array
        for (int i = 1; i < n; i++) {
            // Compare with all the previous
            // elements
            for (int j = 0; j < i; j++) {
                // If the element is consecutive
                // then consider this subsequence
                // and update dp[i] if required.
                if (Math.abs(arr[j] - arr[i]) == 1)
                    dp[i] = Math.max(dp[i], dp[j]+1);
            }
        }

        return Arrays.stream(dp).max().orElse(0);
    }


    // Driver code
    public static void main(String[] args) {
        // Longest subsequence with one
        // difference is
        // {1, 2, 3, 4, 3, 2}
        int arr[] = {1, 2, 3, 4, 5, 3, 2};
        int n = arr.length;
        System.out.println(longestSubseqWithDiffOne(arr, n));
    }



}
