package Arrays;

import java.util.*;

/**
 * https://leetcode.com/problems/relative-ranks/
 *
 * Input: [5, 4, 3, 2, 1]
 * Output: ["Gold Medal", "Silver Medal", "Bronze Medal", "4", "5"]
 * Explanation: The first three athletes got the top three highest scores,
 * so they got "Gold Medal", "Silver Medal" and "Bronze Medal".
 * For the left two athletes, you just need to output their relative ranks according to their scores.
 */
public class RelativeRanks {

    public String[] findRelativeRanks(int[] nums) {

        int[] c_nums = nums.clone();
        Arrays.sort(c_nums); // highest number i.e. last item in list has higest rank
        Map<Integer, Integer> ranks = new HashMap<>();
        int rank = 1;

        for(int i = c_nums.length - 1; i >= 0; i--){
            ranks.put(c_nums[i], rank++);
        }

        String[] medals = {"Gold Medal", "Silver Medal", "Bronze Medal"};
        String[] ans = new String[nums.length];
        for(int i = 0; i < nums.length; i++){
            int num = nums[i];
            int r = ranks.get(num);
            if (r <= 3){
                ans[i] = medals[r-1];
            }else{
                ans[i] = r + "";
            }
        }
        return ans;
    }
}
