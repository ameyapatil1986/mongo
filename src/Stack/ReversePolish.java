package Stack;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;


/**
 *
 * https://leetcode.com/problems/evaluate-reverse-polish-notation/
 * https://www.careercup.com/question?id=4906033149378560
 */
public class ReversePolish {

    private static boolean isOperator(String ch) {
        return ch == "+" || ch == "-" || ch == "*" || ch == "/";
    }

    public static String postFix(List<String> postFix) {
        Stack<String> stack = new Stack<String>();

        for (String i : postFix) {
            if (isOperator(i)) {
                double rightOperand = Double.parseDouble(stack.pop());
                double leftOperand = Double.parseDouble(stack.pop());

                switch(i) {
                    case "+" : stack.push(String.valueOf(leftOperand + rightOperand));
                        break;
                    case "-" : stack.push(String.valueOf(leftOperand - rightOperand));
                        break;
                    case "*" : stack.push(String.valueOf(leftOperand * rightOperand));
                        break;
                    case "/" : stack.push(String.valueOf(leftOperand / rightOperand));
                        break;
                }
            } else {
                stack.push(i);
            }
        }

        return stack.pop();
    }


    public static void main(String[] args) {
        List<String> list = Arrays.asList("4", "1", "+", "2.5", "*");
        System.out.println(postFix(list));
    }
}
