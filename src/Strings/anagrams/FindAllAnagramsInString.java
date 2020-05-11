package Strings.anagrams;

import java.util.*;


/**
 * https://leetcode.com/problems/find-all-anagrams-in-a-string/
 * https://github.com/cherryljr/LeetCode/blob/master/Find%20All%20Anagrams%20in%20a%20String.java
 *
 * Complexity:
 * O(n)
 */
public class FindAllAnagramsInString {

    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> ans = new ArrayList<>();
        if (s == null || s.length() == 0 || s.length() < p.length()) {
            return ans;
        }

        Map<Character, Integer> map = new HashMap<>();
        for (char c : p.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        // The number of distinct characters
        int counter = map.size();
        for (int left = 0, right = 0; right < s.length(); right++) {

            if (map.containsKey(s.charAt(right))) {
                map.put(s.charAt(right), map.get(s.charAt(right)) - 1);
                if (map.get(s.charAt(right)) == 0) {
                    counter -= 1;
                }
            }

            while (counter <= 0) {

                if (right - left + 1 == p.length()) {
                    ans.add(left);
                }

                // put it back: eg:  xyaaaa and 'string p' is aaa
                // another example: eg: aaabc and 'string p' is abc
                if (map.containsKey(s.charAt(left))) {
                    map.put(s.charAt(left), map.get(s.charAt(left)) + 1);
                    if (map.get(s.charAt(left)) == 0) {
                        counter += 1;
                    }
                }

                left++;
            }
        }

        return ans;
    }

}
