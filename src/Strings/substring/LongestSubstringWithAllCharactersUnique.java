package Strings.substring;

import java.util.*;

/**
 * https://leetcode.com/problems/longest-substring-without-repeating-characters/description/
 *
 * Complexity: O(n)
 */
public class LongestSubstringWithAllCharactersUnique {

    private LongestSubstringWithAllCharactersUnique() {
    }

    public static int lengthOfLongestSubstring(String s) {
        int n = s.length(), ans = 0;
        // stores index of that character.
        Map<Character, Integer> charIndexMap = new HashMap<>();
        // try to extend the range [i, j]

        int j = 0;
        for (int i = 0; i < n; i++) {
            if (charIndexMap.containsKey(s.charAt(i))) {
                // eg: axyzbcba   ( at second 'a',  map.get(s.charAt(i)) + 1 < j )
                j = Math.max(charIndexMap.get(s.charAt(i)) + 1, j);
            }
            ans = Math.max(ans, i - j + 1);
            charIndexMap.put(s.charAt(i), i);
        }

        return ans;
    }

    // aaa
    // abcabc
    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("abcdcba"));
        System.out.println(lengthOfLongestSubstring("abcabc"));
    }

}

