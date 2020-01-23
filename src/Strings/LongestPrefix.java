package Strings;

import java.util.*;

/**
 * https://leetcode.com/articles/longest-common-prefix/
 *
 * Complexity: http://www.geeksforgeeks.org/longest-common-prefix-set-4-binary-search/
 *
 * N = Number of strings
 * M = Length of the largest string string
 *
 * Time complexity is O(NM log M)
 *
 *
 * Sample example
 * [leetcode, leet, letgo]
 *
 */
public class LongestPrefix {


    public static String longestCommonPrefix(List<String> strs) {
        if (strs == null || strs.size() == 0)
            return "";

        int minLen = strs.stream().min(Comparator.comparing(word -> word.length())).get().length();

        int low = 1;
        int high = minLen;
        int middle = 0;

        while (low <= high) {
            middle = (low + high) / 2;
            System.out.println(" low: " + low + " high: " + high + " middle:  " + middle + " --- word: -- " + strs.get(0).substring(0, middle));
            if (isCommonPrefix(strs, middle))
                low = middle + 1;
            else
                high = middle - 1;
        }

        return strs.get(0).substring(0, middle);
    }

    private static boolean isCommonPrefix(List<String> strs, int len) {
        // https://www.concretepage.com/java/jdk-8/java-8-stream-allmatch-anymatch-nonematch-example
        final String subString = strs.get(0).substring(0,len);
        return strs.stream().allMatch(e -> e.startsWith(subString));
    }

    public static void main(String[] main) {

        // System.out.println("abcd".substring(0, 2)); // prints ab.

        String[] strList = {"leets", "leetcode", "leet", "leeds"};
        System.out.println(longestCommonPrefix(Arrays.asList(strList)));
    }
}

