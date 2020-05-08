package Strings.palindrome;

/**
 * Complexity:
 * O(n)
 */
public class PalindromMatch {

    public static double countPalindrome(String s) {
        int pairQ = 0;
        int nonPairQ = 0;

        for (int i = 0; i < s.length()/2 ; i++) {

            if (s.charAt(i) == '?' && s.charAt(s.length() -1 -i) == '?') {
                pairQ++;
            } else if (s.charAt(i) == '?' || s.charAt(s.length() -1 -i) == '?') {
                nonPairQ++;
            } else {
                if (s.charAt(i) != s.charAt(s.length() -1 -i)) {
                    return 0;
                }
            }
        }

        if (s.length() % 2 == 1 && s.charAt(s.length()/2) == '?') {
            pairQ++;
        }

        return pairQ == 0 ? nonPairQ : Math.pow(26, pairQ) + nonPairQ;
    }

    public static void main(String[] args) {
        System.out.println(countPalindrome("ab?ba"));
        System.out.println(countPalindrome("ab?xa"));
        System.out.println(countPalindrome("s?rus"));
        System.out.println(countPalindrome("s?r?s"));
        System.out.println(countPalindrome("??r??"));
        System.out.println(countPalindrome("???"));
        System.out.println(countPalindrome("??"));
        System.out.println(countPalindrome("?"));
    }
}

