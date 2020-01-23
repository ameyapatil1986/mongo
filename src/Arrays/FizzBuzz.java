package Arrays;


/**
 * "Write a program that prints the numbers from 1 to 100. But for multiples of three print �Fizz�
 *  instead of the number and for the multiples of five print �Buzz�.
 *  For numbers which are multiples of both three and five print �FizzBuzz�
 *  If nothing then print the number itself."
 *
 * Reference:
 * http://rosettacode.org/wiki/FizzBuzz#Java
 * http://codereview.stackexchange.com/questions/32627/code-review-request-for-fizz-buzz
 * http://codereview.stackexchange.com/questions/36708/efficient-fizzbuzz/36713#36713
 * http://stackoverflow.com/questions/26680246/which-of-the-two-fizzbuzz-program-is-better-efficient
 *
 * Complexity:
 * O(n)
 *
 *
 * Asked by:
 * NVdia
 *
 * BB: 11
 *
 * @author SERVICE-NOW\ameya.patil
 *
 */
public class FizzBuzz {

    private FizzBuzz() {};

    /**
     * comparisons for a multiple of 3 = 4 (although a number is div by 3, we still check for div by 5 and !divThree && !divFive)
     * comparisons for a multiple of 5 = 4
     * comparisons for a multiple of 15 = 4
     * comparisons for a none = 4
     */
    public static void fizzBuzzBad() {
        // most common thought process
        for (int n = 1; n < 20; n++) {
            boolean divThree = false;
            boolean divFive = false;
            if (n % 3 == 0) {
                System.out.print("fizz");
                divThree = true;
            }

            if (n % 5 == 0) {
                System.out.println("buzz");
                divFive = true;
            }

            if (!divThree && !divFive) {
                System.out.println("n: " + n);
            }
        }
    }

    /**
     * MOST COMMON ANSWER:
     *
     * https://github.com/Keith-S-Thompson/fizzbuzz-polyglot/blob/master/fizzbuzz.cs
     * http://stackoverflow.com/questions/11764539/writing-fizzbuzz/11764613#11764613
     *
     * comparisons for a multiple of 15 = 1 <--- here is the savings.
     * comparisons for a multiple of 3 = 2
     * comparisons for a multiple of 5 = 3
     *
     *
     * This saves the number of comparison for 15's.
     */
    public static void fizzBuzzBest(int max) {
        for (int n = 1; n < max; n++) {
            if (n % 15 == 0) {
                System.out.println("fizzbuzz");
            } else if (n % 3 == 0) {
                System.out.println("fizz");
            } else if (n % 5 == 0) {
                System.out.println("buzz");
            } else {
                System.out.println("\n" + n);
            }
        }
    }

    public static void main(String[] args) {
        //  fizzBuzzBad();
        System.out.println("--------------------------------------------");
        fizzBuzzBest(20);
    }

}
