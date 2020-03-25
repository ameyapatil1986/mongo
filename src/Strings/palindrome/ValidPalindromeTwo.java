package Strings.palindrome;

/**
 * https://leetcode.com/problems/valid-palindrome-ii/
 */
public class ValidPalindromeTwo {

    public boolean validPalindrome(String s) {
        if (s == null || s.length() < 2) {
            return true;
        }

        int left = 0;
        int right = s.length() - 1;

        for  ( ;left < right && s.charAt(left) == s.charAt(right); left++, right--);
        // aa or aba
        if (left >= right) {
            return true;
        }

        return isPalin(s, left + 1, right) || isPalin(s, left, right - 1);
    }

    private boolean isPalin(String s, int left, int right) {
        for  ( ;left < right && s.charAt(left) == s.charAt(right); left++, right--);
        return left >= right;
    }

}
