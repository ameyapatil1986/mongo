package BasicMath;


/**
 * FB:
 * https://www.careercup.com/question?id=4635417542393856
 *
 *
 * References:
 * http://stackoverflow.com/questions/1623375/writing-your-own-square-root-function
 * http://stackoverflow.com/questions/15393573/square-root-with-babylonian-or-herons-method
 * http://www.careercup.com/question?id=4419686
 * http://stackoverflow.com/questions/322749/retain-precision-with-doubles-in-java
 *
 * TBR:Complexity:
 * http://stackoverflow.com/questions/28815339/time-complexity-of-math-sqrt-java
 * http://stackoverflow.com/questions/28815385/time-complexity-of-babylon-herons-method-to-find-square-root
 * http://stackoverflow.com/questions/28815339/time-complexity-of-math-sqrt-java
 * http://stackoverflow.com/questions/12309432/time-complexity-for-babylonian-method
 * Since complexity is too  complicated, lets stick to the answer:
 * O(log2 x) <-- i mean log x to the base 2.
 *
 * DIAGRAM:
 * https://bitbucket.org/ameyapatil/all-images/commits/f51b04529c4bf5bbb200ecedd22a2e0486167342
 * .
 * BB:
 * 5
 *
 */
public final class SquareRoot {

    private SquareRoot() { }

   /*
    * http://stackoverflow.com/questions/15393573/square-root-with-babylonian-or-herons-method = answer by JONI
    * http://stackoverflow.com/questions/19026761/mathematics-behind-babylonian-square-root-method
    */

    // sample example: 100.
    // https://bitbucket.org/ameyapatil/all-images/commits/f51b04529c4bf5bbb200ecedd22a2e0486167342
    public static double heronsOrBabyloniansSQRT(double x) {
        double a = 0, b = x, e = 0.001; // e is the chosen precision.will result in answer of root in 3 digits 3.142

        // a != b condition may cause a very long for loop. note: b will always remain greater than A.
        while ((b - a) > e) {
            System.out.println("low: " + a  + " high " + b);
            b = (a + b) / 2;   // find the mid between low and high. slowly slowly keep brigding the gap.
            a = x / b;         //   low * high = x, low incre
        }

        return a;
    }

    public static void main(String args[]) {
        System.out.println(heronsOrBabyloniansSQRT(4));
        System.out.println("\n----------------------------------");
        System.out.println(heronsOrBabyloniansSQRT(8));
        System.out.println("\n----------------------------------");
        System.out.println(heronsOrBabyloniansSQRT(9));
        System.out.println("\n----------------------------------");

        /**
         * float x = (float) (22.0/7.0);
         * System.out.println(x);
         * 3.142857
         */
    }
}
