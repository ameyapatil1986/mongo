package DynamicProgramming;

/**
 *https://massivealgorithms.blogspot.com/2017/04/leetcode-542-01-matrix.html
 * https://leetcode.com/problems/01-matrix/
 * https://www.geeksforgeeks.org/nearest-1-0-binary-matrix/
 * https://gist.githubusercontent.com/lenchen1112/d06747dbcb38425470ae2113686f3b8a/raw/b006601755d848915c7f38816ad0ce0295d196b9/542_01_matrix_dynamic_programming.py
 *
 * This is leet code.
 * Find distance from nearest zero.
 *
 * Input
 * =====
 *
 * | 0 0 0 |
 * | 0 1 0 |
 * | 1 1 1 |
 *
 * Output
 * =======
 * | 0 0 0 |
 * | 0 1 0 |
 * | 1 2 1 |
 *
 */
public class DistanceFromNearestZero {

    public int[][] updateMatrixX(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0)
            return matrix;

        int M = matrix.length, N = matrix[0].length;

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (matrix[i][j] == 1) {
                    int val = Integer.MAX_VALUE;

                    if (i > 0)
                        val = Math.min(val, matrix[i - 1][j] + 1);
                    if (j > 0)
                        val = Math.min(val, matrix[i][j - 1] + 1);

                    matrix[i][j] = val;
                }
            }
        }

        for (int i = M - 1; i >= 0; i--) {
            for (int j = N - 1; j >= 0; j--) {
                if (matrix[i][j] == 1) {

                    int val = matrix[i][j];

                    if (i < M - 1)
                        val = Math.min(val, matrix[i + 1][j] + 1);

                    if (j < N - 1)
                        val = Math.min(val, matrix[i][j + 1] + 1);

                    matrix[i][j] = val;
                }
            }
        }
        return matrix;
    }
}
