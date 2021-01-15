package DynamicProgramming2;


public class LongestCommonSubsequence {

    /**
     * @param str1
     * @param str2
     * @return
     */
    public static int bottomUpOrTabularApproach(char str1[], char str2[]) {
        int LCS[][] = new int[str1.length + 1][str2.length + 1];

        for (int i = 1; i < LCS.length; i++) {
            for (int j = 1; j < LCS[0].length;j++) {
                if (str1[i - 1] == str2[j - 1]) {
                    // this means interval is detected.
                    LCS[i][j] = LCS[i - 1][j - 1] + 1;
                } else {
                    // similar to finding maximum element in an array.
                    // LCS[i - 1][j] is actually a value of another interval.
                    // LCS[i][j - 1] is simply the previous highest value.
                    LCS[i][j] = Math.max(LCS[i - 1][j], LCS[i][j - 1]);
                }
            }
        }

        return LCS[str1.length][str2.length];
    }
}
