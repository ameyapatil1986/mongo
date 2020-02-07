package DynamicProgramming;


/**
 * Contains coordinates of the
 * topMost left, of matrix, obtained by getRowOne and colRowOne
 * bottomMost right, of matrix, obtained by getRowTwo and getColTwo
 *
 * @author javadeveloper
 */
final class RectangleCoord {
    private final int rowOne;
    private final int colOne;
    private final int rowTwo;
    private final int colTwo;

    public RectangleCoord(int rowOne, int colOne, int rowTwo, int colTwo) {
        this.rowOne = rowOne;
        this.colOne = colOne;
        this.rowTwo = rowTwo;
        this.colTwo = colTwo;
    }

    public int getRowOne() {
        return rowOne;
    }

    public int getColOne() {
        return colOne;
    }

    public int getRowTwo() {
        return rowTwo;
    }

    public int getColTwo() {
        return colTwo;
    }
}


/**
 * Returns the maximum rectangle in the matrix
 *
 * Reference:
 * http://codereview.stackexchange.com/questions/39443/maxsum-sub-matrix-within-a-matrix
 *
 * Complexity:
 *  O(row2 * col)
 *
 * BB: 9
 *
 * @author javadeveloper
 */
public final class MaximumSumRectangeInMatrix {

    private MaximumSumRectangeInMatrix() { }

    private static class MaxSubArray {
        int start;
        int end;
        int maxSum;

        public MaxSubArray(int start, int end, int maxSum) {
            this.start = start;
            this.end = end;
            this.maxSum = maxSum;
        }
    }

    /**
     * https://leetcode.com/problems/rotate-image/
     */
    private static MaxSubArray maxSubArraySum(int[] a) {
        assert a != null && a.length > 0;

        int currSum = a[0];
        int startTmp = 0;

        int max = a[0];
        int start = 0;
        int end = 0;

        for (int i = 1; i < a.length; i++) {
            if (currSum < 0) {
                startTmp = i;
                currSum = a[i];
            } else {
                currSum += a[i];
            }
            if (currSum > max) {
                max = currSum;
                start = startTmp;
                end = i;
            }
        }
        return new MaxSubArray(start, end, max);
    }

    /**
     * Returns the RectangleCoord object containing the maximum
     * rectangle in the matrix.
     *
     * @param  The input matrix.
     * @return The rectanglecoor object containing the coordinates of the maximum sub matrix
     */
    public static RectangleCoord calMaxRectangle(int[][] m) {
        if (m.length == 0 || m[0].length == 0) {
            throw new IllegalArgumentException("The matrix should be non empty");
        }

        int max = Integer.MIN_VALUE;
        int rowOne = 0;
        int colOne = 0;
        int rowTwo = 0;
        int colTwo = 0;

        for (int fromRow = 0; fromRow < m.length; fromRow++) {
            int[] temp = new int[m[0].length];
            for (int toRow = fromRow; toRow < m.length; toRow++) {

                for (int col = 0; col < m[0].length; col++) {
                    temp[col] = temp[col] + m[toRow][col];
                }

                MaxSubArray maxSubArrayCoord = maxSubArraySum(temp);

                if (maxSubArrayCoord.maxSum > max) {
                    max = maxSubArrayCoord.maxSum;
                    rowOne = fromRow;
                    colOne = maxSubArrayCoord.start;
                    rowTwo = toRow;
                    colTwo = maxSubArrayCoord.end;
                }
            }
        }

        return new RectangleCoord(rowOne, colOne, rowTwo, colTwo);
    }

    public static void main(String[] args) {


        int[][] m = {  {-20, -30, -40, -50},
            { 10,  20,  30,  50},
            { 50,  60,  70,  80},
            {-10, -20, -30, -40},
        };

        RectangleCoord r = calMaxRectangle(m);
        System.out.println(r.getRowOne() + " : " + r.getColOne());
        System.out.println(r.getRowTwo() + " : " + r.getColTwo());

    }

}
