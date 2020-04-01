package Matrices;


/**
 * https://leetcode.com/problems/game-of-life/discuss/73223/Easiest-JAVA-solution-with-explanation
 * https://www.programcreek.com/2014/05/leetcode-game-of-life-java/
 */
public class GameOfLife {

    private int die = 2;
    private int live = 3;

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

    public void gameOfLife(int[][] board) {
        // we only flip the 1 to die and 0 to live
        // so when we find a die around, it must be a previous 1
        // then we can count the 1s without being affected
        int rows = board.length;
        int cols = board[0].length;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {

                int around = countLive(i,j,board);

                // Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
                if (board[i][j] == 0) {
                    if (around == 3) {
                        board[i][j] = live;
                    }
                } else if (board[i][j] == 1) {
                    // Any live cell` with two or three live neighbors lives on to the next generation.
                    if (around == 2 || around ==3)
                        continue;

                    // Any live cell with more than three live neighbors dies, as if by over-population.
                    if (around < 2 || around > 3)
                        board[i][j] = die;
                }
            }
        }

        for (int i=0; i<rows; i++){
            for (int j=0; j<cols; j++){
                if (board[i][j] == die)
                    board[i][j] = 0;
                if (board[i][j] == live)
                    board[i][j] = 1;
            }
        }
    }

    private int countLive(int i, int j, int[][] board) {
        int count = 0;

        for (Direction dir : Direction.values()) {
            int x = i + dir.getRowDelta();
            int y = j + dir.getColDelta();

            if (x >= 0 && y >= 0 && x < board.length && y < board[0].length ){
                if (board[x][y] == 1 || board[x][y] == die)
                    count ++;
            }
        }

        return count;
    }
}
