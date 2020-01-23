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
        int base = 0;
        for ( ; x  > 0; x = x / 10) {
            y = y * base +  x %10;
            base = base * 10;
        }

        return x == y;
    }
}
