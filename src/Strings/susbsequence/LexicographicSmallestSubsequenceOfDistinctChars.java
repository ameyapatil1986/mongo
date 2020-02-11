package Strings.susbsequence;

import java.util.Stack;
import java.util.Arrays;
import java.util.*;

/**
 * https://leetcode.com/problems/smallest-subsequence-of-distinct-characters/
 *
 * https://www.youtube.com/watch?v=uXwmONIABlA
 * https://github.com/cheeenn/java_code_example/blob/master/1081.%20Smallest%20Subsequence%20of%20Distinct%20Characters
 *
 * Example 1:
 * Input: "cdadabcc"
 * Output: "adbc"
 *
 * Example 2:
 * Input: "abcd"
 * Output: "abcd"
 *
 * Example 3:
 * Input: "ecbacba"
 * Output: "eacb"
 *
 * Example 4:
 * Input: "leetcode"
 * Output: "letcod"
 *
 * SAMPLE EXAMPLE:
 * lela.
 * output: ela.
 *
 * explanation:
 * if char does not repeat use it (eg: char 'e' and 'a' )
 * if char does repeat then, it should be used lexicographically ( eg: second l was used, not first )
 *
 *
 *
 */
public class LexicographicSmallestSubsequenceOfDistinctChars {

    public String smallestSubsequence1(String text) {
        Map<Character, Integer> charCount = new HashMap<>();
        for(int i = 0; i < text.length(); i++) {
            charCount.put(text.charAt(i), charCount.getOrDefault(text.charAt(i), 0) + 1);
        }

        Set<Character> set = new HashSet<>();
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);

            if (set.contains(c)) {
                charCount.put(c, charCount.get(c) - 1);
                continue;
            }

            int last = sb.length() - 1;

            while(sb.length() > 0 && c < sb.charAt(last) && charCount.get(sb.charAt(last)) > 0) {
                set.remove(sb.charAt(last));
                sb.deleteCharAt(last);
                last -= 1;
            }

            sb.append(c);
            set.add(c);
            charCount.put(c, charCount.get(c) - 1);
        }
        return sb.toString();
    }
}
