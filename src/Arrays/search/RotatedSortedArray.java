package Arrays.search;

/**
 *  https://leetcode.com/problems/search-in-rotated-sorted-array/
 */
public class RotatedSortedArray {

    private RotatedSortedArray() {}

    /**
     * pivot is the index of the largest element, whose next element is smallest.
     * eg: [10, 20, 30, 1, 2] in this case pivot is i=2, which corresponds to 30.
     *
     * @param a
     * @return
     */
    private static int getPivot(int[] a) {
        int lb = 0;
        int hb = a.length - 1;

        while (lb <= hb) {
            int mid = (lb + hb)/2;

            // eg: 4, 5, 1, 2.
            // mid < a.length - 1, could be encountered in case of descending array: 1, 2, 3, 4
            if ((mid < a.length - 1) && a[mid + 1] < a[mid]) {
                return mid;
            }

            // eg: 4, 5, 1, 2, 3
            // mid > 0, could be encountered in case of descending array: 4, 3, 2, 1
            if (mid > 0 &&  a[mid] < a[mid -1]) {
                return mid - 1;
            }

            // Why a[lb] <= a[mid], and not, a[lb] >= a[mid] ? Ans:  // eg: 2, 2, 2, 1
            if (a[lb] < a[mid]) {
                // eg: 2, 3, 4, 5, 6, 7, 8, 9, 1
                lb = mid + 1;
            } else {
                // eg: 9, 1, 2, 3, 4, 5, 6, 7, 8
                hb = mid - 1;
            }
        }

        return -1;
    }



    public static int pivotSearch(int[] a, int x) {
        int pivot = getPivot(a);

        // Array is already sorted.
        if (pivot == -1) {
            return binarySearch(a, 0, a.length - 1, x);
        }

        if (a[pivot] == x) return pivot;

        /*
         * [5, 6, 7, 1, 2, 3]
         * x = 7
         */
        if (a[0] < x) {
            return binarySearch(a, 0, pivot, x);
        } else {
            /*
             * [5, 6, 7, 1, 2, 3]
             * x = 2
             */
            return binarySearch(a, pivot + 1, a.length - 1, x);
        }
    }



    private static int binarySearch(int[] a, int lb, int hb, int x) {
        while (lb <= hb) {
            int mid = (lb + hb) / 2;

            if (x == a[mid]) return mid;

            if (x < a[mid]) {
                hb = mid - 1;
            } else {
                lb = mid + 1;
            }
        }
        return -1;
    }


    public static void main(String[] args) {
        // left side of pivot
        //  int[] a1 = {5, 6, 7, 8, 1, 2, 3, 4};

        int[] a1 = {2, 3, 4, 5, 6, 7, 8, 1};
        System.out.println("The value of pivot search is: " + pivotSearch(a1, 7));

        // right side of the pivot.
        int[] a2 = {5, 6, 7, 8, 1, 2, 3, 4};
        System.out.println("The value of pivot search is: " + pivotSearch(a2, 3));

        // right side of the pivot.
        int[] a3 = {1, 2, 3, 4};
        System.out.println("The value of pivot search is: " + pivotSearch(a3, 3));

    }
}




