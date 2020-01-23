package DynamicProgramming.Longest;

import java.util.Collections;
import java.util.Arrays;


/**
 * https://www.geeksforgeeks.org/longest-dividing-subsequence/
 */
public class LongestDividingSubsequence {

    static int lds( Integer arr[], int n ) {
        int lds[]=new int[n];

        lds[0] = 1;

        /* Compute optimized lds values in bottom up manner */
        for (int i = 1; i < n; i++ ) {
            lds[i] = 1;
            for (int j = 0; j < i; j++ ) {
                if (lds[j] != 0 && arr[i] % arr[j] == 0) {
                    lds[i] = Math.max(lds[i], lds[j] + 1);
                }
            }
        }


        return Arrays.stream(lds).max().orElse(0);
    }
}
