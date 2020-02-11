package Strings.substring;

import java.util.*;

/**
 * https://leetcode.com/problems/longest-substring-with-at-least-k-repeating-characters/
 *
 * https://edlinlink.github.io/Leetcode_Longest_Substring_With_At_Least_K_Repeating_Characters.html
 */
public class LongestSubstringWithEachCharacterRepeatingKTimes {

    public class Solution {
        /**
         * Sample example:
         *  a a p a a b b
         *  a p a b b and k = 2
         *
         * @param s: a string
         * @param k: an integer
         * @return: return an integer
         */
        public int longestSubstring(String s, int k) {
            if (s == null || s.length() == 0) return 0;

            final Map<Character, Integer> map = new HashMap<>();

            // record the frequency of each character
            for (int i = 0; i < s.length(); i++) {
                map.putIfAbsent(s.charAt(i), map.getOrDefault(s.charAt(i),0) + 1);
            }


            if (map.values().stream().allMatch(e ->  e > k)) {
                return s.length();
            }

            int max = 0;
            int start = 0;

            // otherwise we use all the infrequent elements as splits
            for (int cur = 0; cur < s.length(); cur++) {
                if (map.get(s.charAt(cur)) < k) {
                    // this is why you cannot directly count the length of left substring.
                    // a p a b b and k = 2
                    max = Math.max(max, longestSubstring(s.substring(start, cur), k));
                    // set start to curr+1 and now process the right substring
                    start = cur + 1;
                }
            }

            max = Math.max(max, longestSubstring(s.substring(start), k));
            return max;
        }
    }
}

