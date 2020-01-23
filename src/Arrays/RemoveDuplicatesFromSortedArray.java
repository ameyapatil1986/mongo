package Arrays;

/**
 * https://leetcode.com/problems/remove-duplicates-from-sorted-array/
 */
public class RemoveDuplicatesFromSortedArray {

    private static int removeDuplicates(int[] nums) {
        int n = nums.length;

        /*
         * This index will move only when we modify the array in-place to include a new
         * non-duplicate element.
         */
        int j = 0;

        for (int i = 0; i < n; i++) {
            /*
             * If the current element is equal to the next element, then skip the current
             * element because it's a duplicate.
             */
            if (i < n - 1 && nums[i] == nums[i + 1]) {
                continue;
            }

            nums[j++] = nums[i];
        }

        return j;
    }
}
