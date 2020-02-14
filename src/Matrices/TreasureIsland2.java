package Matrices;

import java.util.LinkedList;
import java.util.Queue;


/**
 * https://leetcode.com/discuss/interview-question/356150
 */
public class TreasureIsland2 {

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


    private static class Node {
        int i;
        int j;

        Node(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }

    public static int distanceTotTreasureIsland(char[][] m) {

        int count = 1;
        Queue<Node> queue = new LinkedList<>();

        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[0].length; j++) {
                if (m[i][j] == 'S') {
                    queue.add(new Node(i, j));
                }
            }
        }
        queue.add(null);

        while (!queue.isEmpty()) {
            Node node = queue.poll();

            if (node == null) {
                count++;
                queue.add(null);
            } else {
                // amazon code.
                for (Direction direction : Direction.values()) {
                    int row = node.i + direction.getRowDelta();
                    int col = node.j + direction.getColDelta();

                    // out of boundary.
                    if (row < 0 || col < 0 || row >= m.length || col >= m[0].length) {
                        continue;
                    }

                    // already this node is a part of queue. so ignore
                    if (m[row][col] == 'D' || m[row][col] == 'S') {
                        continue;
                    }

                    if (m[row][col] == 'X') {
                        return count;
                    }

                    m[row][col] = 'D';
                    queue.add(new Node(row, col));
                }

            }
        }

        return -1;
    }

    /**
     * [['S', 'O', 'O', 'S', 'S'],
     *  ['D', 'O', 'D', 'O', 'D'],
     *  ['O', 'O', 'O', 'O', 'X'],
     *  ['X', 'D', 'D', 'O', 'O'],
     *  ['X', 'D', 'D', 'D', 'O']]
     */
    public static void main(String[] args) {
        char[][] grid =   { {'S', 'O', 'O', 'S', 'S'},
                            {'D', 'O', 'D', 'O', 'D'},
                            {'O', 'O', 'O', 'O', 'X'},
                            {'X', 'D', 'D', 'O', 'O'},
                            {'X', 'D', 'D', 'D', 'O'}};

        System.out.println(distanceTotTreasureIsland(grid));

    }
}
