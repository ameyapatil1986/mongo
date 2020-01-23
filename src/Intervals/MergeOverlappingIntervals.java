package Intervals;

import java.util.*;

/**
 * Given all intervals, merge overlapping intervals. The meaning of the question
 * is: Given discrete intervals: [0-10] [5-15] merge them for output: [0-15]
 *
 * References: http://heartfire.cc/wp/merge-intervals/
 *
 * Asked by ebay.
 *
 * NOTE: MaxOverlappingIntervals contains Interval class, place it in this class
 * using same conventions in the interview.
 *
 * BB:
 * ---
 *
 *
 * Complexity:
 * -----------
 * O (n logn ) - time, where n is number of intervals.
 * O (n) - space.  where 'n' is the number of intervals.
 *
 */
public class MergeOverlappingIntervals {

    public static List<Interval> mergeOverlappingIntervals(ArrayList<Interval> intervalList) {
        final List<Interval> mergedIntervalList = new ArrayList<Interval>();

        if (intervalList.size() == 0) {
            // return an empty list;
            return mergedIntervalList;
        }

        /**
         * Will cause O(nlogn) time complexity
         * Use sort either using Collections.sort
         * or standard merged sort.
         * Eventually I will code up modified merged sort.
         */
        intervalList.sort(Comparator.comparing(Interval::getStart));


        /**
         * Needed to declare element 0, outside to care care of disjoint sets. [10-20][30-40]
         * Think of it as a=0; b=1; in case of fibonacci for loops.
         */

        Interval prevInterval = intervalList.get(0);
        int prevIntervalStart = prevInterval.getStart();
        int prevIntervalEnd = prevInterval.getEnd();

        for (int i = 1;  i < intervalList.size(); i++) {
            Interval currentInterval = intervalList.get(1);

            if (currentInterval.getStart() <= prevIntervalEnd) {
                prevIntervalEnd = Math.max(prevInterval.getEnd(), currentInterval.getEnd());
            } else {
                mergedIntervalList.add(new Interval(prevIntervalStart, prevIntervalEnd));
                prevIntervalStart = currentInterval.getStart();
                prevIntervalEnd = currentInterval.getEnd();
            }
        }
        mergedIntervalList.add(new Interval(prevIntervalStart, prevIntervalEnd));
        return mergedIntervalList;
    }

    //    /**
    //     * Test cases:
    //     * 1. No interval
    //     * 2. Single interval
    //     * 3. Disjoint.
    //     *    3.1 Non Adjoint. [10 - 20] [30 - 40]
    //     *    3.2 Adjoint      [10 - 20] [20 - 40]
    //     * 4. Overlapping
    //     *    4.1 Partial Overlap  [10 - 30][20 - 50]
    //     *    4.2 Common Start.    [10-15][10-20][10-30]
    //     *    4.3 Common end.      [10-20][15-20][18-20]
    //     *    4.4 Nested intervals: [10 - 40] [20 - 30]
    //     *    4.5 Repeated: [10 - 40] [10 - 40]
    //     *        4.5.1 Repeated at start
    //     *        4.5.2 Repeated at middle
    //     *        4.5.3 Repeated at end.
    //     * @param args
    //     */

