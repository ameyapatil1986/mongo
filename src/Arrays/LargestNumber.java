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

//        Arrays.sort(arr, Comparator.comparing((a, b) -> (b + a).compareTo((String)a + b)));

        // descending array.
        Arrays.sort(arr, new Comparator<String>(){
            public int compare(String a, String b){
                return (b + a).compareTo(a + b);
            }
        });

        if(arr[0].equals("0")) return "0";

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < arr.length; i++){
            sb.append(arr[i]);
        }
        return sb.toString();
    }
}
