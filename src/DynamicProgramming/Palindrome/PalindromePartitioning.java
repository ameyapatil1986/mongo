package DynamicProgramming.Palindrome;

import java.util.*;


/**
 * https://leetcode.com/problems/palindrome-partitioning/
 * https://www.youtube.com/channel/UCMNkvKnD3mo3Jj9eTwJllWw
 * https://www.youtube.com/watch?v=WPr1jDh3bUQ&t=76s
 */
public class PalindromePartitioning {

    public List<List<String>> partition(String s) {
        int n = s.length();
        boolean palindrome[][] = new boolean[n][n]; //boolean table

        //Trivial case: single letter palindromes
        for (int i = 0; i < n; i++) {
            palindrome[i][i] = true;
        }

        //Finding palindromes of two characters.
        for (int i = 0; i < n-1; i++) {
            if (s.charAt(i) == s.charAt(i+1)) {
                palindrome[i][i+1] = true;
            }
        }

        //Finding palindromes of length 3 to n
        for (int len = 3; len <= n; len++) {
            for (int i = 0; i < n - len + 1; i++) {
                int j = i + len -1;

                //1. The first and last characters should match
                //2. Rest of the substring should be a palindrome
                if (s.charAt(i) == s.charAt(j) && palindrome[i+1][j-1]) {
                    palindrome[i][j] = true;
                }
            }
        }


        // word break -2


//        int[] cuts = new int[n];
//
//        for(int i = 0; i < n; i++) {
//            int temp = Integer.MAX_VALUE;
//            if(palindrome[0][i]) {
//                cuts[i] = 0;
//            } else {
//                for(int j = 0; j < i; j++) {
//                    if((palindrome[j+1][i])) {
//                        temp = Math.min(temp, cuts[j] + 1);
//                    }
//                }
//                cuts[i] = temp;
//            }
//        }

        return null;
    }

    public static void main(String args[]) {
    }
}
