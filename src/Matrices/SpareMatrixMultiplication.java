package Matrices;

public class SpareMatrixMultiplication {

    // O(n2)
    public static int[][] multiply(int[][] A, int[][] B) {
        // validation check.

        int[][] m = new int[A.length][B[0].length];

        for (int i = 0; i < A.length; i++) {
            for (int k = 0; k < A[0].length; i++) {
                if (A[i][k] != 0) {
                    for (int j = 0; j < B.length; j++) {
                        m[i][j] += A[i][k] * B[k][j];
                    }
                }
            }
        }

        return null;
    }
}
