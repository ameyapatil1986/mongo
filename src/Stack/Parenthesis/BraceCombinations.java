package Stack.Parenthesis;

import java.util.LinkedList;

/**
 * https://leetcode.com/problems/generate-parentheses/
 *
 *
 * References:
 * http://www.geeksforgeeks.org/print-all-combinations-of-balanced-parentheses/
 * https://bitbucket.org/ameyapatil/all-images/src/9e05e61ccdb670d48021cb2af9a4ac4423d3db39/TreeFormula.JPG?at=master
 * http://codereview.stackexchange.com/questions/46003/print-all-combinations-of-balanced-parentheses
 *
 * Diagrams:
 * https://bitbucket.org/ameyapatil/all-images/commits/e4a3da0583a85624b2fced788e725770a76d8cb8
 * https://bitbucket.org/ameyapatil/all-images/src/9e05e61ccdb670d48021cb2af9a4ac4423d3db39/TreeFormula.JPG?at=master
 *
 * Given ( and ) and length of 4, generate the following combinations.
 * ()()
 * (())
 *
 * BB: 2
 *
 * Complexity:
 * ------------
 * Aux complexity: O(length), where length is same as height
 * Time complexity: O( length * (2(length) -1)), where length is same as height
 *
 */
public final class BraceCombinations {

    private BraceCombinations() {}

    /**
     * Returns sets of valid combinations.
     *
     * @param length    the sum of length of all- opening + closing braces
     * @return          the valid brace combinations
     */
    public static void getBraceCombinations(int length) {
        if (length <= 0) {
            throw new IllegalArgumentException("The length should be greater than zero");
        }

        if (length % 2 == 1) {
            throw new IllegalArgumentException("The length should be positive");
        }

        generate(new LinkedList<Character>(), 0, 0, length/2);
    }

    // length == height == arraynindex
    private static void generate(LinkedList<Character> parenthesis, int openBrace, int closeBrace, int halfLength) {
        if (openBrace == halfLength && closeBrace == halfLength) {
            for (Character ch : parenthesis) {
                System.out.print(ch);
            }
            System.out.println();
        }

        if (openBrace > halfLength || closeBrace > openBrace) {
            return;
        }
        parenthesis.add('(');
        generate(parenthesis, openBrace + 1, closeBrace, halfLength);
        parenthesis.removeLast();

        parenthesis.add(')');
        generate(parenthesis, openBrace, closeBrace + 1, halfLength);
        parenthesis.removeLast();
    }

    public static void main(String[] args) {
        // getBraceCombinations(2);
        System.out.println("----");
        getBraceCombinations(4);

        //        List<List<Character>> paths1 = Arrays.asList(
        //                Arrays.asList('(', ')')
        //            );
        //        assertEquals(paths1, getBraceCombinations(2));
        //
        //        List<List<Character>> paths2 = Arrays.asList(
        //                Arrays.asList('(', '(', ')', ')'),
        //                Arrays.asList('(', ')', '(', ')')
        //            );
        //        assertEquals(paths2, getBraceCombinations(4));
    }
}
