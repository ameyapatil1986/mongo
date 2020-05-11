package Matrices;


/**
 * References:
 * http://haroonsaeed.wordpress.com/2005/09/01/printing-an-array-in-spiral-order/
 * http://www.technicalinterviewquestions.net/2009/03/print-2d-array-matrix-spiral-order.html
 * http://stackoverflow.com/questions/726756/print-two-dimensional-array-in-spiral-order
 * http://chesterli0130.wordpress.com/2012/10/27/printing-mn-matrix-in-spiral-order/
 * http://www.docjar.com/html/api/java/lang/Math.java.html
 * http://www.docjar.com/html/api/java/util/Arrays.java.html
 *
 * Complexity: O (n * m)
 *
 * Asked by Linkedin.
 *
 * @author SERVICE-NOW\ameya.patil
 */
public final class CircularlyPrintMatrix {

    private CircularlyPrintMatrix() {}

    public static void spiralPrint(int[][] a) {
        int rowStart = 0, colStart = 0;
        int rowEnd = a.length;
        int colEnd = a[0].length;

        while (rowStart < rowEnd && colStart < colEnd) {

            /* Print the first row from the remaining rows */
            for (int i = colStart; i < colEnd; ++i) {
                System.out.print(a[rowStart][i] + " ");
            }
            rowStart++;

            /* Print the last column from the remaining columns */
            for (int i = rowStart; i < rowEnd; ++i) {
                System.out.print(a[i][colEnd-1] + " ");
            }
            colEnd--;

            /* Print the last row from the remaining rows */
            if (rowStart < rowEnd) {
                for (int i = colEnd-1; i >= colStart; --i) {
                    System.out.print(a[rowEnd-1][i] + " ");
                }
                rowEnd--;
            }

            /* Print the first column from the remaining columns */
            if (colStart < colEnd) {
                for (int i = rowEnd - 1; i >= rowStart; --i) {
                    System.out.print(a[i][colStart] + " ");
                }
                colStart++;
            }
        }
    }



    /**
     * Null
     * Illegal
     * Excess space
     *
     * Case:
     * 1. single row
     * 2. single col
     * 3. 3*3 matrix.
     */
    public static void main(String[] args) {
        System.out.println("Testing single row.");
        int[] arr1r = {1, 2, 3};
        int[][] m1r = {arr1r};
        System.out.print("Expecting: 1 2 3, Actual: " );
        spiralPrint (m1r);

        System.out.println();

        System.out.println("Testing single col.");
        int[][] m1c = {{1}, {2}, {3}};
        System.out.print("Expecting: 1 2 3, Actual: " );
        spiralPrint (m1c);

        System.out.println();

        System.out.println("Testing number of rows > number of columns.");
        int[] arr21r = {1, 2};
        int[] arr22r = {3, 4};
        int[] arr23r = {5, 6};
        int[][] m2r = {arr21r, arr22r, arr23r};
        System.out.print("Expecting: 1 2 4 6 5 3, Actual: " );
        spiralPrint(m2r);

        System.out.println();

        System.out.println("Testing number of cols > number of rows.");
        int[] arr21c = {1, 2, 3};
        int[] arr22c = {4, 5, 6};
        int[][] m2c = {arr21c, arr22c};
        System.out.print("Expecting: 1 2 3 6 5 4, Actual: " );
        spiralPrint(m2c);

        System.out.println();

        System.out.println("Even Testing N * N.");
        int[] arr31 = {1,   2,  3,  4};
        int[] arr32 = {5,   6,  7,  8};
        int[] arr33 = {9,  10, 11, 12};
        int[] arr34 = {13, 14, 15, 16};
        int[][] m3 = {arr31, arr32, arr33, arr34};
        System.out.print("Expecting: 1 2 3 4 8 12 16 15 14 13 9 5 6 7 11 10, Actual: " );
        spiralPrint(m3);

        System.out.println();

        System.out.println("Even Odd N * N.");
        int[] arr41 = {1,  2,  3};
        int[] arr42 = {4,  5,  6};
        int[] arr43 = {7,  8,  9};
        int[][] m4 = {arr41, arr42, arr43};
        System.out.print("Expecting: 1 2 3 6 9 8 7 4 5, Actual: " );
        spiralPrint(m4);
    }
}
