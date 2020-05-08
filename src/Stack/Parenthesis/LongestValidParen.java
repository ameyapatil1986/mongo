package Stack.Parenthesis;

import java.util.*;

/**
 * https://leetcode.com/problems/longest-valid-parentheses/description/
 * https://stackoverflow.com/questions/25952326/find-the-length-of-the-longest-valid-parenthesis-sequence-in-a-string-in-on-t
 *
 *   ")())"
 *
 *  Complexity:
 *  O (n) - both time and space.
 *
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
                //if stack is empty, it means that we already found a complete valid combo update the last index ())(())
                if (stack.isEmpty()) {
                    last = i;
                } else {
                    stack.pop();
                    //found a complete valid combo and calculate max length. eg: () or even ()()
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
}
