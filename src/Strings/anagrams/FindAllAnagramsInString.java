package Strings.anagrams;

import java.util.*;


/**
 * https://leetcode.com/problems/find-all-anagrams-in-a-string/
 */
public class FindAllAnagramsInString {

    public static List<Integer> findAllAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<>();

        if (s.length() == 0 || s == null) {
            return result;
        }

        int[] charFrequency = new int[26];
        for (char c : p.toCharArray()) {
            charFrequency[c - 'a'] ++;
        }

        int left = 0;
        int right = 0;
        int count = p.length();

        while (right < s.length()) {

            if (charFrequency[s.charAt(right) - 'a'] >= 1) {
                count--;
            }
            charFrequency[s.charAt(right) - 'a']--;
            right++;

            if (count == 0) {
                result.add(left);
            }

            if (right - left == p.length()) {
                if (charFrequency[s.charAt(left) - 'a'] >= 0) {
                    count++;
                }
                charFrequency[s.charAt(left) - 'a']++;
                left++;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(findAllAnagrams("aabc", "abc"));
    }

}
