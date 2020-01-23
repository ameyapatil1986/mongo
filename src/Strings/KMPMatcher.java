package Strings;

import java.util.regex.Pattern;

/**
 * https://leetcode.com/problems/implement-strstr/
 *
 * KMP matcher:
 * abaa -> [0 1 1 1]
 *
 * abcabcabd
 * abcabd
 *
 */


/**
 *
 * https://github.com/Blankj/awesome-java-leetcode/blob/master/note/028/README.md
 *
 * Watch all tushar's videos to understand whats happening:
 * -------------------------------------------------
 * 1. https://bitbucket.org/ameyapatil/all-images/commits/5458385b5ba0826986a9bcbc2ed35ffa8efc6025
 * 2. https://www.youtube.com/watch?v=GTJr8OvyEVQ
 *
 * https://www.youtube.com/watch?v=GTJr8OvyEVQ
 * https://www.youtube.com/watch?v=HBI-7Hs0Lx4
 * https://www.youtube.com/watch?v=5i7oKodCRJo
 * https://www.youtube.com/watch?v=SVKe7bvQ4Xk
 * https://www.youtube.com/watch?v=UsmvbZGKdlM
 * https://www.youtube.com/watch?v=DMmMVy3y1FI
 * https://www.youtube.com/watch?v=2ogqPWJSftE&list=LLLoH4D8grKWbeZEt1ZLYPVg
 * https://www.youtube.com/watch?v=GTJr8OvyEVQ
 *
 *
 * Naive:
 * http://www.geeksforgeeks.org/searching-for-patterns-set-1-naive-pattern-searching/
 * http://www.geeksforgeeks.org/pattern-searching-set-4-a-naive-string-matching-algo-question/
 *
 *
 * Rabin karp:
 * http://www.geeksforgeeks.org/searching-for-patterns-set-3-rabin-karp-algorithm/
 *
 * KMP algo:
 * http://www.geeksforgeeks.org/searching-for-patterns-set-2-kmp-algorithm/
 *
 *
 * Code:
 * http://www.fmi.uni-sofia.bg/fmi/logic/vboutchkova/sources/KMPMatch_java.html             ( best link )
 * http://tekmarathon.com/2013/05/14/algorithm-to-find-substring-in-a-string-kmp-algorithm/
 *
 * BB:
 * ---
 * 19
 *
 *
 * Complexity:
 * -----------
 * O(n)
 *
 */
public final class KMPMatcher {

    private KMPMatcher() { }

    /**
     *  https://www.youtube.com/watch?v=UsmvbZGKdlM
     */
    /*
     * Assume ptrn is - abcabd, then lps would be:   [0 0 0 1 2 0]
     * Assume ptrn is - abaa, then lps would be: [0 0 1 1]
     * Assume ptrn is - YBYBO, then lps would be:   [0 0 1 2 0]
     * Assume ptrn is - YBCYBCO, then lps would be: [0 0 0 1 2 3 0]
     *
     * LPS: longest proper prefix which is also suffix
     */
    public static int[] computeLPS(String ptrn) {
        int j = 0;
        int[] lps = new int[ptrn.length()];
        lps[0] = 0;

        for (int i = 1; i < ptrn.length(); i++) {

            /**
             * https://www.youtube.com/watch?v=UsmvbZGKdlM
             * eg: abaa
             */
            while (j > 0 && ptrn.charAt(i) != ptrn.charAt(j)) {
                j = lps[j - 1];
            }

            if (ptrn.charAt(i) == ptrn.charAt(j)) {
                j++;
            }

            lps[i] = j;
        }

        return lps;
    }


    /**
     * https://www.youtube.com/watch?v=HBI-7Hs0Lx4
     * eg:
     * abcabcabd
     * abcabd       [0 0 0 1 2 0]
     */
    public static void searchSubString(String text, String ptrn) {
        int j = 0;
        if (text.length() == 0) return;


        // initialize new array and preprocess the pattern
        int[] lps = computeLPS(ptrn);

        // i traverses the text, the j traverses the pattern, but j can go back and front.
        for (int i = 0; i < text.length(); i++) {
            // abcabcabd
            // abcabd
            while (j > 0 && text.charAt(i) != ptrn.charAt(j)) {
                j = lps[j - 1];
            }

            if (text.charAt(i) == ptrn.charAt(j)) {
                j++;
            }

            // a match is found
            if (j == ptrn.length()) {
                /**
                 * https://leetcode.com/problems/implement-strstr/
                 * return j - ptrn.length
                 */
                System.out.println("found substring at index:" + (i - ptrn.length() + 1));
                j = 0;
            }
        }
    }

    public static void main(String[] args) {
        searchSubString("ameya", "eya");
        searchSubString("ameyambyeamb", "amb");
    }

}
