package BasicMath;

/**
 * https://leetcode.com/problems/rectangle-overlap/
 */
public class OverlappingRectangles {

    private OverlappingRectangles() { }

    public boolean isRectangleOverlap(int[] rec1, int[] rec2) {
        // right x < left x
        if (rec1[2] <= rec2[0] || rec1[2] <= rec2[0]) {
            return false;
        }

        // top y < bottom y.
        if (rec1[3]<=rec2[1] || rec2[3]<=rec1[1]) {
            return false;
        }


        return true;
    }

}
