package Strings.edit;

/**
 * https://leetcode.com/problems/one-edit-distance/
 * https://www.programcreek.com/2014/05/leetcode-one-edit-distance-java/
 */
public class OneEditDistance {

    public boolean isOneEditDistance(String s, String t) {
        if(s==null || t==null)
            return false;

        if(Math.abs(s.length() - t.length()) > 1){
            return false;
        }

        int i = 0;
        int j = 0;
        int count = 0;

        while (i < s.length() && j < t.length()){
            if (s.charAt(i)==t.charAt(j)){
                i++;
                j++;
            } else {
                count++;

                if (count>1)
                    return false;

                if (s.length() > t.length()) {
                    i++;
                } else if(s.length() < t.length()){
                    j++;
                } else {
                    i++;
                    j++;
                }
            }
        }

        // abc, abcd
        if (i < s.length() || j < s.length()){
            count++;
        }

       return count == 1;
    }
}
