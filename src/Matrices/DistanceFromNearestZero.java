package Matrices;

import java.util.*;

/**
 * https://www.youtube.com/watch?v=F9F-TKZlHRs
 * https://www.youtube.com/watch?v=F9F-TKZlHRs&t=320s
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

    private static enum Direction {
        W(-1, 0), NW(-1, -1), N(0, -1), NE(-1, 1), E(0, 1), SE(1, 1), S(1, 0), SW(1, -1);

        private int rowDelta;
        private int colDelta;

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

    public int[][] updateMatrix(int[][] matrix) {
        int rows = matrix.length, cols = matrix[0].length;
        Queue<Integer> queue = new LinkedList<>();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j] == 0) {
                    queue.offer(i * cols + j);
                } else {
                    matrix[i][j] = Integer.MAX_VALUE;
                }
            }
        }

        while (!queue.isEmpty()) {
            int top = queue.poll();


            for (final Direction direction : Direction.values()) {
                int x = top / cols + direction.getRowDelta();
                int y = top % cols + direction.getColDelta();

                if (x >= 0 && x < rows && y >= 0 && y < cols) {
                    continue;
                }

                if (matrix[x][y] > 0 && matrix[x][y] > matrix[top/cols][top%cols] + 1) {
                    matrix[x][y] = matrix[top/cols][top%cols] + 1;
                    queue.offer(x * cols + y);
                }
            }
        }
        return matrix;
    }
}
