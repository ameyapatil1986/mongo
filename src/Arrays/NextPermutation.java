package Arrays;

/**
 * https://www.youtube.com/watch?v=hPd4MFdg8VU
 * https://www.programcreek.com/2014/06/leetcode-next-permutation-java/
 *
 * Sample example:
 * [ 1 3 5 4 2 ]
 * next permutation
 * [ 1 4 2 3 5 ]
 */
public class NextPermutation {

    public void nextPermutation(int[] nums) {

        //find first decreasing digit going backwards or reverse.
        // 5 4 3 2 1
        int mark = -1;
        for (int i = nums.length - 1; i > 0; i--) {
            if (nums[i - 1] < nums[i]) {
                mark = i - 1;
                break;
            }
        }

        // complete array is descending.
        if (mark == -1) {
            reverse(nums, 0, nums.length - 1);
            return;
        }

        // best example: [ 3 5 4 2 1]
        // scan from right to left, find the first element/smallest that is greater than p.
        // look at https://www.programcreek.com/2014/06/leetcode-next-permutation-java/ for example.
        int idx = nums.length - 1;
        for (int i = nums.length - 1; i > mark; i--) {
            if (nums[i] > nums[mark]) {
                idx = i;
                break;
            }
        }

        swap(nums, mark, idx);

        reverse(nums, mark, nums.length - 1);
    }

    private void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }

    private void reverse(int[] nums, int i, int j) {
        while (i < j) {
            swap(nums, i, j);
            i++;
            j--;
        }
    }


}
