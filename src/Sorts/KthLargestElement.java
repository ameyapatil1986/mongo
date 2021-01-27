package Sorts;

/**
 *
 * https://leetcode.com/problems/kth-largest-element-in-an-array/
 *
 *
 * http://stackoverflow.com/questions/251781/how-to-find-the-kth-largest-element-in-an-unsorted-array-of-length-n-in-on
 * http://www.cs.yale.edu/homes/aspnes/pinewiki/QuickSelect.html
 * https://www.youtube.com/watch?v=pdmZpAJ2uBM
 *
 * Diagram:
 * https://bitbucket.org/ameyapatil/all-images/src/cb20761cec85ff397da6556234ed1ad87a3696bb/QuickSort.JPG?at=master
 *
 * Complexity:
 * -----------
 * Right. The worst time runtime complexity of quick select is known to be O(n^2). Average is O(n), though.
 * http://blog.teamleadnet.com/2012/07/quick-select-algorithm-find-kth-element.html
 *
 * Quick sort worst case:
 * ----------------------
 * http://www.geeksforgeeks.org/when-does-the-worst-case-of-quicksort-occur/
 *
 *
 * BB:
 * 7
 *
 */
public final class KthLargestElement {

    private KthLargestElement() {};


    // do a quick sort here.
    private static int partition(int[] a, int lb, int hb) {
        int x = a[lb];
        int down = lb;
        int up = hb;

        // example: [ 10, 6, 60, 7, 70, 8, 80, 9, 10 ]
        while (down < up) {

            // elements equal to x, would eventually be on my left.
            while (a[down] <= x && down < up) {
                down++;
            }

            while (a[up] > x) {
                up--;
            }

            if (down < up) {
                int temp = a[down];
                a[down] = a[up];
                a[up] = temp;
            }
        }

        /*
         * why return a[up] rather than a[down] ?
         * eg: [10, 1, 2, 30]. you will get your answer.
         */
        a[lb] = a[up];
        a[up] = x;

        return up;
    }


    private static int quickSelect(int[] a, int lowerBound, int upperBound, int k) {
        /*
         * this base case is never encountered because of 'k'
         * the condition (k < pivot) makes sure we always find the right answer without hitting the base case.
         */
        // if (lowerBound > upperBound) return foo


        int pivot = partition(a, lowerBound, upperBound);

        if (pivot == k) return a[k];

        if (k < pivot) {
            return quickSelect(a, lowerBound, pivot - 1, k);
        } else {
            return quickSelect(a, pivot + 1, upperBound, k);
        }
    }


    public static int getKthLargest(int[] a, int k) {
        if (k <= 0 || k > a.length) {
            // we assume when k=1, the index to be returned is a[0]
            // thus k should be from [1 .. arrlength]
            throw new IllegalArgumentException("The array length is greater tha foo.");
        }
        return quickSelect(a, 0, a.length - 1, k - 1);
    }



    public static void main(String[] args) {
        int[] a = {1, 2, 3, 4, 5};
        for (int i = 1; i <= a.length; i++) {
            System.out.print(KthLargestElement.getKthLargest(a, i) + ":");
        }

        System.out.println();

        int[] aRev = {5, 4, 3, 2, 1};
        for (int i = 1; i <= aRev.length; i++) {
            System.out.print(KthLargestElement.getKthLargest(aRev, i) + ":");
        }

        System.out.println();

        int[] aMix = {1, 4, 3, 5, 2};
        for (int i = 1; i <= aMix.length; i++) {
            System.out.print(KthLargestElement.getKthLargest(aMix, i) + ":");
        }
    }
}