//
///**
// * This is utility class for operations on rotated one-d array.
// *
// * Note: a sorted array, reverse-sorted or a single element is array is not considered rotated sorted.
// * Eg:
// * [4, 5, 1, 2, 3] is condidered rotated.
// * While.
// * [1, 2, 3, 4, 5] is not considered rotated
// *
// *
// * Complexity: O (log n)
// *
// * BB:          18
// *
// * Reference:
// * http://codereview.stackexchange.com/questions/39204/binary-search-in-rotated-sorted-array
// *
// * @author SERVICE-NOW\ameya.patil
// */
//public final class RotatedOneDSortedArray {
//
//    private RotatedOneDSortedArray() { }
//
//    /**
//     * Searches for the value provided in the rotated sorted array.
//     * If found, returns the index, of provided array, else returns -1.
//     * If array is not rotated and sorted, then results are unpredictable
//     *
//     * Throws an exception if array is null.
//     *
//     * @param a the rotated sorted array
//     * @param x the element to be searched
//     * @returns the index at which the element is found, else returns -1
//     */
//    public static int binarySearchForRotatedArray(int[] a, int x) {
//        int offset = getOffset(a);
//        if (offset == -1) return -1;
//
//        return binarySearch(a, x, offset);
//    }
//
//
//
//    private static int binarySearch (int[] a, int x, int offset) {
//        assert a != null;
//
//        int lb = 0;
//        int hb = a.length - 1;
//
//        while (lb <= hb) {
//            int mid = (lb + hb)/2;
//            int midOffSet = (mid + offset) % a.length;
//
//            if (a[midOffSet] ==  x) {
//                return midOffSet;
//            }
//
//            if (a[midOffSet] < x) {
//                lb = mid + 1;
//            } else {
//                hb = mid - 1;
//            }
//        }
//
//        return -1;
//    }
//
//
//    /**
//     * Returns the index of greatest element of the rotated sorted array.
//     * if array is not rotated sorted, results are unprodictable
//     *
//     * @param a     the input array
//     * @return      the index of the highest element in the array.
//     */
//    private static int getOffset(int[] a) {
//        assert a!= null;
//
//        int lb = 0;
//        int hb = a.length - 1;
//
//        // Check if rotation is 0
//        if (a[lb] < a[hb])
//            return lb;
//
//        // ideally lb < hb, should have worked, but just following optimizations.
//        while (lb <= hb) {
//            int mid = (lb + hb)/2;
//
//            if (a[mid] > a[mid + 1]) {
//                return mid + 1;
//            } else if (a[mid] > a[hb]) {
//                // go right.
//                lb = mid + 1;
//            } else {
//                // go left.
//                hb = mid; // note i cannot do mid - 1
//            }
//        }
//        return -1;
//    }
//
//
//    public static void main(String[] args) {
//        // even length of the array.
//        int[] a1 = {6, 7, 8, 1, 2, 3};
//        System.out.println("Expected -1, Actual : " + binarySearchForRotatedArray(a1, -1));
//        int[] a2 = {6, 7, 8, 1, 2, 3};
//        System.out.println("Expected 1, Actual : " + binarySearchForRotatedArray(a2, 7));
//        int[] a3 = {6, 7, 8, 1, 2, 3};
//        System.out.println("Expected 5, Actual : " + binarySearchForRotatedArray(a3, 3));
//        int[] a4 = {6, 7, 8, 1, 2, 3};
//        System.out.println("Expected 2, Actual : " + binarySearchForRotatedArray(a4, 8));
//        int[] a5 = {6, 7, 8, 1, 2, 3};
//        System.out.println("Expected 3, Actual : " + binarySearchForRotatedArray(a5, 1));
//
//
//        // odd length of the array.
//        int[] a6 = {4, 6, 7, 8, 1, 2, 3};
//        System.out.println("Expected -1, Actual : " + binarySearchForRotatedArray(a6, -1));
//        int[] a7 = {4, 6, 7, 8, 1, 2, 3};
//        System.out.println("Expected 2, Actual : " + binarySearchForRotatedArray(a7, 7));
//        int[] a8 = {4, 6, 7, 8, 1, 2, 3};
//        System.out.println("Expected 6, Actual : " + binarySearchForRotatedArray(a8, 3));
//        int[] a9 = {4, 6, 7, 8, 1, 2, 3};
//        System.out.println("Expected 3, Actual : " + binarySearchForRotatedArray(a9, 8));
//        int[] a10 = {4, 6, 7, 8, 1, 2, 3};
//        System.out.println("Expected 4, Actual : " + binarySearchForRotatedArray(a10, 1));
//
//        // completely sorted.
//        int[] a11 = {1, 2, 3, 4, 5, 6, 7};
//        System.out.println("Expected 4, Actual : " + binarySearchForRotatedArray(a11, 5));
//    }
//}
