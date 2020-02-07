package Strings.zeroAndOne;

/**
 *
 * https://leetcode.com/problems/count-binary-substrings/
 * https://www.youtube.com/watch?v=4nVUaNGgfl0
 *
 * Give a string s, count the number of non-empty (contiguous) substrings that have the same number of 0's and 1's,
 *
 * "and all the 0's and all the 1's in these substrings are grouped consecutively."
 *
 * Substrings that occur multiple times are counted the number of times they occur.
 * Input: "00110011"
 * Output: 6
 * Explanation: There are 6 substrings that have equal number of
 * consecutive 1's and 0's: "0011", "01", "1100", "10", "0011", and "01".
 *
 * Notice that some of these substrings repeat and are counted the number of times they occur.
 *
 * Also, "00110011" is not a valid substring because all the 0's (and 1's) are not grouped together.
 *
 *
 * Input: "00110"
 * Output: "01", "0011", "0110", "10"
 *
 */
public class CountEqualsZerosAndOnes {

    public int countBinarySubstrings(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        int prev = 0;
        int curr = 1;

        int ans = 0;

        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == s.charAt(i - 1)) {
                curr++;
            } else {
                ans += Math.min(prev, curr);
                prev = curr;
                curr = 1;
            }
        }

        ans += Math.min(prev, curr);

        return ans;
    }

}
