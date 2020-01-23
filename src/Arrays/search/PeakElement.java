package Arrays.search;

/**
 * Also a leetcode question
 * https://leetcode.com/articles/find-peak-element/
 * https://www.geeksforgeeks.org/find-a-peak-in-a-given-array/
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

            if ((mid == 0 || array[mid - 1] <= array[mid]) && (mid == n - 1 || array[mid] >= array[mid + 1])) {
                return array[mid];
            } else if (mid > 0 &&  array[mid] < array[mid - 1]) {
                end = mid - 1; // go left.
            } else {
                start = mid + 1;
            }
        }

        return null;
    }
//
//    // A binary search based function that returns index of a peak
//    // element
//    static int findPeakUtil(int arr[]) {
//        int low = 0;
//        int high = arr.length - 1;
//
//
//        while (low < high) {
//            // Find index of middle element
//            int mid = (low + high) / 2;
//
//            if (arr[mid] < arr[mid + 1]) {
//                low = mid + 1;
//            } else {
//                high = mid;
//            }
//        }
//
//        return low;
//    }
//
//    public int firstBadVersion(int n) {
//        int i = 1, j = n;
//
//        while (i < j) {
//            int m = (i + j) / 2;
//            if (!isBadVersion(m)) {
//                i = m + 1;
//            } else {
//                j = m;
//            }
//        }
//
//        if (isBadVersion(i)) {
//            return i;
//        }
//
//        return j;
//    }
}
