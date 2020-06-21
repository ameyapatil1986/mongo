package Matrices;

/**
 * https://tenderleo.gitbooks.io/leetcode-solutions-/GoogleMedium/74.html
 *
 * O( log mn )
 */
public class FindElementsInMatrices {

    public boolean searchMatrix(int[][] matrix, int target) {
        int row = matrix.length;
        int col = matrix[0].length;
        int l = 0;
        int r = row * col - 1;

        while (l <= r ) {
            int mid = (l + r) / 2;
            int val = matrix[ mid/col ][ mid%col ]; // Only 1 conversion step => a[mid] converts into matrix[mid/col][mid%col]

            if (val == target) return true;

            if (val < target){
                l = mid+1;
            } else{
                r = mid-1;
            }
        }

        return false;
    }
}
