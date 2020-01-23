package Matrices;

/**
 * https://tenderleo.gitbooks.io/leetcode-solutions-/GoogleMedium/74.html
 */
public class FindElementsInMatrices {

    public boolean searchMatrix(int[][] matrix, int target) {
        int row = matrix.length;
        int col = matrix[0].length;
        int l = 0;
        int r = row * col - 1;

        while (l <=r ){
            int mid = l + (r-l) / 2;
            int val = matrix[mid/col][mid%col];

            if(val == target) return true;

            if (val > target){
                r = mid-1;
            } else{
                l = mid+1;
            }
        }

        return false;
    }
}
