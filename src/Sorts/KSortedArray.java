package Sorts;

//import static org.junit.Assert.assertEquals;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Sample example:
 * https://bitbucket.org/ameyapatil/all-images/commits/7028a3b6d4096809d4674354dda832698456a951
 */

/**
 * What is the best way to sort a k-sorted array
 * http://www.geeksforgeeks.org/nearly-sorted-algorithm/
 *
 *
 * Notes and feedback:
 * - quite obvious
 * - quite stupid
 * Only thing it needs is to understand the question.
 * Rest is common sense.
 *
 * The question says the element in the `input arrays` is k away its sorted position.
 * and that +k or it can be -k.
 * ie as explained well on geeks for geeks:
 *
 * "For example, let us consider k is 2, an element at index 7 in the sorted array,
 * can be at indexes "5, 6, 7, 8, 9" in the given array."
 *
 * So why should we make heap size as k + 1 ?
 *
 * Ans: is easy. That is because an element can be present in k + 1 locations.
 * It may sound confusing as "5 6 7 8 9" are 5 places. right ?
 *
 * Ok, so now please consider element at position index 0 and k = 2.
 * That element would be present at 0, 1, and 2 indexes.
 *
 * Thus to capture that element we would need the heap size to be k + 1
 *
 * Reference:
 * http://www.geeksforgeeks.org/nearly-sorted-algorithm/
 * http://stackoverflow.com/questions/21902220/sort-a-nearly-sorted-or-k-sorted-array
 *
 * Complexity:
 * ------------
 * O(n log k) time.
 * - space complexity O(n)  for kSortDontModifyInput
 * - aux complexity O(k)    for kSortMoidifyInput
 *
 * BB:
 * ---
 * 21
 *
 * Interview-Example:
 * ------------------
 * https://bitbucket.org/ameyapatil/all-images/commits/7028a3b6d4096809d4674354dda832698456a951
 *
 */
public final class KSortedArray {

    private KSortedArray() { }

    /**
     * Returns the sorted array provided the input array is k-sorted.
     * If input array is not k-sorted, then results are unpredictable.
     *
     * @param arr   The k-sorted array
     * @param k     the value of k, the deviation of placement.
     * @return      the sorted array
     */
    /*
     * space complexity: O(n)
     */
    public static int[] kSortDontModifyInput(int[] arr, int k) {

        if (k <= 0) {
            throw new IllegalArgumentException("K should be greater than 0.");
        }

        if (k >= arr.length) {
            throw new IllegalArgumentException("K should be less than arr length.");
        }

        int[] n = new int[arr.length];

        final Queue<Integer> queue = new PriorityQueue<Integer>(k + 1);
        for (int  i = 0; i <= k; i++) {
            queue.add(arr[i]);
        }

        int ctr = 0;
        for (int i = k + 1; i < arr.length; i++) {
            n[ctr++] = queue.poll();
            queue.add(arr[i]);
        }

        while (!queue.isEmpty()) {
            n[ctr++] = queue.poll();
        }

        return n;
    }

    /**
     * Sorted array provided the input array is k-sorted.
     * If input array is not k-sorted, then results are unpredictable.
     *
     * @param arr   The k-sorted array
     * @param k     the value of k, the deviation of placement.
     */
    /*
     * aux complexity O(k)
     */
    public static void kSortMoidifyInput(int[] arr, int k) {
        if (k <= 0) {
            throw new IllegalArgumentException("K should be greater than 0.");
        }

        if (k >= arr.length) {
            throw new IllegalArgumentException("K should be less than arr length.");
        }

        final Queue<Integer> queue = new PriorityQueue<Integer>(k + 1);
        for (int  i = 0; i <= k; i++) {
            queue.add(arr[i]);
        }
        int ctr = 0;
        for (int i = k + 1; i < arr.length; i++, ctr++) {
            arr[ctr] = queue.poll();
            queue.add(arr[i]);
        }


        for(; ctr < arr.length; ctr++) {
            arr[ctr] = queue.poll();
        }
    }


    /**
     * sequence: 1 2 3 4 5 6
     *
     * Odd Seq:          1.   3.     5.
     * Even Seq:            2.    4.    6.
     *
     * Now let the even sequence begin before odd sequence.
     *
     * Even seq:   2.    4.   6.
     * Odd  seq:      1.    3.   5.
     *
     * Thus each number is shuffled only 1 position from its original position
     *
     * Thus let input be: 2 1 4 3 6 5
     *
     *
     *
     */
    public static void main(String[] args) {
        int arr[] = {2, 1, 4, 3, 6, 5};

        int[] expected = {1, 2, 3, 4, 5, 6};
        int[] actual = kSortDontModifyInput(arr, 3);
        kSortMoidifyInput(arr, 3);
//        for (int i = 0; i < expected.length; i++) {
//            assertEquals(expected[i], actual[i]);
//            assertEquals(expected[i], arr[i]);
//        }


        //        int arr[] = {2, 6, 3, 12, 56, 8};
        //
        //        int[] expected = {2, 3, 6, 8, 12, 56};
        //        int[] actual = kSortDontModifyInput(arr, 3);
        //        kSortMoidifyInput(arr, 3);
        //        for (int i = 0; i < expected.length; i++) {
        //           assertEquals(expected[i], actual[i]);
        //           assertEquals(expected[i], arr[i]);
        //        }
    }
}



