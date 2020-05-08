package Strings;

/**
 * https://www.geeksforgeeks.org/given-two-strings-find-first-string-subsequence-second/
 *
 * O(n)
 */
public class DoesExistInSameSequence {

    public static boolean doesExistInSequence(String str1, String str2) {
        int i = 0;
        int j = 0;
        for (; i < str1.length(); i++) {
            if (str1.charAt(i) == str2.charAt(j)) {
                j++;
                if (j == str2.length()) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(doesExistInSequence("geeksforgeeks", "gksrg"));
    }
}
