package Arrays;

import java.util.Stack;

/**
 * https://leetcode.com/problems/verify-preorder-serialization-of-a-binary-tree/
 *
 *                9
 *            3       2
 *         4   1    #  6
 *       # #  # #     # #
 *
 * Input: "9,3,4,#,#,1,#,#,2,#,6,#,#"
 * Output: true
 *
 * Input: "1,#"
 * Output: false
 *
 * Input: "9,#,#,1"
 * Output: false
 *
 */
public class ValidatePreorderSerialization {

    public boolean isValidSerialization(final String preorder) {
        final String[] array = preorder.split(",");
        //The in-degree of root is 0, out-degree is 2. Compansation.
        int count = -1;

        for(int i = 0; i < array.length; i++) {
            count++;
            if (count > 0) return false;
            if (!array[i].equals("#")) count = count-2;
        }

        return count == 0;
    }
}
