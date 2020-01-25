package BasicMath;

/**
 * https://zhuhan0.blogspot.com/2017/06/leetcode-227-basic-calculator-ii.html
 */
public class BasicCalculator2 {

    public int calculate(String s) {
        int md=-1; // 0 is m, 1 is d
        int sign=1; // 1 is +, -1 is -
        int prev=0;
        int result=0;

        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);

            if (Character.isDigit(c)) {

                int num = 0;
                for ( ;i < s.length() && Character.isDigit(s.charAt(i)); i++) {
                    num += num * 10 + s.charAt(i) - '0';
                }

                if (md == 0) {
                    prev = prev * num;
                    md = -1;
                } else if (md == 1) {
                    prev = prev / num;
                    md = -1;
                } else {
                    prev = num;
                }
            } else if (c=='/') {
                md = 1;
            } else if (c=='*') {
                md = 0;
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

//        public int calculate(String s) {
//            char operand = '+';
//            int i = 0;
//            int number = 0;
//            int previous = 0;
//            int result = 0;
//
//            while (i < s.length() || number > 0) {
//                char c = i < s.length() ? s.charAt(i) : 'e';
//                if (Character.isDigit(c)) {
//                    number = number * 10 + (c - '0');
//                } else if (c != ' ') {
//                    if (operand == '+') {
//                        result += number;
//                        previous = number;
//                    } else if (operand == '-') {
//                        result -= number;
//                        previous = -number;
//                    } else if (operand == '*') {
//                        result -= previous;
//                        result += previous * number;
//                        previous *= number;
//                    } else {
//                        result -= previous;
//                        result += previous / number;
//                        previous /= number;
//                    }
//                    number = 0;
//                    operand = c;
//                }
//                i++;
//            }
//            return result;
//        }
}
