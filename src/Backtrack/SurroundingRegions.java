package Backtrack;

/**
 * https://www.youtube.com/watch?v=ztTLGMeleco
 */
public class SurroundingRegions {

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

    public void solve(char[][] board) {
        if(board == null || board.length==0)
            return;

        int m = board.length;
        int n = board[0].length;

        //merge O's on left & right boarder
        for(int i=0;i<m;i++){
            if(board[i][0] == 'O'){
                merge(board, i, 0);
            }

            if(board[i][n-1] == 'O'){
                merge(board, i, n-1);
            }
        }

        //merge O's on top & bottom boarder
        for(int j=0; j<n; j++){
            if(board[0][j] == 'O'){
                merge(board, 0, j);
            }

            if(board[m-1][j] == 'O'){
                merge(board, m-1, j);
            }
        }

        //process the board
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(board[i][j] == 'O') {
                    board[i][j] = 'X';
                } else if(board[i][j] == '#') {
                    board[i][j] = 'O';
                }
            }
        }
    }

    public void merge(char[][] board, int row, int col){
        if (row < 0 || row >= board.length || col < 0 || col >= board[0].length) {
            return;
        }

        // false condition
        if (board[row][col] != 0) {
            return;
        }

        board[row][col] = '#';


        for (Direction dir : Direction.values()) {
            int newRow = row + dir.getY();
            int newCol = col + dir.getX();

            merge(board, newRow, newCol);
        }
    }
}
