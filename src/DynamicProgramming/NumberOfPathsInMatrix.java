package DynamicProgramming;

/**
 * https://leetcode.com/problems/unique-paths/
 * https://www.programcreek.com/2014/05/leetcode-unique-paths-java/
 *
 */
public class NumberOfPathsInMatrix {

    // Returns count of possible paths to reach
    // cell at row number m and column number n from
    //  the topmost leftmost cell (cell at 1, 1)
    static int numberOfPaths(int m, int n) {

        int count[][] = new int[m][n];

        for (int i = 0; i < m; i++) {
            count[i][0] = 1;
        }

        for (int j = 0; j < n; j++) {
            count[0][j] = 1;
        }

        for (int i = 1; i < m; i++)  {
            for (int j = 1; j < n; j++) {
                count[i][j] = count[i-1][j] + count[i][j-1];
            }
        }

        return count[m-1][n-1];
    }

    public static void main(String[] args) {

    }
}
