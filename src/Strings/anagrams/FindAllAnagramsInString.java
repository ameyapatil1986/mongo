package Strings.anagrams;

import java.util.*;


/**
 * https://leetcode.com/problems/find-all-anagrams-in-a-string/
 */
public class FindAllAnagramsInString {

    public static List<Integer> count(String s, String p) {

        List<Integer> result = new ArrayList<>();

        final Map<Character, Integer> mapOfPCount = new HashMap<>();
        for (int i = 0; i < p.length(); i++) {
            mapOfPCount.putIfAbsent(p.charAt(i), mapOfPCount.getOrDefault(p.charAt(i), 0) + 1);
        }

        final Map<Character, Integer> mapOfSCount = new HashMap<>();
        int count = 0;
        for (int i = 0; i < s.length(); i++) {

            if (mapOfPCount.containsKey(s.charAt(i))) {
                mapOfSCount.putIfAbsent(p.charAt(i), mapOfPCount.getOrDefault(p.charAt(i), 0) + 1);
                count++;

                if (count == p.length()) {
                    if (mapOfPCount.equals(mapOfSCount)) {
                        result.add(i - p.length());
                    }
                }
            } else {
                // O (p)
                // https://stackoverflow.com/questions/6757868/map-clear-vs-new-map-which-one-will-be-better
                mapOfSCount.clear();
                count = 0;
            }
        }

        return result;
    }
}
