package Decimals;

import java.util.HashMap;
import java.util.Map;



//Given a roman numeral, convert it to an integer. (I: 1, V: 5, X: 10, L: 50, C: 100, D: 500, M: 1000)
//Input is guaranteed to be within the range from 1 to 3999.
//
//
//IV: 4 (5-1), MDC = 1000 + 500 + 100
//
//if VI left hand  > right hand => add them.
//if IV left hand  < right hand => subtract them.
//
//XVI -> 10 + 5 + 1 =>       16 -> XVI
//XIV -> 10 + (5 - 1) => 14.
//X  -> 10;
//XI -> 10 + 1
//IX -> 10 - 1
//XII -> 10 + 1 + 1 = 12.
//XX
//
//16 -> XVI
//14 -> XIV
//
//49 -> XLIX
//39 -> XXXIX
//59 -> LIX

public final class RomanNumeral {

    private RomanNumeral() {
    }

    final static Map<Character, Integer> romanNumralMap;
    static {
        romanNumralMap = new HashMap<>();
        romanNumralMap.put('I', 1);
        romanNumralMap.put('V', 5);
        romanNumralMap.put('X', 10);
        romanNumralMap.put('L', 50);
        romanNumralMap.put('C', 100);
        romanNumralMap.put('D', 500);
        romanNumralMap.put('M', 1000);
    }

    // 49 -> XLIX
    // 39 -> XXXIX
    // 59 -> LIX
    /**
     *  Converts an integer number into roman numeral.
     *  In roman numeral we can have same character repeat maximum 3 times.
     *  On the fourth time, we need it to preceed the next higher character.
     *  eg: III means 3, but to represent 4, we will use IV instead of IIII.
     *
     */
    public static String romanNumaralConver(int num) {

        String str = "";
        // MeDiCine
        char[] romanLetters = {'M', 'D', 'C', 'L', 'X', 'V', 'I'};
        char[] romanValues =  {1000, 500, 100, 50, 10, 5, 1};

        for (int i = 0; i < romanValues.length; i++) {

            while (num / romanValues[i] > 0) {
                str += romanLetters[i];
                num -= romanValues[i];
            }
        }

        //simplify format using -1 rule.  Special Cases IV IX XL XC CD DM
        str = str.replace("DCCCC", "CM"); //400
        str = str.replace("CCCC",  "CD"); //400
        str = str.replace("LXXXX", "XC"); //90
        str = str.replace("XXXX",  "XL"); //40
        str = str.replace("VIIII", "IX"); //9
        str = str.replace("IIII",  "IV"); //5
        return str;

    }

    /**
     * IV   -> 5 - 1
     * VI   -> 5 + 1
     * VIII -> 5 + 1 + 1 + 1
     */
    public static int romanNumeral(String str) {

        int sum = 0;
        for (int i = 0; i < str.length(); i++) {
            // V1111
            if (i == str.length() - 1 || (romanNumralMap.get(str.charAt(i)) >= romanNumralMap.get(str.charAt(i + 1)))) {
                sum = sum + romanNumralMap.get(str.charAt(i));
            }
            // 1V
            else  {
                sum = sum + (romanNumralMap.get(str.charAt(i + 1)) - romanNumralMap.get(str.charAt(i)));
                i++;
            }
        }

        return sum;
    }
}
