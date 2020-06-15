package BasicMath;

/**
 * https://zhuhan0.blogspot.com/2017/06/leetcode-227-basic-calculator-ii.html
 */
public class BasicCalculator2 {

    /**
     *   5 + 3 * 2
     */
    public int calculate(String s) {
        int prevMultiplyOrDivide = -1; // 0 is m, 1 is d
        int prevSign = -1; // 1 is +, -1 is -
        int prev = 0;
        int result = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (Character.isDigit(c)) {
                int num = 0;
                for ( ; i < s.length() && Character.isDigit(s.charAt(i)); i++) {
                    num += num * 10 + s.charAt(i) - '0';
                }
                if (prevMultiplyOrDivide == 0) {
                    prev = prev * num;
                    prevMultiplyOrDivide = -1;
                } else if (prevMultiplyOrDivide == 1) {
                    prev = prev / num;
                    prevMultiplyOrDivide = -1;
                } else {
                    prev = num;
                }
            } else if (c == '/') {
                prevMultiplyOrDivide = 1;
            } else if (c == '*') {
                prevMultiplyOrDivide = 0;
            } else if (c == '+') {
                result = result + prevSign * prev;
                prevSign = 1;
            } else if (c =='-') {
                result = result + prevSign * prev;
                prevSign = -1;
            }
        }

        result = result + prevSign * prev;
        return result;
    }
}
