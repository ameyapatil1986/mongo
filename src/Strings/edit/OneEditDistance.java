package Strings.edit;

/**
 * https://leetcode.com/problems/one-edit-distance/
 * https://www.programcreek.com/2014/05/leetcode-one-edit-distance-java/
 *
 *  Complexity:
 *  O(n)
 */
public class OneEditDistance {

    public boolean isOneEditDistance(String s, String t) {
        if(s==null || t==null)
            return false;

        if(Math.abs(s.length() - t.length()) > 1){
            return false;
        }

        int diff = 0;
        if (s.length() == t.length()) {
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) != t.charAt(i)) {
                    diff++;
                    if (diff > 1) {
                        return false;
                    }
                }
            }

            return true;
        }

        return s.substring(0, s.length() - 1).equals(t) || s.substring(1, s.length()).equals(t);
    }
}
