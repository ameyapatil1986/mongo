package Decimals;

/**
 * https://leetcode.com/problems/string-to-integer-atoi/
 *
 * https://www.programcreek.com/2012/12/leetcode-string-to-integer-atoi/
 *
 * Complexity: O(n)
 */
public class StringToInteger {

    public static int atoi(String str) {
        if (str == null || str.length() < 1)
            return 0;

        str = str.trim();
        int i = str.length() - 1;

        double result = 0;
        int base = 1;
        // calculate value
        for (; i >=0 && str.charAt(i) >= '0' && str.charAt(i) <= '9'; i--) {
            result = result + (str.charAt(i) - '0') * base;
            base = base * 10;
        }

        if (i == 0 && str.charAt(i) == '-') {
            result = -result;
            i--;
        }

        if (i >= 0) {
            throw new IllegalArgumentException("You provided really bad input");
        }

        // handle max and min
        if (result > Integer.MAX_VALUE)
            return Integer.MAX_VALUE;

        if (result < Integer.MIN_VALUE)
            return Integer.MIN_VALUE;


        return (int) result;
    }

    public static void main(String[] args) {
        System.out.println(atoi("231"));
    }
}
