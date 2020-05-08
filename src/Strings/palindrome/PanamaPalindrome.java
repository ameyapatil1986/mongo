package Strings.palindrome;

/**
 * https://leetcode.com/problems/valid-palindrome/
 *
 * Complexity:
 * O(n)
 */
public class PanamaPalindrome {

    private static boolean isCharacter(char ch) {
        return 'a' <= ch && ch <= 'z';

    }

    public static boolean isPalamaPalindrome(String str) {
        int i = 0;
        int j = str.length() - 1;
        char[] ch = str.toCharArray();

        while (i < j) {
            if (!isCharacter(ch[i])) {
                i++;
            } else if (!isCharacter(ch[j])) {
                j--;
            } else if (ch[i] != ch[j]) {
                return false;
            } else {
                i++;
                j--;
            }
        }

        return true;
    }

}
