package Arrays.sum;

import java.util.*;


/**
 * https://leetcode.com/problems/3sum/
 * https://www.programcreek.com/2012/12/leetcode-3sum/
 */
public class ThreeSum {

    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);

        ArrayList<List<Integer>> result = new ArrayList<>();

        for (int i = 0; i < nums.length - 2; i++) {
            // The solution set must not contain duplicate triplets.
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            int k = i + 1;
            int l = nums.length - 1;

            while (k < l) {
                if (nums[i] + nums[k] + nums[l] > 0) {
                    l--;
                } else if (nums[i] + nums[k] + nums[l] < 0) {
                    k++;
                } else {
                    ArrayList<Integer> list = new ArrayList<>();
                    list.add(nums[i]);
                    list.add(nums[k]);
                    list.add(nums[l]);
                    result.add(list);
                    k++;
                    l--;
                }

                while(k<l && nums[l]==nums[l+1] ){
                    l--;
                }

                while(k<l && nums[k]==nums[k-1]){
                    k++;
                }
            }
        }

        return result;
    }
}
