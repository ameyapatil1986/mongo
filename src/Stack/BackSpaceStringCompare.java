package Stack;

import java.util.Stack;

/**
 * https://helloacm.com/the-backspace-string-compare-algorithm/
 *
 * Example 1:
 * Input: S = “ab#c”, T = “ad#c”
 * Output: true
 * Explanation: Both S and T become “ac”.
 *
 * Example 2:
 * Input: S = “ab##”, T = “c#d#”
 * Output: true
 * Explanation: Both S and T become “”.
 *
 * Example 3:
 * Input: S = “a##c”, T = “#a#c”
 * Output: true
 * Explanation: Both S and T become “c”.
 *
 * Example 4:
 * Input: S = “a#c”, T = “b”
 * Output: false
 * Explanation: S becomes “c” while T becomes “b”.
 */
public class BackSpaceStringCompare {

    class Solution {
        public boolean backspaceCompare(String S, String T) {
            return build(S).equals(build(T));
        }

        public String build(String S) {
            Stack<Character> ans = new Stack();

            for (char c: S.toCharArray()) {
                if (c != '#')
                    ans.push(c);
                else if (!ans.empty())
                    ans.pop();
            }

            StringBuilder sb = new StringBuilder();
            while (!ans.isEmpty()) {
                sb.append(ans.pop());
            }
            sb.reverse();

            return sb.toString();
        }
    }


}
