package Strings;

/**
 * https://www.programcreek.com/2014/05/leetcode-length-of-last-word-java/
 *
 * O(n)
 */
public class LengthOfLastWord {

    public int lengthOfLastWord(String s) {

        if (s == null || s.length() == 0) {
            return 0;
        }

        int count = 0;
        int len = s.length();

        for (int i = len - 1; i >= 0; i--) {
            char c = s.charAt(i);
            if(c == ' '){
                return count;
            } else {
                count++;
            }
        }

        return s.length();
    }
}
