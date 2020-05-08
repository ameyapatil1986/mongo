package Backtrack;

/**
 * http://buttercola.blogspot.com/2016/06/leetcode-329-longest-increasing-path-in.html
 */
public class LongestIncreasingPathMatrix {

    private static enum Direction {
        NORTH(0, -1), EAST(1, 0), SOUTH(0, 1), WEST(-1, 0);

        int dx;
        int dy;

        private Direction(int dx, int dy) {
            this.dx = dx;
            this.dy = dy;
        }

        public int getX() {
            return dx;
        }

        public int getY() {
            return dy;
        }
    }

    public int longestIncreasingPath(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return 0;
        }

        int m = matrix.length;
        int n = matrix[0].length;

        int max = 0;
        int[][] dp = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                max = Math.max(max, helper(i, j, matrix, dp, Integer.MIN_VALUE));
            }
        }

        return max;
    }

    private int helper(int row, int col, int[][] matrix, int[][] dp, int prev) {
        if (row < 0 || row >= matrix.length || col < 0 || col >= matrix[0].length) {
            return 0;
        }

        if (prev > matrix[row][col]) {
            return 0;
        }

        // remember we are counting backwards.
        if (dp[row][col] > 0) {
            return dp[row][col];
        }

        int curMax = 0;

        for (Direction direction : Direction.values()) {
            int x = direction.dx + row;
            int y = direction.dy + col;

            curMax = Math.max(curMax, helper(x, y, matrix, dp, matrix[row][col]));
        }

        dp[row][col] = curMax + 1;

        return dp[row][col] + 1;
    }
}
