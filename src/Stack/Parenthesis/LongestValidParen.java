package Stack.Parenthesis;

import java.util.*;

/**
 * https://leetcode.com/problems/longest-valid-parentheses/description/
 * https://stackoverflow.com/questions/25952326/find-the-length-of-the-longest-valid-parenthesis-sequence-in-a-string-in-on-t
 *
 *   ")())"
 */
public class LongestValidParen {

    // good string to try out: ")())"
    private int getLongestLenByStack(String s) {
        //use last to store the last matched index
        int len = s.length();
        int maxLen = 0;
        int last = -1;

        if (len == 0 || len == 1)
            return 0;

        //use this stack to store the index of '('
        Stack<Integer> stack = new Stack<Integer>();
        for (int i = 0; i < len; i++) {
            if (s.charAt(i) == '(')
                stack.push(i);
            else {
                //if stack is empty, it means that we already found a complete valid combo
                //update the last index.
                // ())(())
                if (stack.isEmpty()) {
                    last = i;
                } else {
                    stack.pop();
                    //found a complete valid combo and calculate max length
                    // eg: () or even ()()
                    if (stack.isEmpty())
                        maxLen = Math.max(maxLen, i - last);
                    else
                        // eg: (())
                        maxLen = Math.max(maxLen, i - stack.peek());
                }
            }
        }

        return maxLen;
    }
//
//    // method to get length of the longest valid
//    public static int findMaxLen(String str)
//    {
//        // Create a stack and push -1 as initial index to it.
//        Stack<Integer> stk = new Stack<>();
//        stk.push(-1);
//
//        // Initialize result
//        int result = 0;
//
//        // Traverse all characters of given string
//        for (int i = 0; i < str.length(); i++) {
//            // If opening bracket, push index of it
//            if (str.charAt(i) == '(') {
//                stk.push(i);
//            } else {
//                // Pop the previous opening bracket's index
//                stk.pop();
//
//                // Check if this length formed with base of
//                // current valid substring is more than max
//                // so far
//                if (!stk.empty()) {
//                    result = Math.max(result, i - stk.peek());
//                } else {
//                    // If stack is empty. push current index as
//                    // base for next valid substring (if any)
//                    stk.push(i);
//                }
//            }
//        }
//
//        return result;
//    }
}
