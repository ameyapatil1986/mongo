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

        Map<Character, Integer> frequencyMap = new HashMap<>();
        for (char c : p.toCharArray()) {
            frequencyMap.put(c, frequencyMap.getOrDefault(c, 0) + 1);
        }

        Map<Character, Integer> copyMap = new HashMap<>();
        copyMap.putAll(frequencyMap);


        // The number of distinct characters
        int counter = copyMap.size();
        for (int left = 0, right = 0; right < s.length(); right++) {

            if (copyMap.containsKey(s.charAt(right))) {
                copyMap.put(s.charAt(right), copyMap.get(s.charAt(right)) - 1);
                if (copyMap.get(s.charAt(right)) == 0) {
                    counter -= 1;
                }
            }

            while (counter == 0) {

                if (right - left + 1 == p.length()) {
                    ans.add(left);
                }

                // put it back: eg:  xyaaaa and 'string p' is aaa
                // another example: eg: aaabc and 'string p' is abc
                if (copyMap.containsKey(s.charAt(left))) {
                    copyMap.put(s.charAt(left), copyMap.get(s.charAt(left)) + 1);
                    if (copyMap.get(s.charAt(left)) == frequencyMap.get(s.charAt(left))) {
                        counter += 1;
                    }
                }

                left++;
            }
        }

        return ans;
    }

}
