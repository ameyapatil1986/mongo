package BasicMath;

import java.util.Stack;


/**
 * https://zhuhan0.blogspot.com/2017/06/leetcode-224-basic-calculator.html
 */
public class BasicCalculator {

    /**
     *  5 + (3 - 2 + 1) - 2
     */
    public int calculate(String s) {
        Stack<Integer> stack = new Stack<>();
        int result = 0;
        int sign = 1;
        int number = 0;

        for (char c : s.toCharArray()) {
            if (Character.isDigit(c)) {
                number = number * 10 + (c - '0');
            } else if (c == '+') {
                result += sign * number;
                sign = 1;
                number = 0;
            } else if (c == '-') {
                result += sign * number;
                sign = -1;
                number = 0;
            } else if (c == '(') {
                stack.push(result);
                stack.push(sign);
                result = 0;
                sign = 1;
            } else if (c == ')') {
                result += sign * number;
                number = result;
                sign = stack.pop();
                result = stack.pop();
            }
        }

        result += sign * number;
        return result;
    }
}
