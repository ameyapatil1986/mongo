package Random;


/**
 * asked at Cisco.
 *
 * Diagrams:
 * https://bitbucket.org/ameyapatil/pointstonote/commits/be8f2d4877bc8f7f47630cb1ab6cee8e2fc8b018
 *
 * References:
 * http://codereview.stackexchange.com/questions/32109/implement-strings-valueof-function-code-review-request
 *
 *
 * Comlexity:
 * O (log n to the base 10)
 *
 * BB: 1
 */
public final class StringValueOf {

    private StringValueOf () {}

    // note that int max value is 10 digits
    final static int [] sizeTable = { 9, 99, 999, 9999, 99999, 999999, 9999999,
        99999999, 999999999, Integer.MAX_VALUE };

    private final static char[] DigitOne = {
        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
    };


    private final static char[] DigitTens = {
        '0', '0', '0', '0', '0', '0', '0', '0', '0', '0',
        '1', '1', '1', '1', '1', '1', '1', '1', '1', '1',
        '2', '2', '2', '2', '2', '2', '2', '2', '2', '2',
        '3', '3', '3', '3', '3', '3', '3', '3', '3', '3',
        '4', '4', '4', '4', '4', '4', '4', '4', '4', '4',
        '5', '5', '5', '5', '5', '5', '5', '5', '5', '5',
        '6', '6', '6', '6', '6', '6', '6', '6', '6', '6',
        '7', '7', '7', '7', '7', '7', '7', '7', '7', '7',
        '8', '8', '8', '8', '8', '8', '8', '8', '8', '8',
        '9', '9', '9', '9', '9', '9', '9', '9', '9', '9',
    };


    private static int stringSize(int x) {
        int  i = 0;
        // simple case of 10 and x=9.
        for (i = 0; sizeTable[i] < x; i++);
        return i + 1; // one is incremented size, arr index is size - 1, and i is the index, and we want to return size.
    }


    private static void getChars (char[] buf, int i) {
        int charPos = buf.length - 1;

        i = Math.abs(i);

        // take example of 109 and 99.
        for (;i >= 10; i = i / 100) {
            int r = i % 100;

            buf[charPos--]  = DigitOne[r];
            buf[charPos--]  = DigitTens[r];
        }

        if (i > 0) {
            buf[charPos--] = DigitOne[i];
        }

        if (charPos == 0) {
            buf[charPos] = '-';
        }
    }


    /**
     * Algo:
     * -----
     * -ve value of int max cannot be reproduced into +ve so just hardcode the negative value.
     * get digits number of digits
     * do the division by 100.
     *
     * @param i
     * @return
     */
    public static String valueOf(int i) {
        if (i == Integer.MIN_VALUE) {
            return "-2147483648";
        }
        int size = (i < 0) ? stringSize(-i ) + 1 : stringSize(i);

        char[] charBuff = new char[size];
        getChars(charBuff, i);

        /**
         * There are 2 ways to convert a char into string.
         * 1. buf.toString()
         * 2. String(buf)
         *
         * but we should use String(buf) because:
         * 1. Mostly buf.toString would internally call String(buf)
         * 2. Integer class uses new String.
         */
        return new String(charBuff);
    }

    public static void main(String[] args) {
        System.out.println(valueOf(101));
        System.out.println(valueOf(-2010));
        System.out.println(Integer.MAX_VALUE);
        System.out.println(Integer.MAX_VALUE + 1);
        System.out.println(Integer.MAX_VALUE + 2);
    }
}

