package Matrices;

public class SparseMatrixMultiplication {

    // O(n2)
    public static int[][] multiply(int[][] A, int[][] B) {
        // validation check.
        int[][] m = new int[A.length][B[0].length];

        for (int i = 0; i < m.length; i++) {
            // for each non-zero column of A.
            for (int k = 0; k < A[0].length; i++) {
                if (A[i][k] != 0) {
                    // pick the kth row in B's matrix and go through all columns.
                    for (int j = 0; j < B[0].length; j++) {
                        m[i][j] += A[i][k] * B[k][j];
                    }
                }
            }
        }

        return m;
    }

}
