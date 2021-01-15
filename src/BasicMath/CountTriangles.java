package BasicMath;

// Java program to count number of triangles that can be
// formed from given array
import java.io.*;
import java.util.*;


/**
 * https://twchen.gitbook.io/leetcode/array/valid-triangle-number
 */
class CountTriangles {

    int triangleNumber(int[] nums) {
        int count = 0;
        Arrays.sort(nums);

        for(int i = nums.length - 1; i >= 2; i--) {
            int l = 0, r = i - 1;

            while(l < r) {
                if (nums[l] + nums[r] > nums[i]) {
                    count += r - l;
                    --r;
                } else{
                    ++l;
                }
            }
        }

        return count;
    }

    public static void main (String[] args) {
        int arr[] = {10, 21, 22, 100, 101, 200, 300};
//        System.out.println("Total number of triangles is " + triangleNumber(arr));
    }
}
