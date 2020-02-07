package Decimals;

/**
 * https://leetcode.com/problems/palindrome-number/
 */
public class PalindromeNumber {

    public static boolean isPalindrome(int x) {

        if (x < 0) {
            return false;
        }

        int y = 0;
        for ( ; x  > 0; x = x / 10) {
            y = y * 10 +  x %10;
        }

        return x == y;
    }
}
