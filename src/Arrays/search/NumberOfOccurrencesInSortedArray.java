package Arrays.search;

/**
 * http://www.geeksforgeeks.org/count-number-of-occurrences-in-a-sorted-array/
 *
 * BB:
 * 38
 *
 * Complexity:
 * O(logn)
 */
public final class NumberOfOccurrencesInSortedArray {

    private NumberOfOccurrencesInSortedArray() { }

    public static int countOccurences(int[] arr, int x) {
        int lb = lowerIndex(arr, x);

        if (lb == -1) return 0;

        int hb = higherIndex(arr, x);

        // simplest way to remember. if there is only 1 element in the array. then lb = hb. then answer should be 1 not 0.
        return hb - lb + 1;
    }


    private static int lowerIndex (int[] arr, int x) {
        int low = 0;
        int high = arr.length - 1;

        while (low <= high) {

            int mid = (low + high)/2;

            if (arr[mid] == x && (mid == 0 || arr[mid -1] < x)) {
                return mid;
            }

            if (arr[mid] < x) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return -1;
    }

    private static int higherIndex (int[] arr, int x) {
        int low = 0;
        int high = arr.length - 1;

        while (low <= high) {

            int mid = (low + high)/2;

            if (arr[mid] == x  && ((mid == arr.length - 1) || x < arr[mid + 1])) {
                return mid;
            }

            if (arr[mid] <= x) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 3, 3, 4, 5};
        System.out.println("---: " + countOccurences(arr, 3));

        // full array is of same value
        int[] arr1 = {3, 3, 3, 3, 3};
        System.out.println("---: " + countOccurences(arr1, 3));

        // value not present.
        int[] arr2 = {1, 2, 4, 5};
        System.out.println("---: " + countOccurences(arr2, 3));
    }
}
