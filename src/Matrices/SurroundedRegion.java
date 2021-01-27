package Matrices;

/**
 * https://leetcode.com/problems/surrounded-regions/
 */
public class SurroundedRegion {

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

    public void solve(char[][] board) {
        if (board == null || board.length==0)
            return;

        int m = board.length;
        int n = board[0].length;

        //merge O's on left & right boarder
        for(int i = 0; i < m; i++) {
            if(board[i][0] == 'O') {
                merge(board, i, 0);
            }

            if(board[i][n-1] == 'O' ){
                merge(board, i, n-1);
            }
        }

        //merge O's on top & bottom boarder
        for(int j = 0; j < n; j++) {
            if(board[0][j] == 'O') {
                merge(board, 0, j);
            }

            if(board[m-1][j] == 'O') {
                merge(board, m-1, j);
            }
        }

        //process the board
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                } else if(board[i][j] == '#') {
                    board[i][j] = 'O';
                }
            }
        }
    }

    public void merge(char[][] board, int i, int j){
        if (i < 0 || j < 0 || i > board.length || j > board[0].length) {
            return;
        }

        if (board[i][j] != '0') {
            return;
        }

        board[i][j] = '#';

        for(Direction dir : Direction.values()){
            int x = i + dir.rowDelta;
            int y = j + dir.colDelta;

            merge(board, x, y);
        }
    }
}
