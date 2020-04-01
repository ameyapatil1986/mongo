package Strings;

import java.util.*;


/**
 * https://leetcode.com/problems/partition-labels/
 */
public class ParititionLabels {

    public static List<Integer> partitionLabels(String str) {
        Map<Character, Integer> lastIndex = new HashMap<>();

        for (int i = 0; i < str.length(); ++i)
            lastIndex.put(str.charAt(i), i);

        List<Integer> ans = new ArrayList<>();

        int start = 0;
        int end = 0;

        for (int i = 0; i < str.length(); ++i) {
            end = Math.max(end, lastIndex.get((int)str.charAt(i)));
            if (i == end) {
                ans.add(end - start + 1);
                start = end + 1;
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        String str = "ababcbacadefegdehijhklij";
        for (int i : partitionLabels(str)) {
            System.out.print(i + " ");
        }
    }

}
