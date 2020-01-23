
/**
 *
 * test boundary:
 * Z  - 26
 * AA - 27
 * AZ - 52
 * BA - 53
 *
 */


/**
 * References:
 * http://leetcode.com/2010/10/amazon-bar-raiser-interview-question.html
 * http://stackoverflow.com/questions/763691/programming-riddle-how-might-you-translate-an-excel-column-name-to-a-number
 * http://www.glassdoor.com/Interview/Come-out-with-an-algorithm-for-getting-the-column-number-provided-the-column-name-in-a-excel-sheet-and-vice-versa-Excel-ha-QTN_996.htm
 * http://www.devx.com/tips/Tip/31064
 * http://stackoverflow.com/questions/1269899/convert-number-to-corresponding-excel-column
 * http://codereview.stackexchange.com/questions/44545/excel-column-string-to-row-number-and-vice-versa
 *
 * Notes:
 * -----
 * This is not the skipping of 0's from number series as previously thought
 * http://math.stackexchange.com/questions/713731/whats-the-function-to-translate-between-two-number-series-with-and-without-zero
 *
 * Instead this is something like treating 0 as an entity:
 * series1/columnAlphabets      series2/columnValue
 *          0                       1
 *          1                       2
 *          2                       3
 *          :                       :
 *          :                       :
 *          9                      10
 *         00 (00 is not simply 0) 11
 *         01                      12
 *         02                      13
 *          :                       :
 *          :                       :
 *         09                      19
 *         10                      21
 *         11                      22
 *         12                      23
 *          :                       :
 *         19                      30
 *         20                      31
 *
 * Now think as if 0 was A, and 1 was B and 25 was Z
 * this means
 * A  ->  1
 * B  ->  2
 * Z  -> 26
 * AA -> 27
 *
 *
 * Basically algorithm works something as follows:
 * diagram:
 * https://bitbucket.org/ameyapatil/all-images/commits/7b2694f30c25385ebc237fec31716b76ccd25356
 *
 * - getExcelColumnNumber();
 * do exactly what is being done to convert to decimal, but "after" the value at that decimal position is computed "add it to 1"
 *
 * - getExcelColumnName()
 * do exactly what you do when converting into hexa(26 base whatever) but just "before" division "subtract by 1"
 * to get the true value.
 *
 * example:
 * Consider a highly simplistic "AB" in the world of base 26.
 * Normal workflow:
 * => A * 26  + B * 1
 * => 0 * 26  + 1 * 1
 * => -1
 *
 * In our padded workflow:
 * =>  A * 26       +    B * 1
 * => (0 + 26) + 1  +    (1 * 1) + 1
 * =>  1            +    2
 * => 12
 *
 * Decoding in our padded workflow:
 * get the true value of the last deciemal
 * ie true value of last decimal in padded 12 is 1 ie B and not 2 ie C.
 * Thus just before the mod division, we subtract by 1.
 * ie
 * (12 - 1)  % 26 => 1 thus obtained B :)
 * now
 * 12 % 26 => 1
 * and we subtract this 1.
 * Thus
 * (1 - 1) % 26 => 0 thus obtained A :)
 *
 *
 * BB:
 * 5
 *
 * Complexity:
 * O(log n) to the base its "base value" ie hexa or decimal etc
 *
 * @author SERVICE-NOW\ameya.patil
 */
public final class Excel {

    private Excel() {};

    //    public static int getExcelColumnNumber(String descriptor) {
    //        char[] chars = descriptor.toLowerCase().toCharArray();
    //        int result = 0;
    //        for (char c : chars) {
    //            result *= 26;
    //            result += c - 'a' + 1;
    //        }
    //        return result;
    //    }

    /**
     * http://stackoverflow.com/questions/196830/what-is-the-easiest-best-most-correct-way-to-iterate-through-the-characters-of-a
     *
     * @param column
     * @return
     *
     * ( This code is more efficient than reversing the string. )
     */
    public static int getExcelColumnNumber(String column) {
        int base = 1;
        int result = 0;

        for (int i = column.length() - 1; i >= 0;i--) {
            result = result +  (column.charAt(i) - 'A' + 1) * base;
            base *= 26;
        }
        return result;
    }

    /**
     * http://stackoverflow.com/questions/196830/what-is-the-easiest-best-most-correct-way-to-iterate-through-the-characters-of-a
     *
     * @param number
     * @return
     */
    public static String getExcelColumnName(int number) {
        if (number < 1) {
            throw new IllegalArgumentException("Negative numbers and 0 not allowed.");
        }

        final StringBuilder sb = new StringBuilder();

        int num = number - 1;
        while (num >=  0) { // MOST IMPORTANT THING IS: num >= 0.
            int numChar = (num % 26)  + 'A';
            sb.append((char)numChar);
            num = (num  / 26) - 1;
        }
        return sb.reverse().toString();
    }
}

