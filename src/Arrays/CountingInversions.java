package Arrays;


/**
 * sample example:
 * 4, 3, 2, 1.
 */

/**
 * Reference:
 * http://www.geeksforgeeks.org/counting-inversions/
 * http://codereview.stackexchange.com/questions/54756/counting-inversions
 * https://bitbucket.org/ameyapatil/all-images/commits/fa0efd6d3ae518fabe9dd4653b47e99bb88fedd0
 *
 *
 * BB:
 * 27
 *
 * Complexity:
 * O(nlogn) - time.
 * O(n)     - space.
 *
 */
public class CountingInversions {
    private CountingInversions() {}

    /**
     * Returns the number of inversions in the input array
     *
     * @param a the input array
     * @return  the number of inversions.
     */
    public static int countInversions(int[] a) {
        if (a.length == 0) throw new IllegalArgumentException("The array cannot be empty.");
        int[] myArray = new int[a.length];
        int x =  mergeSort(a, 0, a.length - 1, myArray);

        for (int i : myArray) {
            System.out.println("-------: value is i " + i);
        }

        return x;
    }

    private static int mergeSort (int[] a, int low, int high, int[] myArray) {
        // means a single element array.
        if (low >= high) return 0;

        int mid = (low + high)/2;


        return mergeSort (a, low, mid, myArray) +
            mergeSort (a, mid + 1, high, myArray) +
            merge (a, low, mid, high, myArray);
    }

    private static int merge (int[] a, int low, int mid, int high, int[] myArray) {
        int count = 0;
        int[] temp = new int[a.length];

        for (int i = low, pos1 = low, pos2 = mid + 1; i <= high; i++) {
            // note:
            // hb == high also works, but hb >= high is more readable and easy to comprehend.
            // a[lb] <= a[hb]  is correct a[lb] < a[hb] is incorrect: coz, (5, 5) is not counted as an inversion pair.
            if (pos2 > high || pos1 <= mid && a[pos1] <= a[pos2]) {
                temp[i]  = a[pos1++];
            } else {
                /*
                 * mid - i is incorrect since, but (mid - lb) is correct
                 * 5, 6, 7 || 1, 2, 3
                 * In such a case for the first time we come in this else loop,
                 * out i = 3, but out lb is 0.
                 * 5, 6, 7, both are less than 1.
                 * Thus (mid == 3) - (lb == 0) == (3-0) = 3
                 * ie (5, 1) (6, 1) (7, 1)
                 * where as (mid - i) = 0.
                 *
                 * https://bitbucket.org/ameyapatil/all-images/commits/fa0efd6d3ae518fabe9dd4653b47e99bb88fedd0
                 */

                /*
                 * Do we need to sort array as we go ?
                 * Ans: Yes.
                 * Why?
                 * Ans: without sorting : " count = count + (mid - pos1);  " would not give correct result.
                 * Try an example like:
                 * 6, 8, 1, 2, 10, 11, 5, 4
                 * you will realize that without sorting we do not get the righ answer.
                 */

                /*
                 * mug up this simplest example:
                 * 3, 4 || 1, 2
                 */
                count = count + (mid - pos1 + 1);
                myArray[i] = mid - pos1 + 1;
                temp[i]  = a[pos2++];
            }
        }

        for (int i = low;  i <= high; i++) {
            a[i] = temp[i];
        }

        return count;
    }


    /**
     * Cases:
     * ------
     * 1. null
     * 2. empty
     * 3. illegal
     * 4. lot of space
     *
     * 5. single element
     * 6. sorted            : 1 2 3 4
     * 7. reverse sorted.   : 4 3 2 1
     */
    public static void main(String[] args) {
        int[] a1 = {2, 4, 1, 3, 5};
        countInversions(a1);
        //
        //        int[] a2 = {4, 3, 2, 1};
        //        assertEquals(6, countInversions(a2));
        //
        //        int[] a3 = {1, 2, 3, 4};
        //        assertEquals(0, countInversions(a3));
        //
        //        int[] a4 = {3, 3, 3, 3};
        //        assertEquals(0, CountingInversions.countInversions(a4));
    }
}
