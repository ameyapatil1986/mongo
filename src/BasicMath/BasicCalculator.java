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
        int prevNumber = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                prevNumber = 0;
                for (; i < s.length() && Character.isDigit(s.charAt(i)); i++) {
                    prevNumber += prevNumber * 10 + s.charAt(i) - '0';
                }
            } else if (c == '+') {
                result += prevSign * prevNumber;
                prevSign = 1;
            } else if (c == '-') {
                result += prevSign * prevNumber;
                prevSign = -1;
            } else if (c == '(') {
                stack.push(result);
                stack.push(prevSign);
                result = 0;
                prevSign = 1;
            } else if (c == ')') {
                result += prevSign * prevNumber;
                prevNumber = result;
                prevSign = stack.pop();
                result = stack.pop();
            }
        }

        result += prevSign * prevNumber;
        return result;
    }
}
