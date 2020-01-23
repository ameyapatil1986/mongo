package Strings;

import java.util.*;

/**
 * References:
 * http://stackoverflow.com/questions/13692221/anagram-algorithm-in-java
 * http://www.geeksforgeeks.org/check-whether-two-strings-are-anagram-of-each-other/
 *
 *
 * Complexity:
 * -----------
 * O (nlogn) - time - for checkAnagramWithoutExtraSpace
 *
 * O (n)     - time - for checkAnagramWithExtraSpace
 * O (n)     - aux space
 *
 * BB:
 * ----
 * 3
 */
public final class Anagram {

    private Anagram() { }

    public static boolean checkAnagramWithExtraSpace(String str1, String str2) {
        if (str1.length() ==  0 || str2.length() == 0) {
            throw new IllegalArgumentException("Strings of length 0 are illegal");
        }

        if (str1.length() != str2.length()) {
            return false;
        }

        final Map<Character, Integer> map = new HashMap<Character, Integer>();
        for (int i = 0; i < str1.length(); i++) {
            map.put(str1.charAt(i), map.getOrDefault(str1.charAt(i), 0) + 1);
        }


        for (int i = 0; i < str2.length(); i++) {
            if (!map.containsKey(str2.charAt(i))) {
                return false;
            }

            map.put(str2.charAt(i), map.get(str2.charAt(i)) - 1);

            if (map.get(str2.charAt(i)) == 0) {
                map.remove(str2.charAt(i));
            }
        }

        return map.isEmpty(); // actually you can also return true without checking map is empty.
    }

    /**
     * https://leetcode.com/problems/permutation-in-string/
     */
    public static boolean ransomNote(String str1, String str2) {
        if (str1.length() ==  0 || str2.length() == 0) {
            throw new IllegalArgumentException("Strings of length 0 are illegal");
        }

        if (str1.length() != str2.length()) {
            return false;
        }

        final Map<Character, Integer> map = new HashMap<Character, Integer>();
        for (int i = 0; i < str1.length(); i++) {
            map.put(str1.charAt(i), map.getOrDefault(str1.charAt(i), 0) + 1);
        }


        for (int i = 0; i < str2.length(); i++) {
//            if (!map.containsKey(str2.charAt(i))) {
//                return false;
//            }

            map.put(str2.charAt(i), map.get(str2.charAt(i)) - 1);

            if (map.get(str2.charAt(i)) == 0) {
                map.remove(str2.charAt(i));

                if (map.isEmpty()) {
                    return true;
                }
            }
        }

        return false;
    }

    public static void main(String[] args) {
        System.out.println("------------------------------------------------------------");

        System.out.println("Expected: true,  " + checkAnagramWithExtraSpace("bca", "abc"));
        System.out.println("Expected: true,  " + checkAnagramWithExtraSpace("bcaa", "aabc"));
        System.out.println("Expected: false,  " + checkAnagramWithExtraSpace("bsc", "abc"));
    }
}
