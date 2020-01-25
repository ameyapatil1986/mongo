package BasicMath;

/**
 * https://github.com/Blankj/awesome-java-leetcode
 * https://github.com/Blankj/awesome-java-leetcode/blob/master/note/007/README.md
 *
 */
public class ReverseInteger {


//     here we go left to right.
//     while (int idx = 0; Character.isDigit(s.charAt(idx)); idx++) {
//        num = num * 10 + (s.charAt(idx) - '0');
//        idx++;
//    }


    public int reverseInt(int x) {
        // check for negative number

        // here we go right to left, thus number reverses
        double res = 0;
        for (; x!= 0; x /= 10)
            res = res * 10 + x % 10;

        return res > Integer.MAX_VALUE || res < Integer.MIN_VALUE ? 0 : (int) res;
    }
}
