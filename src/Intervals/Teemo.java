package Intervals;
import java.util.Arrays;


/**
 * https://leetcode.com/problems/teemo-attacking/
 *
 * Example 1:
 *
 * Input: [1,3], 2
 * Output: 4
 * Explanation: At time point 1, Teemo starts attacking Ashe and makes Ashe be poisoned immediately.
 * This poisoned status will last 2 seconds until the end of time point 2.
 * And at time point 4, Teemo attacks Ashe again, and causes Ashe to be in poisoned status for another 2 seconds.
 * So you finally need to output 4.
 *
 *
 * Example 2:
 *
 * Input: [1,2], 2
 * Output: 3
 * Explanation: At time point 1, Teemo starts attacking Ashe and makes Ashe be poisoned.
 * This poisoned status will last 2 seconds until the end of time point 2.
 * However, at the beginning of time point 2, Teemo attacks Ashe again who is already in poisoned status.
 * Since the poisoned status won't add up together, though the second poisoning attack will still work at time point 2, it will stop at the end of time point 3.
 * So you finally need to output 3.
 *
 */
public class Teemo {

    public static int findTime(int[] arr, int sleepSpan) {
        int sleptUntil = 0;
        int count = 0;

        for (int i = 0; i < arr.length; i++) {
            if (sleptUntil < arr[i]) {
                count += sleepSpan;
            } else {
                count += arr[i] + sleepSpan - 1 - sleptUntil;
            }
            sleptUntil = arr[i] + (sleepSpan - 1);
        }
        return count;
    }

    public static void main(String[] args) {
        int[] arr = new int[2];
        arr[0] = 1;
        arr[1] = 2;

        System.out.println("----------------------------: " + findTime(arr, 2));

        int[] abc = new int[2];
        arr[0] = 1;
        arr[1] = 4;

        System.out.println("----------------------------: " + findTime(arr, 2));
    }

}



