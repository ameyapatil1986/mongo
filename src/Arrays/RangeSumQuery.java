package Arrays;

/**
 * https://leetcode.com/problems/range-sum-query-immutable/
 */
public class RangeSumQuery {

        private int[] nums;

        public RangeSumQuery(int[] nums) {
            this.nums = nums;

            // Calculate the prefix sum
            for (int i = 1; i < nums.length; i++) {
                nums[i] += nums[i - 1];
            }
        }

        public int sumRange(int i, int j) {
            if (i < 0 || i >= nums.length ||
                j < 0 || j >= nums.length ||
                i > j) {
                return 0;
            }

            if (i == 0) {
                return nums[j];
            } else {
                return nums[j] - nums[i - 1];
            }
        }

}
