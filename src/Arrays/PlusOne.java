package Arrays;

/**
 * https://www.programcreek.com/2014/05/leetcode-plus-one-java/
 */
public class PlusOne {

    public int[] plusOne(int[] digits) {
        if (digits == null || digits.length == 0)
            return new int[0];

        for (int i = digits.length - 1; i >= 0; i--) {
            int sum = digits[i] + 1;
            digits[i] = sum % 10;
            if (sum < 10) {
                return digits;
            }
        }

        int[] result = new int[digits.length + 1];
        System.arraycopy(digits, 0, result, 1, digits.length);
        result[0] = 1;
        return result;
    }
}
