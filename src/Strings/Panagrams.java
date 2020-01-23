package Strings;

import java.util.*;

/**
 *
 * Reference:
 * https://www.careercup.com/question?id=5699257984090112
 *
 * Pangrams are sentences constructed by using every letter of the alphabet at least once.
 *
 * BB:
 * O(n)
 *
 */
public class Panagrams {

    public static boolean ispanagram(String str) {

        Set<Character> hashSet = new HashSet<Character>();
        for (int i = 0; i < str.length(); i++) {
            hashSet.add(str.charAt(i));
        }

        return hashSet.size() == 26;
    }

}
