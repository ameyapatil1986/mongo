package Strings;

/**
 *   Input:  s1 = "geeks", s2 = "geek"
     Output: yes
     Number of edits is 1

     Input:  s1 = "geeks", s2 = "geeks"
     Output: no
     Number of edits is 0

     Input:  s1 = "geaks", s2 = "geeks"
     Output: yes
     Number of edits is 1

     Input:  s1 = "peaks", s2 = "geeks"
     Output: no
     Number of edits is 2
 */
public class IsOneWordEdit {

    public static boolean isOneWordEdit(String s1, String s2) {

        int diff = 0;

        if (Math.abs(s1.length() - s2.length()) > 1) {
            return false;
        }

        if (s1.length() == s2.length()) {
            for (int i = 0; i < s1.length(); i++) {
                if (s1.charAt(i) != s2.charAt(i)) {
                    diff++;
                    if (diff > 1) {
                        return false;
                    }
                }
            }
            return diff == 1;
        }

        if (s1.length() > s2.length()) {
            return s1.substring(0, s1.length() -1).equals(s2);
        } else {
            return s2.substring(0, s2.length() -1).equals(s1);
        }
    }

    public static void main(String[] args) {
        System.out.println(isOneWordEdit("geeks", "geek"));
        System.out.println(isOneWordEdit("geeks", "geeks"));
        System.out.println(isOneWordEdit("geaks", "geeks"));
        System.out.println(isOneWordEdit("peaks", "geeks"));
    }
}
