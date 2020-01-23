package DynamicProgramming;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Leetcode:
 * https://leetcode.com/problems/climbing-stairs/
 */

/**
 * Qualified example:
 * https://bitbucket.org/ameyapatil/all-images/commits/088f35c5539b544f67017a59ceba0e24bf0f4588
 *
 */

/**
 *
 * Question is here: http://dailyjobquestions.com/2011/10/17/stairs/
 * Explanation: of why or how stair counting relates to fibo.
 * Assume the question was as simple as:
 * given 5 stairs, count how many ways you can climb stairs taking 1 step / 2 steps:
 *
 * return fibo (n - 1) + fibo (n - 2) where n == 5
 *
 * - Now there are only 2 ways to reach 5th step.
 * - 1. either take the 4th step and climb 1 step. ie fibo(n - 1)
 * - 2. or take the 3rd step and climb 2 steps. ie fibo(n - 2)
 *
 * So how many ways can you reach the "4th" step ?
 * Ans:
 * Same thing:
 * - 1. either take 3rd step and climb the 1 step to reach 4th. ie fibo(n - 1)
 * - 2. or take the 2nd step and climb 2 steps. ie fibo(n - 2)
 *
 * Note:
 * There is another dirty method to solve fibo using arrays which should never be done.
 * http://www2.its.strath.ac.uk/courses/c/subsection3_4_6.html
 *
 * References:
 * -----------
 * http://dailyjobquestions.com/2011/10/17/stairs/
 * http://www.careercup.com/question?id=3590768
 * http://stackoverflow.com/questions/360748/computational-complexity-of-fibonacci-sequence
 * http://betterexplained.com/articles/easy-permutations-and-combinations/
 * http://codereview.stackexchange.com/questions/41441/fibonacci-series-topdown-and-bottom-up-approaches-stairway-climbing
 *
 *
 * Interval Diagram:
 * -----------------
 * https://bitbucket.org/ameyapatil/all-images/commits/4e32a863bebdb04c85daf2c6eaaec164381ebf83
 *
 *
 * POINT TO NOTE:
 * ---------------
 * https://www.mathsisfun.com/numbers/fibonacci-sequence.html
 * FOLLOW RULE.
 * fiboArray is the same array represented on the above link.
 * fiboArrayIndex is the index of that array.
 * Thus if
 * fiboArrayIndex = 0, then fiboArray[0] = 0,
 * fiboArrayIndex = 1, then fiboArray[1] = 1,
 * fiboArrayIndex = 2, then fiboArray[2] = 1,
 * fiboArrayIndex = 3, then fiboArray[3] = 2,
 * and so on.
 *
 * To get the nth fibo number is like getting the first item on the array which is at pos = 0.
 * this call fibo (fibonum - 1);
 *
 *
 * DIAGRAM FOR THE STAIRCASE:
 * --------------------------
 * https://bitbucket.org/ameyapatil/all-images/commits/5dac1dcf2d27328fcf4e40252dab59f3084be068
 *
 *
 *

 *
 * Complexity:
 * ------------
 * O(2^n) - time complexity  - without the use of dynamic programming
 *
 * O(n)  - time complexity
 * O(1)  - space complexity
 *
 *
 * BB: 10
 *
 */
public final class FiboStairCount {

    private FiboStairCount() {}

    /**
     * Code: http://www.dreamincode.net/forums/topic/126317-writing-a-for-loop-for-the-fibonnaci-numbers/
     * Returns the nth number in the fibonacci sequence.
     *
     * @param fiboArrayIndex     nth position / index in the fibo series, which starts from 0th position.
     * @return      the nth number in the fibonacci series.
     */
    /*
     * Time complexity: O(n)
     * Aux Space: O(1)
     */
    /*
     * For purposes of interview, we will stick to our convention:
     * when n = 0, then fibo returns 0.
     * when n = 1, thne fibo returns 1.
     */
    public static int fiboBottomUp (int fiboArrayIndex) {
        // fiboArrayIndex = fiboArrayIndex - 1; in the code.

        if (fiboArrayIndex < 0) throw new IllegalArgumentException("The value of n: " + fiboArrayIndex  + " should be positive.");

        if (fiboArrayIndex <= 1) {
            return fiboArrayIndex;
        }

        int a = 0;
        int b = 1;
        int c = 0;
        for (int i = 2; i <= fiboArrayIndex; i++) {
            c = a + b;
            a = b;
            b = c;
        }
        return c;
    }


    /**
     * You are climbing a stair case. Each time you can either make 1 step or 2 steps
     * The staircase has numStairs steps. Returns In how many distinct ways can you climb the staircase.
     *
     * @param numStairs
     * @return
     */
    public static int stairCount(int numStairs) {
        if (numStairs <= 0) throw new IllegalArgumentException("The number of stairs: " + numStairs  + " should be positive.");
        return fiboBottomUp(numStairs + 1); // numStairs + 1 is the same as 'fiboArrayIndex'
    }


    public static void main(String[] args) {

        //        System.out.println(fibo(0) + ":" + fibo(1) + ":" + fibo(2) + ":" + fibo(3) + ":" + fibo(4) + ":" + fibo(5));
        //
        //        System.out.println(fiboTopDown(0) + ":" + fiboTopDown(1) + ":" + fiboTopDown(2) + ":"
        //                + fiboTopDown(3) + ":" + fiboTopDown(4) + ":" + fiboTopDown(5));
        //
        //
        //        System.out.println(fiboTopDownUsingArrayList(0) + ":" + fiboTopDownUsingArrayList(1) + ":" + fiboTopDownUsingArrayList(2) + ":"
        //                + fiboTopDownUsingArrayList(3) + ":" + fiboTopDownUsingArrayList(4) + ":" + fiboTopDownUsingArrayList(5));


        System.out.println(fiboBottomUp(0) + ":" + fiboBottomUp(1) + ":"
            + fiboBottomUp(2) + ":" + fiboBottomUp(3) + ":"
            + fiboBottomUp(4) + ":" + fiboBottomUp(5));

        System.out.println(stairCount(1) + ":"
            + stairCount(2) + ":" + stairCount(3) + ":"
            + stairCount(4) + ":" + stairCount(5));
    }
}
