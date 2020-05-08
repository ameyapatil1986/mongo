package Arrays.search;

/**
 * Also a leetcode question
 * https://leetcode.com/articles/find-peak-element/
 * https://www.geeksforgeeks.org/find-a-peak-in-a-given-array/
 *
 * Youtube:
 * https://www.youtube.com/watch?v=CFgUQUL7j_c
 * https://www.youtube.com/watch?v=a7D77DdhlFc
 *
 * Peak element is greater than neighbors
 *
 * eg: [ 1 2 3 4 5 1]
 * here 3 is peak.
 *
 */
public class PeakElement {

    public static Integer getPeakElement(int[] array) {

        if (array == null || array.length == 0) {
                return null;
        }

        int n = array.length;

        int start = 0;
        int end = n - 1;

        while (start <= end) {
            int mid = (start + end) / 2;

            if (mid == 0) {
                return array[mid];
            }

            if ((array[mid - 1] <= array[mid]) && (mid == n - 1 || array[mid] >= array[mid + 1])) {
                return array[mid];
            }

            if (array[mid - 1] > array[mid]) {
                end = mid - 1; // go left.
            } else {
                start = mid + 1;
            }
        }

        return null;
    }
}
