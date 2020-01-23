package Arrays;

import java.util.HashMap;
import java.util.*;


/**
 * https://leetcode.com/problems/letter-combinations-of-a-phone-number/
 *
 * O(strLength ^ numberofstrings)
 */
public class FacebookShuffle {

    static Map<Integer, String> map = new HashMap<>();
    static {
        map.put(1, "ABC");
        map.put(2, "DEF");
    }


    public static void shuffle(String[] strlist, int currStringIndex, char[] buff) {

        if (strlist.length == currStringIndex) {
            System.out.println(new String(buff));
            return;
        }

        String str = strlist[currStringIndex];

        for (int i = 0; i < str.length(); i++) {
            buff[currStringIndex] = str.charAt(i);
            shuffle(strlist, currStringIndex + 1, buff);
        }
    }

    public static void main(String[] args) {

        int x = 6132875;
        String[] strArray = new String[7];
        int i = 0;
        for ( ; x > 0; x = x / 10 ) {
            strArray[i++] = map.get(x % 10);
        }

        char[] ch = new char[5];
        shuffle(strArray, 0, ch);
    }
}
