package Arrays.container;

/**
 * https://leetcode.com/problems/trapping-rain-water/
 */
public class TrappingRainwaterHistogram {

    public static int maxWaterIsHere(int[] arr) {

        int low = 0;
        int high = arr.length - 1;

        int leftMax = arr[0];
        int rightMax = arr[high];
        int result = 0;

        while (low < high) {

            leftMax = Math.max(arr[low], leftMax);
            rightMax = Math.max(arr[high], rightMax);

            if (arr[low] < arr[high]) {
                result  = result + (leftMax - arr[low]);
                low++;
            } else {
                result = result + (rightMax - arr[high]);
                high--;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int[] arr = {10, 5, 2, 15, 3 , 2, 11};
        System.out.println(maxWaterIsHere(arr));
    }
}
