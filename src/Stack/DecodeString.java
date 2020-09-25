package Stack;

import java.util.*;

/**
 * https://leetcode.com/problems/decode-string/
 * https://www.geeksforgeeks.org/decode-string-recursively-encoded-count-followed-substring/
 *
 * s = "3[a]2[bc]", return "aaabcbc".
 * s = "3[a2[c]]", return "accaccacc".
 * s = "2[abc]3[cd]ef", return "abcabccdcdcdef".
 *
 *
 *  Sample example: s = "3[a2[b]]" return "abbabbabb".
 *
 *
 *  Time complexity: O(n), according to : https://zhuhan0.blogspot.com/2017/09/394-decode-string.html
 */
public class DecodeString {

    /**
     * https://medium.com/@speedforcerun/leetcode-easy-38-count-and-say-303e0bb8e51
     * https://medium.com/@rebeccahezhang/leetcode-394-decode-string-6aafb1ad6bc3
     */
    public String decodeString(String s) {
        if (s.length() == 0 || s == null) {
            return s;
        }
        Stack<String> strStack = new Stack<String>();
        Stack<Integer> nums = new Stack<Integer>();
        StringBuilder sb = new StringBuilder();
        int idx = 0;

        while (idx < s.length()) {
            if (Character.isDigit(s.charAt(idx))) {
                int num = 0;
                for ( ; idx < s.length() && Character.isDigit(s.charAt(idx)); idx++) {
                    num = num * 10 + (s.charAt(idx) - '0');
                }
                nums.push(num);
            } else if (s.charAt(idx) == '[') {
                if (sb.length() > 0) {
                    strStack.push(sb.toString());
                }
                sb = new StringBuilder();
                idx++;
            } else if (s.charAt(idx) == ']') {
                StringBuilder temp = new StringBuilder();
                if (!strStack.isEmpty()) {
                    temp = new StringBuilder(strStack.pop());
                }
                int repeatTimes = nums.pop();
                for (int i = 0; i < repeatTimes; i++) {
                    temp.append(sb);
                }
                sb = temp;
                idx++;
            } else {
                sb.append(s.charAt(idx++));
            }
        }

        return sb.toString();
    }


//    // Returns decoded string for 'str'
//    static String decode(String str) {
//        Stack<Integer> integerstack = new Stack<>();
//        Stack<Character> stringstack = new Stack<>();
//        String temp = "", result = "";
//
//        // Traversing the string
//        for (int i = 0; i < str.length(); i++) {
//            int count = 0;
//
//            // If number, convert it into number and push it into integerstack.
//            if (Character.isDigit(str.charAt(i))) {
//                while (Character.isDigit(str.charAt(i))) {
//                    count = count * 10 + str.charAt(i) - '0';
//                    i++;
//                }
//                i--;
//                integerstack.push(count);
//            }
//
//            // If closing bracket ']', pop elemment until
//            // '[' opening bracket is not found in the
//            // character stack.
//            else if (str.charAt(i) == ']') {
//                temp = "";
//                count = 0;
//
//                if (!integerstack.isEmpty()) {
//                    count = integerstack.pop();
//                }
//
//                while (!stringstack.isEmpty() && stringstack.peek()!='[' ) {
//                    temp = stringstack.pop() + temp;
//                }
//
//                if (!stringstack.empty() && stringstack.peek() == '[')
//                    stringstack.pop();
//
//                // Repeating the popped string 'temo' count number of times.
//                for (int j = 0; j < count; j++)
//                    result = result + temp;
//
//                // Push it in the character stack.
//                for (int j = 0; j < result.length(); j++)
//                    stringstack.push(result.charAt(j));
//
//                result = "";
//            }
//
//            // If '[' opening bracket, push it into character stack.
//            else if (str.charAt(i) == '[') {
//                if (Character.isDigit(str.charAt(i - 1)))
//                    stringstack.push(str.charAt(i));
//                else {
//                    stringstack.push(str.charAt(i));
//                    integerstack.push(1);
//                }
//            } else {
//                stringstack.push(str.charAt(i));
//            }
//        }
//
//        // Pop all the elmenet, make a string and return.
//        while (!stringstack.isEmpty()) {
//            result = stringstack.pop() + result;
//        }
//
//        return result;
//    }



}
