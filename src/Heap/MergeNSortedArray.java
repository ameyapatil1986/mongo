package Heap;


import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Complexity:
 * Time : O (m  n-log-n ) :
 * n is the number of lists.
 * m is the max size of list.
 *
 * Asked at FB:
 * https://www.careercup.com/question?id=5768186823704576
 *
 * Space:
 * Aux O(n), where n is the wrapper classes containing the array
 *
 * References:
 * http://www.dsalgo.com/2013/02/merge-n-sorted-arrays.html
 * http://stackoverflow.com/questions/17139048/how-to-improve-on-a-case-when-private-function-needs-to-call-a-public-function
 * http://stackoverflow.com/questions/23318537/complexity-of-calculating-matrix-with-uneven-row-length
 * http://codereview.stackexchange.com/questions/56699/merge-n-sorted-list
 *
 * BB:
 * 3
 */
public final class MergeNSortedArray {

    private final PriorityQueue<ArrayContainer> heap;
    private int totalLength;


    /**
     * All rows of the matrix are expected to be sorted.
     * Else results are unpredictable.
     *
     * @param arrList
     */
    public MergeNSortedArray(List<int[]> arrList) {
        if (arrList == null) {
            throw new NullPointerException("The input matrix is null");
        }
        heap = new PriorityQueue<ArrayContainer>();
        addAll(arrList);
    }

    private void addAll(List<int[]> matrix) {
        for (int[] a : matrix) {
            if (a.length > 0) {
                totalLength += a.length;
                heap.add(new ArrayContainer(a));
            }
        }
    }

    /**
     * Keeps a counter, to keep track of current element to be considered to
     * sort.
     *
     * Any element before the currentIndex, has already been placed in the sorted array.
     *
     */
    private static class ArrayContainer implements Comparable<ArrayContainer> {
        private int index;
        private int[] array;

        public ArrayContainer(int[] array) {
            this.array = array;
        }

        public boolean isFull() {
            return index == array.length;
        }

        public int peek() {
            return array[index];
        }

        public int fetchAndIncrement() {
            return array[index++];
        }

        @Override
        public int compareTo(ArrayContainer o) {
            return peek() - o.peek();
        }
    }

    /*
     * aux: O(logk)
     * space: O(n*m)
     */
    public int[] mergeNArrays() {
        int[] result = new int[totalLength];
        int index = 0;

        while (!heap.isEmpty()) {
            final ArrayContainer arrayContainer = heap.poll();

            result[index++] = arrayContainer.fetchAndIncrement();

            if (!arrayContainer.isFull()) {
                heap.add(arrayContainer);
            }
        }
        return result;
    }

//    public static void main(String[] args) {
//        int[] arr1 = {2,4,6,8,9,12,14,16};
//        int[] arr2 = {3,6,7,9,22,25,28};
//        int[] arr3 = {2,5,7,8,10,11,16};
//        int[] arr4 = {4,8,23,26,28};
//        int[] arr5 = {};
//
//        MergeNSortedArray msa = new MergeNSortedArray(Arrays.asList(arr1, arr2, arr3, arr4, arr5));
//
//        int[] expected = {2, 2, 3, 4, 4, 5, 6, 6, 7, 7, 8, 8, 8, 9, 9, 10, 11, 12, 14, 16, 16, 22, 23, 25, 26, 28, 28};
//
//        assertArrayEquals(expected, msa.mergeNArrays());
//    }
}
