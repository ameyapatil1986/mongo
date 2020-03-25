package Matrices;

import java.util.LinkedList;
import java.util.Queue;


/**
 * https://leetcode.com/discuss/interview-question/347457
 *
 * You have a map that marks the location of a treasure island.
 * Some of the map area has jagged rocks and dangerous reefs.
 * Other areas are safe to sail in. There are other explorers trying to find the treasure.
 * So you must figure out a shortest route to the treasure island.
 *
 * Assume the map area is a two dimensional grid, represented by a matrix of characters.
 * You must start from the top-left corner of the map and can move one block up, down, left or right at a time.
 * The treasure island is marked as X in a block of the matrix. X will not be at the top-left corner.
 * Any block with dangerous rocks or reefs will be marked as D. You must not enter dangerous blocks.
 * You cannot leave the map area. Other areas O are safe to sail in. The top-left corner is always safe.
 * Output the minimum number of steps to get to the treasure.
 */
public class TreasureIslands {

    enum Direction {
        N(-1, 0), W(0, -1), S(0, 1), E(1, 0);

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

        queue.add(new Node(0, 0));
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
                    if (m[row][col] == 'D') {
                        continue;
                    }

                    if (m[row][col] == 'X') {
                        return count + 1;
                    }

                    m[row][col] = 'D';
                    queue.add(new Node(row, col));
                }

            }
        }

        return -1;
    }

    public static void main(String[] args) {
        char[][] grid =   {{'O', 'O', 'O', 'O'},
                           {'D', 'O', 'D', 'O'},
                           {'O', 'O', 'O', 'O'},
                           {'X', 'D', 'D', 'O'}};

        System.out.println(distanceTotTreasureIsland(grid));

    }
}

