package Strings.substringsum;

import java.util.HashMap;
import java.util.Map;


public class LongestSubstringWithSumK {


    //eg.1 : 1 1 2 1 1,   k=2   result = 2
    //eg.2 : 1 1 1        k=2   result = 2
    //eg.3 : 2 -2 2 2     k=2   result = 2

    // Find maximum length sub-array with sum S present in the given array
    public static void maxLengthSubArray(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int sum = 0;
        int len = 0;
        int ending_index = -1;

        // traverse the given array
        for (int i = 0; i < nums.length; i++) {

            sum += nums[i];

            if (!map.containsKey(sum)) {
                map.put(sum, i);
            }

            if (map.containsKey(sum - k) && len < i - map.get(sum - k)) {
                len =  i - map.get(sum - k);
                ending_index = i;
            }
        }

        // print the sub-array
        System.out.println("[" + (ending_index - len + 1) + ", " + ending_index + "]");
    }
}
