package Backtrack;

/**
 * Created by ameya.patil on 10/8/18.
 */
public class CountIslands {


    private CountIslands() {}

    private static enum Direction {
        NW(-1, -1), N(0, -1), NE(-1, 1), E(0, 1), SE(1, 1), S(1, 0), SW(1, -1), W(-1, 0);

        int rowDelta;
        int colDelta;

        Direction(int rowDelta, int colDelta) {
            this.rowDelta = rowDelta;
            this.colDelta = colDelta;
        }

        public int getRowDelta() {
            return rowDelta;
        }

        public int getColDelta() {
            return colDelta;
        }
    }

    private static void dfs(int row, int col, int[][] m, boolean[][] visited) {
        if (row < 0 || row == m.length || col < 0 || col == m[0].length) return; // initial check

        if (m[row][col] == 0) return; // initial check

        if (visited[row][col]) return; // visited = true check

        visited[row][col] = true; // mark visited.

        /**
         *  https://www.careercup.com/question?id=5998719358992384
         *  int sum = 0;
         */
        for (Direction direction : Direction.values()) {
            // sum = sum + dfs();
            dfs(row + direction.getRowDelta(), col + direction.getColDelta(), m, visited);
        }
        // return sum + 1;
    }

    /**
     * Returns the number of 1 islands.
     *
     * @param m     the input matrix
     * @return      the number of 1 islands.
     */
    public static int count(int[][] m) {
        final boolean[][] visited = new boolean[m.length][m[0].length]; // visited matrix.
        int count = 0;

        for (int row = 0; row < m.length; row++) {
            for (int col = 0; col < m[0].length; col++) {
                if (m[row][col] == 1 && !visited[row][col]) {
                    dfs(row, col, m, visited);
                    count++;
                }
            }
        }
        return count;
    }
}
