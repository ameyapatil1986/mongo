package BasicMath;

/**
 * 50. Pow(x, n)
 * https://leetcode.com/problems/powx-n/
 */


/**
 * Contains a simple function which helps us find power of a number.
 *
 * Complexity: O (log (base 2) power)
 *
 * BB: 11
 *
 * Diagram:
 * https://bitbucket.org/ameyapatil/all-images/commits/dc34144ffd37c7429a2842544854293fd4538928
 *
 * Reference:
 * http://codereview.stackexchange.com/questions/39190/find-power-of-a-number
 *
 * @author SERVICE-NOW\ameya.patil
 *
 */
public final class Power {

    private Power() { }

    /**
     * Finds the power of the input number.
     * @param x		the number whose power needs to be found
     * @param pow   the power
     * @return      the value of the number raised to the power.
     */
    public static double pow(double x, int pow) {
        if (x == 0) return 0; // we assume that 0^0 is 0. 0^anything is 0.
        if (pow == 0) return 1;
        return pow > 0 ? getValueForPositivePower(x, pow) : 1 / getValueForPositivePower(x,  -pow);
    }

    /*
     * This code is O(log power).
     *
     * Logic.
     * This code converts a decimal to binary. As simply.
     * Now:
     * Binary for 7 is  111
     * Binary for 8 is 1000
     *
     * Assume input number is 2, and power is 7 the code calculates as follows.
     * 2^1 * 2^2 * 2^4 => 2^(1 + 2 + 4) => 2^(11)
     *
     * Assume the input number is 2, and the power is 5, the code calculates the code as follows.
     * 2^(8)
     *
     * @param x			x is the value
     * @param pow		pow is the power
     * @return			x raised to power.
     */
    private static double getValueForPositivePower(double x, int pow) {
        double result = 1;
        while (pow > 0) {
            if (pow % 2 == 1) {
		        /*
		         * Lets consider case- 2^7
		         * ------------------------
		         *
		         * firstime:    result =  2^1, at start of while loop pow = 7
		         *
		         * second time: result = 2^1 * 2^2 = 2^3, at start of while loop pow = 3
		         *
		         * third time:  result = 2^1 * 2^2 * 2^4 = 2^7, at start of while loop pow = 1
		         *
		         */
                result *= x;
            }
            pow /= 2;
            if (pow > 0) {
                x *= x;

    		    /*
    		     * Lets consider case- 2^8
    		     *
    		     * first  time: result = 1, but x = 2^2, and pow = 4
    		     * second time: result = 1, but x = 2^2 * 2^2 = 2^4, and pow = 2
    		     * third time:  result = 1, but x = 2^4 * 2^4 = 2^8 and pow = 1.
    		     *
    		     */

            }
        }
        return result;
    }
}
