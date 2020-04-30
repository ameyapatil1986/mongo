package Backtrack;

/**
 * http://buttercola.blogspot.com/2015/09/leetcode-walls-and-gates.html
 *
 * You are given a m x n 2D grid initialized with these three possible values.
 * -1 - A wall or an obstacle.
 * 0 - A gate.
 * INF - Infinity means an empty room. We use the value 231 - 1 = 2147483647 to represent INF
 * as you may assume that the distance to a gate is less than2147483647.
 * Fill each empty room with the distance to its nearest gate.
 *
 * If it is impossible to reach a gate, it should be filled with INF.
 *
 */
public class WallsAndGates {

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

    public void wallsAndGates(int[][] rooms) {
        if (rooms == null || rooms.length == 0) {
            return;
        }

        int m = rooms.length;
        int n = rooms[0].length;

        boolean[][] visited = new boolean[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (rooms[i][j] == 0) {
                    wallsAndGatesHelper(i, j, 0, visited, rooms);
                }
            }
        }
    }

    private void wallsAndGatesHelper(int row, int col, int distance, boolean[][] visited, int[][] rooms) {
        if (row < 0 || row >= rooms.length || col < 0 || col >= rooms[0].length) {
            return;
        }

        /**
         * if ( false condition || already visited ) return
         * set stuff  + set visisted = true
         */

        if (visited[row][col]) {
            return;
        }

        // false condition  + already visited.
        if (rooms[row][col] == -1) {
            return;
        }

        if (distance > rooms[row] [col]) {
            return;
        }

        // Set condition + Mark as visited
        rooms[row][col] = distance;
        visited[row][col] = true;


        for (Direction dir : Direction.values()) {
            int newRow = row + dir.getY();
            int newCol = col + dir.getX();

            wallsAndGatesHelper(newRow, newCol, distance + 1, visited, rooms);
        }

        // Mark as unvisited
        visited[row][col] = false;
    }
}
