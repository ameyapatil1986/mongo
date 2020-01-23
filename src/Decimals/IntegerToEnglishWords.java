package Decimals;

import java.util.HashMap;
import java.util.*;

/**
 * Convert a non-negative integer to its english words representation.
 * Given input is guaranteed to be less than 231 - 1.
 *
 * For example,
 * 123 -> "One Hundred Twenty Three"
 * 12345 -> "Twelve Thousand Three Hundred Forty Five"
 * 1234567 -> "One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"
 */
public class IntegerToEnglishWords {
    final static  Map<Integer, String> map = new HashMap<>();

    static {
        /* include all numbers from 0 to 999 */
        map.put(012, "Twelve");
        map.put(12, "Twelve");
        map.put(125, "One hundred and twenty five");
        map.put(745, "Seven hundred and fourty five");
    }

    private static String[] str = {"", "Thousand", "Million", "Billion"};

    public static List<String> getInteger(int number) {
        final List<String> list = new ArrayList<>();
        int i = 0;

        while (number > 0) {
            int remainder = number % 1000;
            list.add(map.get(remainder) + " " + str[i++] + " ");
            number = number / 1000;
        }

        Collections.reverse(list);
        return list;
    }


    public static void main(String[] args) {
        System.out.println(getInteger(12125745));
    }
}
