package Strings;

import java.util.LinkedHashSet;
import java.util.Set;


/**
 * https://leetcode.com/problems/first-unique-character-in-a-string/
 */
public class FirstNonRepeatingCharacter {

    public static int firstNonRepeating(String str) {
        Set<Character> unique = new LinkedHashSet<>();
        Set<Character> visited = new LinkedHashSet<>();

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
