package BitManip;

/**
 * https://leetcode.com/problems/reverse-bits/
 */
public class ReverseBitsOfInteger {


    public int reverseBitsOfInteger(int x) {

        int b = 0;

        while (x != 0) {
            b <<= 1;
            b |= (x & 1);
            x >>= 1;
        }

        return 0;
    }

}
