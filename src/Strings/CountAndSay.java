package Strings;

/**
 * https://leetcode.com/problems/count-and-say/
 * https://www.youtube.com/watch?v=usAZEcL_y-0
 * https://medium.com/@speedforcerun/leetcode-easy-38-count-and-say-303e0bb8e51
 *
 *      1
 *      1 1
 *      2 1
 *      1 2 1 1
 *
 *  The overall time complexity is O(ln).
 *  l - length of string.
 *  n - number of strings.
 */
public class CountAndSay {

    public String countAndSay(int n) {
        if (n <= 0)
            return null;

        String result = "1";
        int i = 1;

        while (i < n) {
            StringBuilder sb = new StringBuilder();
            int count = 1;

            for (int j = 1; j < result.length(); j++) {
                if (result.charAt(j) == result.charAt(j - 1)) {
                    count++;
                } else {
                    sb.append(count);
                    sb.append(result.charAt(j - 1));
                    count = 1;
                }
            }

            sb.append(count);
            sb.append(result.charAt(result.length() - 1));

            result = sb.toString();
            i++;
        }

        return result;
    }
}
