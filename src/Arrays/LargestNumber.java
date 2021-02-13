package Arrays;

import java.util.Arrays;
import java.util.Comparator;


/**
 * https://leetcode.com/problems/largest-number/
 *
 * http://codesniper.blogspot.com/2015/04/179-largest-number-leetcode-java.html
 * https://www.programcreek.com/2014/02/leetcode-largest-number-java/
 */
public class LargestNumber {

    public String largestNumber(int[] nums) {
        String[] arr = new String[nums.length];

        for(int i = 0; i < nums.length; i++){
            arr[i] = String.valueOf(nums[i]);
        }

        // https://stackoverflow.com/questions/61043870/how-to-translate-following-comparator-into-compatator-comparing
        // eg: 90 and 9 becomes 909 and 990
        Arrays.sort(arr, (a, b) -> (b + a).compareTo(a + b));

        if(arr[0].equals("0")) return "0";

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < arr.length; i++){
            sb.append(arr[i]);
        }
        return sb.toString();
    }
}
