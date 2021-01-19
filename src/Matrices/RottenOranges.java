package Matrices;

import java.util.*;

enum Direction {
    W(-1, 0), N(0, -1), E(0, 1), S(1, 0);

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

/**
 * https://leetcode.com/problems/rotting-oranges/
 */
public class RottenOranges {

    public static int timeToConvert(int[][] m) {

        int count = -1;
        Queue<Integer> queue = new LinkedList<>();

        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[0].length; j++) {
                // put all rotten oranges in queue.
                if (m[i][j] == 2) {
                    queue.add(i * m[0].length + j);
                }
            }
        }

        queue.add(null);

        while (!queue.isEmpty()) {
            Integer node = queue.poll();

            if (node == null) {
                count++;

                if (queue.isEmpty()) {
                    return count;
                } else {
                    queue.add(null);
                }
            } else {

                // rotten oranges.
                for (Direction direction : Direction.values()) {
                    int row = node / m[0].length + direction.getRowDelta();
                    int col = node % m[0].length + direction.getColDelta();

                    // out of boundary.
                    if (row < 0 || col < 0 || row >= m.length || col >= m[0].length) {
                        continue;
                    }

                    // no orange, thus nothing to infect.
                    if (m[row][col] == 0) {
                        continue;
                    }

                    // already this node is a part of queue. so ignore
                    if (m[row][col] == 2) {
                        continue;
                    }

                    // this is the good orange
                    queue.add(row * m[0].length + col);
                    m[row][col] = 2; // this is done to rot the good orange.
                }
            }
        }

        return 1;
    }
}
