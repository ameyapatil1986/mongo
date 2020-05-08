package Matrices;


/**
 * https://leetcode.com/problems/search-a-2d-matrix-ii/
 *
 * Sample example:
 * https://bitbucket.org/ameyapatil/all-images/commits/0848982c8a57575364d04fc83969ca468b4c3da4
 */

/**
 * This means a row is sorted and so is column.
 *
 * Complexity: O (rows + columns)
 *
 * References:
 * http://leetcode.com/2010/10/searching-2d-sorted-matrix-part-ii.html   ( BEST LINK )
 * http://stackoverflow.com/questions/17256335/avoiding-null-pointer-when-single-value-needs-to-be-returned
 * http://www.docjar.com/html/api/java/util/Arrays.java.html
 * http://www.geeksforgeeks.org/divide-conquer-set-6-search-row-wise-column-wise-sorted-2d-array/
 *
 * Diagrams:
 * ---------
 * https://bitbucket.org/ameyapatil/all-images/commits/625e82bf61b8074482f1fa64b8bd4a4850aadece?at=master
 *
 * Complexity:
 * -----------
 * n = row count.
 * m = column count.
 *
 * O (logn * logm) ( complexity is correct be happy )
 *
 * BB:
 * ---
 * 3
 *
 */
public class FindElementsInSortedMatrix2 {

    public static int findNearestValueLesserThanOrEqualToX (int[] a, int x,  int low, int high) {

        while (low <= high) {
            int mid = (low + high) / 2;

            if (a[mid] == x) return mid;

            // test perfect range.
            // NumOccurences: if (arr[mid] == x  && ((mid == arr.length - 1) || x < arr[mid + 1])) {
            if (a[mid] < x && ((mid == a.length - 1) || x < a[mid + 1])) {
                return mid;
            }

            // keep searching.
            if (a[mid] < x) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return -1;
    }

    /**
     * Following the pattern of Arrays and binary search on docjar line 988
     * http://www.docjar.com/html/api/java/util/Arrays.java.html
     *
     * @param m
     * @param x
     * @return
     */
    public static boolean matrixSearch(int[][] m, int x) {
        return matrixSearch (m, x, 0, m.length - 1, 0, m[0].length -1);
    }

    /*
     * cannot do a while loop here since we call recursion twice,
     * matrixSearch (m, x, currentRow + 1, downRow, leftColumn, currentColumn) ||
     * matrixSearch (m, x, upRow, currentRow - 1, currentColumn + 1, rightColumn);
     *
     */
    private static boolean matrixSearch (int[][] m, int x, int upRow, int downRow, int leftColumn, int rightColumn) {

        if (upRow > downRow) {
            return false;
        }

        final int midRow = (downRow + upRow) / 2;

        // reach middle column.
        final int currentColumn = findNearestValueLesserThanOrEqualToX (m[midRow], x, leftColumn, rightColumn); // find the row.

        /*
         * x is smallest element in the current row, Go up AND ONLY UP. DONT GO DOWN, AS IT MAKES NO SENSE.
         */
        if (currentColumn < 0) {
            return matrixSearch (m, x, upRow, midRow - 1, leftColumn, rightColumn);
        }

        if (x == m[midRow][currentColumn]) {
            return true;
        }

        // https://bitbucket.org/ameyapatil/all-images/commits/0848982c8a57575364d04fc83969ca468b4c3da4
        // if (x > m[currentRow][currentColumn]) {
        return matrixSearch (m, x, upRow, midRow - 1, currentColumn + 1, rightColumn) ||
            matrixSearch (m, x, midRow + 1, downRow, leftColumn, currentColumn);
    }

    public static void main(String args[]) {
        int[][] m = { { 10, 20,   30,   40,   95 },
            { 60, 70,   80,   90,  100 },
            { 75, 120, 130,  140,  150 },
        };


        System.out.println(matrixSearch(m, 75)); // goes to bottom left of '70'
        System.out.println(matrixSearch(m, 95)); // goes to top right of '90'

        // matrixSearch(m, 9);

        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[0].length; j++) {
                System.out.print("Finding: " + m[i][j] + ", ");
                System.out.println("Expecting true, Actual: " + matrixSearch(m, m[i][j]));
            }
        }

        // testing out of matrix
        System.out.println("Finding -40, Expecting: false, Actual: " + matrixSearch(m, -40));
        System.out.println("Finding 42, Expecting: false, Actual: " + matrixSearch(m, 42));
    }
}
