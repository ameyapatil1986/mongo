package Strings.susbsequence;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;

import java.util.stream.Collectors;

/**
 * References:
 * -----------
 * http://www.geeksforgeeks.org/find-the-smallest-window-in-a-string-containing-all-characters-of-another-string/
 * http://stackoverflow.com/questions/16368153/method-to-find-the-shortest-substring-containing-the-given-wordsoptimization-re
 * http://leetcode.com/2010/11/finding-minimum-window-in-s-which.html
 * http://leetcode.com/2010/11/finding-minimum-window-in-s-which.html
 * http://codereview.stackexchange.com/questions/48375/find-minimum-window-size-which-includes-the-subset-of-characters
 *
 * BB: 15
 *
 * Complexity
 * O(n)
 * where n is number of strings,
 *
 */
public final class MinStringWindow {

    private MinStringWindow() {}

    // https://stackoverflow.com/questions/54506757/how-to-populate-a-map-in-java-8
    public static String minWindowSize(String string, List<Character> chars) {
        final Map<Character, Long> inputCharacterCountMap = chars.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        final Map<Character, Long> foundCharacterCountMap = new HashMap<Character,  Long>();

        boolean allCharsFound = false;
        boolean firstMatch = true;
        int size = 0;

        int j = 0; // the trailing position of the sliding window
        int i = 0; // the leading position of the sliding window.

        int minJ = 0;
        int minI = 0;

        int min = Integer.MAX_VALUE;

        char[] charsArr = string.toCharArray();

        for (i = 0; i < charsArr.length; i++) {

            if (inputCharacterCountMap.containsKey(charsArr[i])) {

                // step 1: first match.
                if (firstMatch) {
                    firstMatch = false;
                    j = i;  // later on, j  will get modified in the for loop.
                }

                // step 2: add count.
                foundCharacterCountMap.put(charsArr[i], foundCharacterCountMap.getOrDefault(charsArr[i], 0l) + 1);


                // step 3: advance size
                if (foundCharacterCountMap.get(charsArr[i]) <= inputCharacterCountMap.get(charsArr[i])) {
                    size++;
                }

                // step 4: advance the backpointer.
                // advance the left pointer, such the window (i-j) is as small as possible.
                // example: xypqrxyz, and we are looking for xyz
                for (; !foundCharacterCountMap.containsKey(charsArr[j]) || foundCharacterCountMap.get(charsArr[j]) > inputCharacterCountMap.get(charsArr[j]); j++) {
                    if (foundCharacterCountMap.containsKey(charsArr[j])) {
                        foundCharacterCountMap.put(charsArr[i], foundCharacterCountMap.get(charsArr[j]) - 1);
                    }
                }

                // step 5 : calculate min
                if (size == chars.size()) {
                    if ((i - j + 1) < min) {
                        min = i - j + 1;
                        minJ = j;
                        minI = i;
                    }
                    allCharsFound = true;
                }
            }
        }

        if (!allCharsFound) {
            throw new IllegalArgumentException("The subset is not found in the input string.");
        }

        StringBuilder sb = new StringBuilder();
        for (int k = minJ; k <= minI; k++) {
            sb.append(charsArr[k]);
        }

        return sb.toString();
    }
}
