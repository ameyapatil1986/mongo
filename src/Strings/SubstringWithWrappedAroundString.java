package Strings;

import java.util.*;

/**
 * http://buttercola.blogspot.com/2018/11/leetcode-467-unique-substrings-in.html
 *
 * Note: substrings need to be unique.
 * thus
 *
 * abcab, here the last ab will not count.
 *
 * Input: "zab"
 * Output: 6
 * Explanation: There are six substrings "z", "a", "b", "za", "ab", "zab" of string "zab" in the string s
 *
 * O(n)
 */
public class SubstringWithWrappedAroundString {

    public int findSubstringInWraproundString(String p) {
        if (p == null || p.length() == 0) {
            return 0;
        }

        Map<Character, Integer> charCount = new HashMap<>();
        int maxLen = 1;
        charCount.put(p.charAt(0), 1);

        for (int i = 1; i < p.length(); i++) {
            char curr = p.charAt(i);
            char prev = p.charAt(i - 1);

            if (Character.getNumericValue(curr) == Character.getNumericValue(prev) + 1 || (prev == 'z' && curr == 'a')) {
                maxLen++;
            } else {
                maxLen = 1;
            }

            charCount.put(p.charAt(i), Math.max(charCount.getOrDefault(p.charAt(i), 0), maxLen));
        }

        return charCount.values().stream().mapToInt(Integer::intValue).sum();
    }
}
