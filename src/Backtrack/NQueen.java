package Backtrack;

/**
 * NQueen : http://www.geeksforgeeks.org/backtracking-set-3-n-queen-problem/
 *
 * Complexity:
 * O (n!) where n is size of board.
 * (majority of answers were n!).
 * https://www.quora.com/What-is-best-average-and-worst-case-in-case-of-N-Queen-Problem
 * http://stackoverflow.com/questions/21059422/time-complexity-of-n-queen-using-backtracking
 *
 * BB: 4
 *
 */
public class NQueen {

    private NQueen() {}

    private static void printBoard (int[][] board) {
        System.out.println("N queen solution.");
        for (int i = 0;  i < board.length; i++) {
            for (int j  = 0; j < board[0].length; j++) {
                if (board[i][j] == 1)
                    System.out.println(" row: " + i + " col: " +  j);
            }
        }
    }

    private static boolean canPlaceQueen (int[][] board, int row, int col) {
        // check in vertically upward
        for (int  i = 0; i < row; i++) {
            if (board[i][col] == 1) return false;
        }

        // same in NW direction.
        for (int i = row, j = col; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 1) return false;
        }

        // check in NE direction.
        for (int i = row, j = col; i >= 0  && j < board[0].length; i--, j++) {
            if (board[i][j]  == 1) return false;
        }

        return true;
    }

    private static boolean NQueenSolver (int[][] board, int row) {
        if (row == board.length) {
            printBoard(board);
            return true;
        }

        boolean returnValue = false;

        for (int j = 0; j < board[0].length; j++) {
            if (canPlaceQueen(board, row, j)) {
                board[row][j] = 1;
                returnValue = NQueenSolver (board, row + 1) || returnValue;
                board[row][j] = 0;
            }
        }

        return returnValue;
    }

    public static boolean solveNQueen(int[][] board) {
        return NQueenSolver (board, 0);
    }


    public static void main(String[] args) {
        int[][] m  = { {0, 0, 0, 0 },  {0, 0, 0, 0} , {0, 0, 0, 0 } , {0, 0, 0, 0 }};
        System.out.println(solveNQueen(m));
    }
}
