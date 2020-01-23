package Arrays;

/**
 * https://www.programcreek.com/2015/02/leetcode-increasing-triplet-subsequence-java/
 */
public class IncreasingTriplet {

    public boolean increasingTriplet(int[] nums) {
        int small = Integer.MAX_VALUE;
        int big = Integer.MAX_VALUE;

        for (int num: nums) {
            if (num <= small) {
                small = num;// update x to be a smaller value
            } else if (num <= big) {
                big = num; // update y to be a smaller value
            } else {
                return true;
            }
        }

        return false;
    }


}
