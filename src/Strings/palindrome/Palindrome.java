package Strings.palindrome;


import java.util.stream.*;


/**
 * Complexity O(n)
 */
public class Palindrome {

    public boolean isPalindromeUsingIntStream(String s) {
        s  = s.replaceAll("\\s+", "").toLowerCase();

        int left = 0;
        int right = s.length() - 1;

        for  ( ;left < right && s.charAt(left) == s.charAt(right); left++, right--);

        return left >= right;  // aa or aba
    }
}
