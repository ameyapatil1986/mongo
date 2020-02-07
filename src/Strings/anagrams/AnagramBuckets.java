package Strings.anagrams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * https://leetcode.com/problems/group-anagrams/
 * https://www.careercup.com/question?id=5111068527427584
 *
 * Complexity:
 * O(m * nlogn)
 * m - number of words
 * n - length.
 *
 */
public class AnagramBuckets {

    public static String sortString(String str) {
        char[] ch = str.toCharArray();
        Arrays.sort(ch);
        return new String(ch);
    }


    public static void anagramBuckets(List<String> anagramBuckets) {
        Map<String, List<String>> bucketMap = new HashMap<>();
        anagramBuckets.stream()
            .forEach(word -> bucketMap.putIfAbsent(sortString(word), new ArrayList<>()).add(word));
    }

    public static void main(String[] args) {

    }

}
