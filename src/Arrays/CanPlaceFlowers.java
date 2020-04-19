package Arrays;

/**
 * https://leetcode.com/problems/can-place-flowers/
 */
public class CanPlaceFlowers {

    private static boolean canPlace(int[] arr, int i) {
        return (i == 0 || arr[i - 1] == 0) && (i == arr.length - 1 || arr[i + 1] == 0);
    }

    public static boolean canPlaceFlowers(int[] arr, int n) {

        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 0 && canPlace(arr, i)) {
                arr[i] = 1;

                i++; // skip the next position.
                count++;

                if (count >= n) {
                    return true;
                }
            }
        }
        return false;
    }
}
