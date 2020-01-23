//package zBasicMath;
//
///**
// * Find the smallest positive number whose individual digits adds to the input number.
// * eg: consider the input 14,
// * The smallest number whose digit add to the given number is 59 ie 5 + 9 = 14.
// *
// * Complexity: O(n), where n is the input number
// *
// * References:
// * http://codereview.stackexchange.com/questions/38014/find-the-smallest-number-whose-sum-of-digits-add-to-a-number
// *
// * BB: 7
// *
// * @author SERVICE-NOW\ameya.patil
// */
//public class SmallestNumber {
//
//    private SmallestNumber() {}
//
//    private static int getIntNum(int x, int y) {
//        String number = new StringBuilder().append(x).append(y).toString();
//        return Integer.parseInt(number);
//    }
//
//
//    /**
//     * Check rolf's answer:
//     * Complexity: O(log9 n)
//     *
//     * Rough explanation:
//     *       1
//     *   ------
//     * 9 | 14
//     * --
//     *     -9
//     *   -----
//     *      5
//     *   -----
//     *
//     * Remainder(5) +  Divosor (9) => 14
//     *
//     * @param val
//     * @return
//     */
//    public static int getVal(int val) {
//        val = Math.abs(val);
//        int ninecount =  val / 9;
//        int remainder = val % 9;
//        int scale = (int)Math.pow(10, ninecount);
//        int result = remainder * scale + (scale - 1);
//        return result;
//    }
//
//    /**
//     * http://stackoverflow.com/questions/2674707/how-to-concatenate-int-values-
//     * in-java
//     *
//     * @param args
//     */
//    public static void main(String[] args) {
//        int x1 = getVal(14);
//        System.out.println("Expected 59, Actual: " + x1);
//
//        int x2 = getVal(16);
//        System.out.println("Expected 79, Actual: " + x2);
//    }
//}
