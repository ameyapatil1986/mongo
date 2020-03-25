package Strings;

/**
 * https://leetcode.com/problems/count-and-say/
 * https://www.youtube.com/watch?v=usAZEcL_y-0
 *
 *      1
 *      1 1
 *      2 1
 *      1 2 1 1
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
