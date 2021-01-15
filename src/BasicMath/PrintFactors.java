package BasicMath;

/**
 *
 *  12 / 3 = 4,
 *  12 - dividend.
 *  3 - divisor
 *  4 - quotient
 *  0 - remain
 *
 *
 Write a program in Java that takes a positive integer and prints out all ways to multiply smaller integers
 that equal the original number, without repeating sets of factors.  In other words, if your output contains 4 * 3,
 you should not print out 3 * 4 again as that would be a repeating set.  Note that this is not asking for prime factorization only.

 Here is the sample from one solution.  Your answer may differ in the order of output and that would be okay,
 but your answer should provide the same sets of factors to be correct. The order of factors in my solution is a
 hint on how one might implement a solution efficiently, but there are multiple ways to solve the problem.
 Note also that the problem does not require you to store the possible factors in a data structure,
 but simply to print them out.

 Example:

 $ java -cp . PrintFactors 32
 32 * 1
 16 * 2
 8 * 4
 8 * 2 * 2
 4 * 4 * 2
 4 * 2 * 2 * 2
 2 * 2 * 2 * 2 * 2

 References:
 https://compilr.com/swethav/factors/Project.java
 http://ideone.com/fork/fP9oqs

 Diagram:
 https://bitbucket.org/ameyapatil/all-images/commits/ddf2019185b07328eefaeba5bbc227507400d21c

 Complexity:
 O(n!)
 http://codereview.stackexchange.com/questions/42939/prints-out-all-ways-to-multiply-smaller-integers-that-equal-the-original-number

 BB: 13
 */

public final class PrintFactors {

    private PrintFactors() {}

    public static void printFactors(int number) {
        if (number <= 0) throw new IllegalArgumentException("The number should be greater than 0.");
        printFactorsListX(number, number, "");
    }

    /*
    * https://bitbucket.org/ameyapatil/all-images/commits/ddf2019185b07328eefaeba5bbc227507400d21c
    */
    public static void printFactorsListX(int number, int seed, String factorString) {
        if (number <= 2) {
            return;
        }
        /*
         * This function contains factorString as an argument to facilitate the recursive call for subsequent
         * factors until it reaches prime values. For example, let's say input number = 32 and when i = 8 it prints
         * 8*(32/8) ==> 8 * 4 but the subsequent reduction of 4 is needed and this is done by recursively passing in 4
         * as number. But we also want to maintain the chain "8 * ". So this makes the carry over string as an input
         * argument for the helper function printFactorsList
         */
        for (int dividedBy = seed; dividedBy >= 2; dividedBy--) {
            if (number % dividedBy != 0)
                continue;

            int quotient = number / dividedBy;

            /*
             * 32*1 16*2 8*4 8*2*2 4*4*2 4*2*2*2 2*2*2*2*2
             *
             * Note: as we go right, the values keeps descending.
             */
            if (dividedBy >= quotient) {
                /*
                 * 4 * 8, wont get printed
                 */
                System.out.println(factorString + dividedBy + "*" + quotient);
            }
            /*
             *  4 * 8, will get called and eventually in the next recursion get printed as 4 * 4 * 2.
             */
            printFactorsListX(quotient, Math.min(dividedBy, quotient - 1), factorString + dividedBy + "*");
        }
    }


    /*
     * practice the use-case of 32.
     */
    public static void main(String[] args) {
        printFactors(32);
    }
}
