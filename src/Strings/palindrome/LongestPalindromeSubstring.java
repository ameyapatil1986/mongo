package Strings.palindrome;

/******************************************************************************
 *
 * https://www.youtube.com/watch?v=SV1ZaKCozS4&t=1299s
 *
 *  Compilation:  javac Manacher.java
 *  Execution:    java Manacher text
 *  Dependencies: StdOut.java
 *
 *  Computes the longest palindromic substring in linear time
 *  using Manacher's algorithm.
 *
 *  Credits: The code is lifted from the following excellent reference
 *  http://www.leetcode.com/2011/11/longest-palindromic-substring-part-ii.html
 *
 ******************************************************************************/


/**
 * Complexity: O(n)
 */
public class LongestPalindromeSubstring {

    static char[] getFormattedArray(String s) {
        char[] t = new char[s.length() * 2 + 1];

        for (int i = 0; i < t.length - 1; i = i + 2) {
            t[ 2 * i ] = '#';
            t[ 2 * i + 1 ] = s.charAt(i);
        }

        t[s.length() - 1] = '#';
        return t;
    }

    // Transform s into t.
    // For example, if s = "abba", then t = "$#a#b#b#a#@"
    // the # are interleaved to avoid even/odd-length palindromes uniformly
    // $ and @ are prepended and appended to each end to avoid bounds checking
    private static String preprocess(String s) {
        char[] t = getFormattedArray(s);
        int[] palindromeSize = new int[ t.length ];

        int center = 0;
        int right = 0;

        for (int i = 1; i < t.length - 1; i++) {
            int mirror = 2 * center - i;

            if (i < right) {
                palindromeSize[i] = Math.min(right - i, palindromeSize[mirror]);
            }

            // attempt to expand palindrome centered at i
            while (t[i + (1 + palindromeSize[i])] == t[i - (1 + palindromeSize[i])]) {
                palindromeSize[i]++;
            }

            // if palindrome centered at i expands past right,
            // adjust center based on expanded palindrome.
            if (i + palindromeSize[i] > right) {
                center = i;
                right = i + palindromeSize[i];
            }
        }

        return longestPalindromicSubstring(s, palindromeSize);
    }

    // longest palindromic substring
    public static String longestPalindromicSubstring(String s, int[] palindromeSize) {
        int length = 0;   // length of longest palindromic substring
        int center = 0;   // center of longest palindromic substring
        for (int i = 1; i < palindromeSize.length-1; i++) {
            if (palindromeSize[i] > length) {
                length = palindromeSize[i];
                center = i;
            }
        }
        return s.substring((center - (length - 1)/2), (center + (length - 1)/2) + 1);
    }


    // test client
    public static void main(String[] args) {
        String s = "xbababy";
        System.out.println(LongestPalindromeSubstring.preprocess(s));
    }
}
