package Strings.substring;

import java.util.*;

/**
 * https://stackoverflow.com/questions/15505508/what-is-the-difference-between-string-substring-and-string-subsequence
 * substring is a continuous part or subpart of a string
 * whereas
 * subsequence is the part of a string or sequence, that might be continuous or not but the order of the elements is maintained
 *
 *
 * Fruit in basket is exactly the same as this one:
 * https://leetcode.com/problems/fruit-into-baskets/
 *
 * https://www.youtube.com/watch?v=za2YuucS0tw
 *
 * Note that you do not have any choice after the initial choice of starting tree:
 * you must perform step 1, then step 2, then back to step 1, then step 2, and so on until you stop.
 */

 /**
 * https://www.programcreek.com/2013/02/longest-substring-which-contains-2-unique-characters/
 * Find out the longest length of subarrays with at most 2 different numbers?
 * Sliding window for K-elements?
 */
public class LongestSubstringKcharacters {

    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        int result = 0;
        int j = 0;
        Map<Character, Integer> charFrequency = new HashMap<Character, Integer>();

        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            charFrequency.put(c, charFrequency.getOrDefault(c, 0) + 1);

            if (charFrequency.size() <= k) {
                result = Math.max(result, i - j +1);
            } else {
                // eg: xxxxyz and k=3
                while (charFrequency.size() > k) {
                    char l = s.charAt(j);

                    int count = charFrequency.get(l);
                    if (count == 1) {
                        charFrequency.remove(l);
                    } else {
                        charFrequency.put(l, charFrequency.get(l)-1);
                    }
                    j++;
                }
            }
        }

        return result;
    }
}
