package BitManip;

/**
 * http://buttercola.blogspot.com/2015/08/leetcode-reverse-bits.html
 */
public class ReverseBits {

    public int reverseBits(int n) {
        int result = 0;
        for (int i = 0; i < 32; i++) {
            // basically doing result * 2. this is similar to palindrome, where you go backwards but logic is same like converting string to a number going forward.
            result = (result << 1) | (n & 1);
            n = (n >> 1);
        }

        return result;
    }
}
