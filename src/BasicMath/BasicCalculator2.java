package BasicMath;

/**
 * https://zhuhan0.blogspot.com/2017/06/leetcode-227-basic-calculator-ii.html
 */
public class BasicCalculator2 {

    /**
     *   5 + 3 * 2
     */
    public int calculate(String s) {
        int multiplyOrDivide=-1; // 0 is m, 1 is d
        int sign=-1; // 1 is +, -1 is -
        int prev=0;
        int result=0;

        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);

            if (Character.isDigit(c)) {

                int num = 0;
                for ( ; i < s.length() && Character.isDigit(s.charAt(i)); i++) {
                    num += num * 10 + s.charAt(i) - '0';
                }

                if (multiplyOrDivide == 0) {
                    prev = prev * num;
                    multiplyOrDivide = -1;
                } else if (multiplyOrDivide == 1) {
                    prev = prev / num;
                    multiplyOrDivide = -1;
                } else {
                    prev = num;
                }
            } else if (c=='/') {
                multiplyOrDivide = 1;
            } else if (c=='*') {
                multiplyOrDivide = 0;
            } else if (c=='+') {
                result = result + sign * prev;
                sign = 1;
            } else if (c =='-') {
                result = result + sign * prev;
                sign = -1;
            }
        }

        result = result + sign * prev;
        return result;
    }
}
