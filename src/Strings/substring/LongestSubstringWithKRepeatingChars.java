package Strings.substring;

import java.util.*;


/**
 * https://edlinlink.github.io/Leetcode_Longest_Substring_With_At_Least_K_Repeating_Characters.html
 */
public class LongestSubstringWithKRepeatingChars {

    public int longestSubstring(String s, int k) {
        int ans = 0;
        Map<Character, Integer> map = new HashMap<>();
        for( int i=0; i< s.length(); i++ ) {
            map.putIfAbsent(s.charAt(i), map.getOrDefault(s.charAt(i) + 1, 0) + 1);
        }

        int len = -1;
        for( int i=0; i< s.length(); i++ ){
            int index = 0;
            if(map.get(s.charAt(i)) < k ){
                len = Math.max( len, longestSubstring(s.substring(index, i - index), k) );
                index = i + 1;
            }
        }
        return (-1 == len ? s.length() : len);
    }
}

