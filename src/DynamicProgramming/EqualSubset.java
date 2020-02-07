package DynamicProgramming;

/**
 * https://leetcode.com/problems/partition-equal-subset-sum/
 * https://www.youtube.com/watch?v=s6FhG--P7z0
 */
public class EqualSubset {

    /**
     * https://www.youtube.com/watch?v=s6FhG--P7z0
     */
    /**
     * Given a non-empty array containing only positive integers,
     * find if the array can be partitioned into two subsets such that the
     * sum of elements in both subsets is equal.
     *
     * Input: [1, 5, 11, 5]
     *
     * Output: true
     * Explanation: The array can be partitioned as [1, 5, 5] and [11].
     *
     * https://www.youtube.com/watch?v=s6FhG--P7z0
     */
    public boolean subsetSum(int[] input, int total) {

        boolean[][] T = new boolean[input.length + 1][total + 1];

        for (int i = 0; i <= input.length; i++) {
            T[i][0] = true;
        }

        for (int i = 1; i <= input.length; i++) {
            for (int j = 1; j <= total; j++) {
                if (j - input[i - 1] >= 0) {
                    T[i][j] = T[i - 1][j] || T[i - 1][j - input[i - 1]];
                } else {
                    T[i][j] = T[i - 1][j];
                }
            }
        }

        return T[input.length][total];
    }


    public boolean partition(int arr[]) {
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }

        if (sum % 2 != 0) {
            return false;
        }
        sum = sum / 2;
        boolean[][] T = new boolean[arr.length + 1][sum + 1];

        for (int i = 0; i <= arr.length; i++) {
            T[i][0] = true;
        }

        for (int i = 1; i <= arr.length; i++) {
            for (int j = 1; j <= sum; j++) {
                if (j - arr[i - 1] >= 0) {
                    T[i][j] = T[i - 1][j - arr[i - 1]] ||  T[i - 1][j];
                } else {
                    T[i][j] = T[i - 1][j];
                }
            }
        }
        return T[arr.length][sum];
    }
}
