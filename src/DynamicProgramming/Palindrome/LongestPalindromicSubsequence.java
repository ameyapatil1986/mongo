package DynamicProgramming.Palindrome;

/**
 * https://leetcode.com/problems/longest-palindromic-subsequence/
 * https://www.youtube.com/watch?v=_nCsPn7_OgI&t=225s
 * https://www.youtube.com/watch?v=U4yPae3GEO0
 * https://leetcode.com/problems/longest-palindromic-subsequence/
 */
public class LongestPalindromicSubsequence {

    public static int LPS(String s) {
        int n = s.length();
        int palindrome[][] = new int[n][n];

        for (int i = 0; i < n; i++) {
            palindrome[i][i] = 1;
        }

        for (int currLen = 2; currLen <= n; currLen++) {
            for (int i = 0; i < n - currLen + 1; i++) {
                int j = i + currLen - 1;
                if (s.charAt(i) == s.charAt(j)) {
                    palindrome[i][j] = palindrome[i+1][j-1] + 2;
                } else {
                    palindrome[i][j] = Math.max(palindrome[i+1][j], palindrome[i][j-1]);
                }
            }
        }

        return palindrome[0][n-1];
    }
}
