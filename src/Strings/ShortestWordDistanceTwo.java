package Strings;

import java.util.*;

/**
 * https://cheonhyangzhang.wordpress.com/2017/01/07/244-leetcode-java-shortest-word-distance-ii-medium/
 */
public class ShortestWordDistanceTwo {

    HashMap<String, List<Integer>> indexes = new HashMap<String, List<Integer>>();

    public ShortestWordDistanceTwo(String[] words) {
        for (int i = 0; i < words.length; i ++) {
            if (!indexes.containsKey(words[i])) {
                indexes.put(words[i], new ArrayList<Integer>());
            }
            indexes.get(words[i]).add(i);
        }
    }

    public int shortest(String word1, String word2) {
        final List<Integer> indexes1 = indexes.get(word1);
        final List<Integer> indexes2 = indexes.get(word2);
        int min = Integer.MAX_VALUE;
        int i = 0, j = 0;
        while (i < indexes1.size() && j < indexes2.size()) {
            int i1 = indexes1.get(i);
            int i2 = indexes2.get(j);
            min = Math.min(min, Math.abs(i1 - i2));
            if (i1 < i2) {
                i ++;
            }
            else {
                j ++;
            }
        }
        return min;
    }
}
