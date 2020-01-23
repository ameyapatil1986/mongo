package BasicMath;

/**
 * https://zhuhan0.blogspot.com/2017/06/leetcode-227-basic-calculator-ii.html
 */
public class BasicCalculator2 {

        public int calculate(String s) {
            char operand = '+';
            int i = 0;
            int number = 0;
            int previous = 0;
            int result = 0;

            while (i < s.length() || number > 0) {
                char c = i < s.length() ? s.charAt(i) : 'e';
                if (Character.isDigit(c)) {
                    number = number * 10 + (c - '0');
                } else if (c != ' ') {
                    if (operand == '+') {
                        result += number;
                        previous = number;
                    } else if (operand == '-') {
                        result -= number;
                        previous = -number;
                    } else if (operand == '*') {
                        result -= previous;
                        result += previous * number;
                        previous *= number;
                    } else {
                        result -= previous;
                        result += previous / number;
                        previous /= number;
                    }
                    number = 0;
                    operand = c;
                }
                i++;
            }
            return result;
        }
}
