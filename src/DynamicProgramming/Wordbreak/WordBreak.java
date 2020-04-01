package DynamicProgramming.Wordbreak;

import java.util.*;

/**
 * https://leetcode.com/problems/word-break/
 * https://www.youtube.com/watch?v=YxtQUbKbdUs
 * http://www.goodtecher.com/leetcode-139-word-break/
 *
 * eg: acat
 */
public class WordBreak {

    /**9i
     * https://massivealgorithms.blogspot.com/2016/12/leetcode-472-concatenated-words.html
     */
    public boolean wordBreakWithDictionaryFrequency(String s, Map<String, String> wordDict) {

        boolean[] isWordBreak = new boolean[s.length() + 1];

        isWordBreak[0] = true;

        for (int i = 1; i <= s.length() ; i++) {
            for (int j = 0; j < i; j++) {
                if (isWordBreak[j]) {
                    if (wordDict.containsKey(s.substring(j, i))) {
                        /**
                         * reduce frequency in dictionary
                         */

                        isWordBreak[i] = true;
                        break;
                    }
                }
            }
        }

        return isWordBreak[s.length()];
    }
}
