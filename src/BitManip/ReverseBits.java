package BitManip;

/**
 * http://buttercola.blogspot.com/2015/08/leetcode-reverse-bits.html
 */
public class ReverseBits {

    public int reverseBits(int n) {

        int result = 0;
        for (int i = 0; i < 32; i++) {
            result = (result << 1) | (n & 1);
            n = (n >> 1);
        }

        return result;
    }

}
