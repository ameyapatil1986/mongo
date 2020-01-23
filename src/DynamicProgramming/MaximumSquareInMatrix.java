package DynamicProgramming;

/**
 * https://www.youtube.com/watch?v=_Lf1looyJMU
 * https://leetcode.com/problems/maximal-square/
 */
public class MaximumSquareInMatrix {

    // method for Maximum size square sub-matrix with all 1s
    static void printMaxSubSquare(int m[][]) {
        int i, j;
        int[][] S = new int[m.length][m[0].length];

        int max_of_s, max_i, max_j;

        /* Set first column of S[][]*/
        for (i = 0; i < m.length; i++)
            S[i][0] = m[i][0];

        /* Set first row of S[][]*/
        for (j = 0; j < m[0].length; j++)
            S[0][j] = m[0][j];

        /* Construct other entries of S[][]*/
        int max = 0;
        int maxi = 0;
        int maxj = 0;
        for (i = 1; i < m.length; i++) {
            for (j = 1; j < m[0].length; j++) {
                if (m[i][j] == 1) {
                    S[i][j] = Math.min(S[i][j - 1], Math.min(S[i - 1][j], S[i - 1][j - 1])) + 1;
                    if (max < S[i][j]) {
                        max = S[i][j];
                        maxi = i;
                        maxj = j;
                    }
                } else {
                    S[i][j] = 0;
                }
            }
        }


//        System.out.println("Maximum size sub-matrix is: ");
//        for(i = maxi; i > maxi - max; i--) {
//            for(j = maxj; j > maxj - max; j--) {
//                System.out.print(m[i][j] + " ");
//            }
//            System.out.println();
//        }
    }

}
