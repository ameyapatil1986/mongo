package Arrays.sum;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * TODO: Add the O(nlogn) method.
 *
 * Testing if 2 numbers (pair) sum upto a 3rd.
 *
 * Complexity: O(n)
 *
 * Reference: Impact interview.
 *
 * Software Design: chosen static function simply coz interviewer would be more
 * familiar to static function, and many other places static functions were
 * used.
 *
 * @author SERVICE-NOW\ameya.patil
 */
public class PairSum {

    // https://leetcode.com/problems/two-sum-iii-data-structure-design/
    public void add(int x) {
        Set<Integer> s = new HashSet<Integer>();
        s.add(x);
    }


    /**
     * printPairs : function appropriately describes it's intent
     *
     * @param input : good descriptive parameter name
     * @param x
     */
    public static void printPairs (int input[], int x) {
        /**
         * A good practice to include null pointer checks.
         * As per joshua bloch
         */
        if (input == null) {
            throw new NullPointerException();
        }

        Set<Integer> s = new HashSet<Integer>();
        for (int i : input) {
            int diff = x - i; // calculate difference only once.
            if (s.contains(i)) {
                System.out.println("The 2 values are: " + diff + " " + i);
            } else {
                s.add(diff);
            }
        }
    }


    public static void printPairsWithoutAuxStorage (int input[], int x) {

        /**
         * A good practice to include null pointer checks.
         */
        if (input == null) {
            throw new NullPointerException();
        }

        /**
         * Run a merge sort here.
         * This merge sort computes, 0 (n log n)
         * For this example, we input the sorted array.
         */
        int low = 0;
        int high = input.length -1;

        while (low < high) {
            int sum = input[low] + input[high];
            if (sum < x) {
                low ++;
            } else if (sum > x) {
                high --;
            } else {
                System.out.println("The 2 values are: " + input[low] + " " + input[high]);
                low ++;
                high --;
            }
        }
    }


    public static void main(String args[]) {
        int a[] = {-5, -4, -3, -2, -1, 0, 1, 2, 3, 4, 5};
        printPairs (a, 0);
        System.out.println("======================================================");
        printPairsWithoutAuxStorage(a, 0);

    }
}
