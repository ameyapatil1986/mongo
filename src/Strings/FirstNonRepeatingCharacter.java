package Strings;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.*;

/**
 * https://leetcode.com/problems/first-unique-character-in-a-string/
 *
 * O(n)
 */
public class FirstNonRepeatingCharacter {

    public static int firstNonRepeating(String str) {
        Set<Character> unique = new LinkedHashSet<>();
        Set<Character> visited = new HashSet<>();

        for (char i : str.toCharArray()) {
            if (!visited.contains(i)) {
                if (!unique.contains(i)) {
                    unique.add(i);
                } else {
                    visited.add(i);
                    unique.remove(i);
                }
            }
        }

        return unique.iterator().next();
    }
}
