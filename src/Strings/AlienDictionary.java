package Strings;

import java.util.*;

/**
 * https://leetcode.com/problems/verifying-an-alien-dictionary/
 * https://gist.github.com/junhaowww/266896226c933460525cfdfc92c4bea8
 */
public class AlienDictionary {

        public boolean isAlienSorted(String[] words, String order) {
            if (words == null || words.length == 0 || order == null || order.length() == 0) {
                return false;
            }
            Map<Character, Integer> orderRule = new HashMap<>();
            for (int i = 0; i < order.length(); ++i) {
                char ch = order.charAt(i);
                orderRule.put(ch, i);
            }

            for (int i = 0; i < words.length - 1; ++i) {
                String s1 = words[i];
                String s2 = words[i + 1];

                for (int j = 0; j < Math.min(s1.length(), s2.length()); j++) {
                    if (orderRule.get(s1.charAt(j)) > orderRule.get(s2.charAt(j))) {
                        return false;
                    }
                }

                if (s1.length() > s2.length()) return false; // "abcdef" vs. "abc"
            }
            return true;
        }
}
