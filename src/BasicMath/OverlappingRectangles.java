package BasicMath;

/**
 * https://leetcode.com/problems/rectangle-overlap/
 */
public class OverlappingRectangles {

    private OverlappingRectangles() { }

    private static boolean intersectsFoo(int rect1_LeftX, int rect1_RightX, int rect1_TopY, int rect1_BottomY,
        int rect2_LeftX, int rect2_RightX, int rect2_TopY, int rect2_BottomY) {

        // is one rectangle on left side of other
        if (rect1_RightX < rect2_LeftX || rect2_RightX < rect1_LeftX) {
            return false;
        }

        // is one of the rectangle is below the other.
        if (rect1_TopY < rect2_BottomY || rect2_TopY < rect1_BottomY) {
            return false;
        }

        return true;
    }

}
