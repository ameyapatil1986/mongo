package Matrices;

import java.util.*;

public class ValidSudoku {

   public boolean isSudokuValidator(char[][] board) {

       Set<String> seen = new HashSet<>();

       for (int i = 0; i < 9; i++) {
           for (int  j = 0; j < 9; j++) {
               char current_val = board[i][j];
               if (current_val != '.') {
                   if (!seen.add(current_val + " found in row " + i) ||
                        !seen.add(current_val + " found in col " + j) ||
                         !seen.add(current_val + " found in sub_box " + i/3 + "-" + j/3)) {
                       return false;
                   }
               }
           }
       }
       return true;
   }
}