    /*
     * https://bitbucket.org/ameyapatil/all-images/src/5d79710f6ed925a330199cdc1a56af76613cb12a/6intervalUT.JPG?at=master
     */
//    public static void main(String args[]) {
//
//        /**
//         * Test case 1 : No interval
//         */
//        ArrayList<Interval> intervalList1 = new ArrayList<Interval>();
//        ArrayList<Interval> expectedMergedArrayList1 = new ArrayList<Interval>();
//        List<Interval> mergedList1 = mergeOverlappingIntervals(intervalList1);
//        System.out.println("Test case 1 " + mergedList1.equals(expectedMergedArrayList1));
//
//        /**
//         * Test case 2 : Single interval
//         */
//        ArrayList<Interval> intervalList2 = new ArrayList<Interval>();
//        ArrayList<Interval> expectedMergedArrayList2 = new ArrayList<Interval>();
//        intervalList2.add(new Interval(10, 40));
//        expectedMergedArrayList2.add(new Interval(10, 40));
//        List<Interval> mergedList2 = mergeOverlappingIntervals(intervalList2);
//        System.out.println("Test case 2 " + mergedList2.equals(expectedMergedArrayList2));
//
//        /**
//         * Test Case 3.1: Non Adjoint. [10 - 20] [30 - 40]
//         */
//        ArrayList<Interval> intervalList31 = new ArrayList<Interval>();
//        ArrayList<Interval> expectedMergedArrayList31 = new ArrayList<Interval>();
//        int interval31[] = {10, 20, 30, 40, 50, 60};
//        for (int i = 0; i <  interval31.length - 1; i=i+2) {
//            intervalList31.add(new Interval(interval31[i], interval31[i+1]));
//            expectedMergedArrayList31.add(new Interval(interval31[i], interval31[i+1]));
//        }
//        List<Interval> mergedList31 = mergeOverlappingIntervals(intervalList31);
//        System.out.println("Test case 3.1 " +mergedList31.equals(expectedMergedArrayList31));
//
//        /**
//         * Test Case 3.2: Non Adjoint but touching boundaries. [10 - 20] [20 - 40]
//         */
//        ArrayList<Interval> intervalList32 = new ArrayList<Interval>();
//        ArrayList<Interval> expectedMergedArrayList32 = new ArrayList<Interval>();
//        expectedMergedArrayList32.add(new Interval(10, 60));
//        int interval32[] = {10, 20, 20, 40, 40, 60};
//        for (int i = 0; i <  interval32.length - 1; i=i+2) {
//            intervalList32.add(new Interval(interval32[i], interval32[i+1]));
//        }
//        List<Interval> mergedList32 = mergeOverlappingIntervals(intervalList32);
//        System.out.println("Test case 3.2 " + mergedList32.equals(expectedMergedArrayList32));
//
//        /**
//         * Test Case 4.1 Partial Overlap [10 - 30][20 - 50]
//         */
//        ArrayList<Interval> intervalList41 = new ArrayList<Interval>();
//        int interval41[] = {10, 30, 20, 40, 30, 50, 40, 60};
//        for (int i = 0; i <  interval41.length - 1; i=i+2) {
//            intervalList41.add(new Interval(interval41[i], interval41[i+1]));
//        }
//        ArrayList<Interval> expectedIntervalList41 = new ArrayList<Interval>();
//        expectedIntervalList41.add(new Interval(10, 60));
//        List<Interval> mergedList41 = mergeOverlappingIntervals(intervalList41);
//        System.out.println("Test case 4.1 " + mergedList41.equals(expectedIntervalList41));
//
//        /**
//         * Test Case 4.2 Common Start. [10-15][10-20][10-30]
//         */
//        ArrayList<Interval> intervalList42 = new ArrayList<Interval>();
//        ArrayList<Interval> expectedMergedArrayList42 = new ArrayList<Interval>();
//        int interval42[] = {10, 20, 10, 30, 10, 40};
//        for (int i = 0; i <  interval42.length - 1; i=i+2) {
//            intervalList42.add(new Interval(interval42[i], interval42[i+1]));
//        }
//        expectedMergedArrayList42.add(new Interval(10, 40));
//        List<Interval> mergedList42 = mergeOverlappingIntervals(intervalList42);
//        System.out.println("Test case 4.2 " +mergedList42.equals(expectedMergedArrayList42));
//
//        /**
//         * Test Case 4.3 Common end. [10-20][15-20][18-20]
//         */
//        ArrayList<Interval> intervalList43 = new ArrayList<Interval>();
//        ArrayList<Interval> expectedMergedArrayList43 = new ArrayList<Interval>();
//        int interval43[] = {10, 60, 20, 60, 30, 60};
//        for (int i = 0; i <  interval43.length - 1; i=i+2) {
//            intervalList43.add(new Interval(interval43[i], interval43[i+1]));
//        }
//        expectedMergedArrayList43.add(new Interval(10, 60));
//        List<Interval> mergedList43 = mergeOverlappingIntervals(intervalList43);
//        System.out.println("Test case 4.3 " + mergedList43.equals(expectedMergedArrayList43));
//
//        /**
//         * 4.4 Nested intervals: [10 - 40] [20 - 30]
//         */
//        ArrayList<Interval> intervalList44 = new ArrayList<Interval>();
//        ArrayList<Interval> expectedMergedArrayList44 = new ArrayList<Interval>();
//        int interval44[] = {10, 40, 20, 25, 50, 60};
//        for (int i = 0; i <  interval44.length - 1; i=i+2) {
//            intervalList44.add(new Interval(interval44[i], interval44[i+1]));
//        }
//        expectedMergedArrayList44.add(new Interval(10, 40));
//        expectedMergedArrayList44.add(new Interval(50, 60));
//        List<Interval> mergedList44 = mergeOverlappingIntervals(intervalList44);
//        System.out.println("Test case 4.4 " + mergedList44.equals(expectedMergedArrayList44));
//
//        /**
//         * 4.5.1 Repeated At Start
//         */
//        ArrayList<Interval> intervalList451 = new ArrayList<Interval>();
//        ArrayList<Interval> expectedMergedArrayList451 = new ArrayList<Interval>();
//        int interval451[] = {10, 20, 10, 20, 10, 20, 30, 40};
//        for (int i = 0; i <  interval451.length - 1; i=i+2) {
//            intervalList451.add(new Interval(interval451[i], interval451[i+1]));
//        }
//        expectedMergedArrayList451.add(new Interval(10, 20));
//        expectedMergedArrayList451.add(new Interval(30, 40));
//        List<Interval> mergedList451 = mergeOverlappingIntervals(intervalList451);
//
//        System.out.println("Test case 4.5.1 " +mergedList451.equals(expectedMergedArrayList451));
//
//        /**
//         * 4.5.2 Repeated At middle
//         */
//        ArrayList<Interval> intervalList452 = new ArrayList<Interval>();
//        ArrayList<Interval> expectedMergedArrayList452 = new ArrayList<Interval>();
//        int interval452[] = {5, 7, 10, 20, 10, 20, 10, 20, 30, 40};
//        for (int i = 0; i <  interval452.length - 1; i=i+2) {
//            intervalList452.add(new Interval(interval452[i], interval452[i+1]));
//        }
//        expectedMergedArrayList452.add(new Interval(5, 7));
//        expectedMergedArrayList452.add(new Interval(10, 20));
//        expectedMergedArrayList452.add(new Interval(30, 40));
//        List<Interval> mergedList452 = mergeOverlappingIntervals(intervalList452);
//
//        System.out.println("Test case 4.5.2 " + mergedList452.equals(expectedMergedArrayList452));
//
//        /**
//         * 4.5.3 Repeated At End
//         */
//        ArrayList<Interval> intervalList453 = new ArrayList<Interval>();
//        ArrayList<Interval> expectedMergedArrayList453 = new ArrayList<Interval>();
//        int interval453[] = {5, 7, 10, 20, 10, 20, 10, 20, 30, 40};
//        for (int i = 0; i <  interval453.length - 1; i=i+2) {
//            intervalList453.add(new Interval(interval453[i], interval453[i+1]));
//        }
//        expectedMergedArrayList453.add(new Interval(5, 7));
//        expectedMergedArrayList453.add(new Interval(10, 20));
//        expectedMergedArrayList453.add(new Interval(30, 40));
//        List<Interval> mergedList453 = mergeOverlappingIntervals(intervalList453);
//
//        System.out.println("Test case 4.5.3 " + mergedList453.equals(expectedMergedArrayList453));
//    }
}
