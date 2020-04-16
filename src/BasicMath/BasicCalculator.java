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
        int prevSign = 1;
        int number = 0;

        for (char c : s.toCharArray()) {
            if (Character.isDigit(c)) {
                number = number * 10 + (c - '0');
            } else if (c == '+') {
                result += prevSign * number;
                prevSign = 1;
                number = 0;
            } else if (c == '-') {
                result += prevSign * number;
                prevSign = -1;
                number = 0;
            } else if (c == '(') {
                stack.push(result);
                stack.push(prevSign);
                result = 0;
                prevSign = 1;
            } else if (c == ')') {
                result += prevSign * number;
                number = result;
                prevSign = stack.pop();
                result = stack.pop();
            }
        }

        result += prevSign * number;
        return result;
    }
}
