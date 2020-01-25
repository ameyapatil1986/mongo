package BasicMath;

/**
 * https://www.programcreek.com/2013/01/leetcode-search-a-2d-matrix-java/
 *
 * 1) Integers in each row are sorted from left to right.
 * 2) The first integer of each row is greater than the last integer of the previous row.
 */
public class SearchAMatrix2 {

    public boolean searchMatrix(int[][] matrix, int target) {
        if(matrix==null || matrix.length==0 || matrix[0].length==0)
            return false;

        int m = matrix.length;
        int n = matrix[0].length;

        int start = 0;
        int end = m * n - 1;

        while (start <= end) {
            int mid = (start + end)/2;
            int midX = mid / n;
            int midY = mid % n;

            if (matrix[midX][midY] == target)
                return true;

            if (matrix[midX][midY] < target) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        return false;
    }
}
