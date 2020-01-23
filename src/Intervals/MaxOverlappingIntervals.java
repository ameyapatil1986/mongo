package Intervals;

//import static org.junit.Assert.assertEquals;


final class MaxInterval {

    private final int startTime;
    private final int endTime;
    private final int numberOfOverlaps;

    public MaxInterval(int startTime, int endTime, int numberOfOverlaps) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.numberOfOverlaps = numberOfOverlaps;
    }

    public int getStartTime() {
        return startTime;
    }

    public int getEndTime() {
        return endTime;
    }

    public int getNumberOfOverlap() {
        return numberOfOverlaps;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + endTime;
        result = prime * result + numberOfOverlaps;
        result = prime * result + startTime;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        MaxInterval other = (MaxInterval) obj;
        if (endTime != other.endTime)
            return false;
        if (numberOfOverlaps != other.numberOfOverlaps)
            return false;
        if (startTime != other.startTime)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Interval: [" + startTime + "," +  endTime + "], and interval depth is: " + numberOfOverlaps;
    }
}


/**
 *
 * FB:
 * https://www.careercup.com/question?id=6277103186083840
 *
 * Find any range, with MAX interval overlap.
 *
 * NOTE: if all ranges are asked first calculate the 'value of max overlap' and then pass it as parameter to maxOverlap function.
 * Any range where intervalCount == 'value of max overlap', go ahead and print the range.
 *
 * Complexity: O(nlogn + n), effectively : 0(nlogn), where 'n' is number of intervals.
 *
 * nlogn: comes as start and end times need to be sorted in 2 different arrays.
 * n : comes as we sweep through these 2 different arrays in the max value function.
 *
 * Design:
 * We dont have a constructor as we dont see any of the state being shared across function.
 * A simple static function is used as no state of an object is used in any computations.
 *
 * Reference: http://stackoverflow.com/questions/8969823/how-to-find-the-maximal-number-of-these-intervals-that-have-a-common-point
 *
 * Quadratic time solution, which should be avoided should be done as follows:
 *
 * [10, 30] [20, 40] [25, 50]
 *
 * for (  all starts ) {
 *   for ( all ends ) {
 *
 *      if (start < end) {
 *          hash.put(end, count++); // number of overlaps till that end.
 *      }
 *   }
 * }
 *
 * NOTE:
 * http://heartfire.cc/wp/merge-intervals/ suggests a shortcut.
 * It eliminates the need for counter, keeping track of start and finish.
 * This shortcut would fail for a range like: [10, 30] [20, 40] [25, 50].
 * Thus we need counter to indicate where what ended.
 *
 * @author SERVICE-NOW\ameya.patil
 */

/**
 * Following the prototype similar to
 * : Robert Lafore, javadsa: declares the class without main on the top of class with main.
 * : derek banas BST on youtube.
 * : http://www.java-tips.org/java-se-tips/java.lang/binary-search-tree-implementation-in-java.html
 *
 * Declaring a class interval in the same java file with no modifiers.
 *
 * A class is declared following prototype shown on sun oracle websites:
 * http://docs.oracle.com/javase/tutorial/java/javaOO/classvars.html
 *
 * Complexity
 * O(nlogn)
 *
 * BB:
 * 1
 *
 * Sample example of input
 *
 *      |--------|    |--------|
 *  |--------------------------------|
 *  10  20      30    40      50    60
 *
 *  [10, 20, 40]  [30, 40, 60]
 *
 *
 */
public final class MaxOverlappingIntervals {

    private MaxOverlappingIntervals() {};

    /**
     * Returns the time-interval as well as span of max-overlap.
     *
     * example: if intervals are [0-30][10-20][14-30]
     * - then the return value should be 14-20
     * But note the the span returned need not correspond to actual interval.
     *
     * Note that this code needs input start-time and end-time to be sorted.
     * else results would be unpredictable
     *
     * @param startTime - the start time of all intervals sorted.
     * @param endTime   - the end time intervals that are sorted.
     */
    public static MaxInterval maxOverlap(int[] startTime, int[] endTime) {

        if (startTime.length !=  endTime.length) {
            throw new IllegalArgumentException();
        }

        int i = 0;  // the counter for startTime
        int j = 0;  // the counter for endTime
        int intervalCount = 0;
        int max = Integer.MIN_VALUE;

        int intervalStart = 0;
        int intervalEnd = 0;

        while (i < startTime.length) {
            /*
             * [10 - 8 ]
             *      [12 - 6]
             *
             * This is an illegal input.
             */
            if (/*intervalCount Or */ i < j) {
                throw new IllegalArgumentException();
            }

            if (startTime[i] < endTime[j]) {
                // the very first interval.
                intervalCount++;
                if (intervalCount >= max) {
                    max = intervalCount;
                    intervalStart = startTime[i];
                    intervalEnd = endTime[j];
                }
                i++;
            } else if (startTime[i] > endTime[j]) {
                /*           [22 - 23]
                 *     [15 - 25]
                 * [10 - 20]
                 *
                 * We come in this section when (end is 20) and (start is 22)
                 *
                 */
                intervalCount--;
                j++;
            } else {
                /*
                 * happens in case of no overlap.
                 *             [25 - 35]
                 *         [20 -30]
                 * [10 - 20]
                 *
                 */
                i++;
                j++;
            }
        }


        return new MaxInterval(intervalStart, intervalEnd, max);
    }

    /**
     * Test cases:
     * 1. No interval
     * 2. Single interval
     * 3. Disjoint.
     *    3.1 Non Adjoint. [10 - 20] [30 - 40]
     *    3.2 Adjoint      [10 - 20] [20 - 40]
     * 4. Overlapping
     *    4.1 Partial Overlap  [10 - 30][20 - 50]
     *    4.2 Common Start.    [10-15][10-20][10-30]
     *    4.3 Common end.      [10-20][15-20][18-20]
     *    4.4 Nested intervals: [10 - 40] [20 - 30]
     *    4.5 Repeated: [10 - 40] [10 - 40]
     * @param args
     */
//    public static void main(String args[]) {
//
//        /**
//         * Test case 1 : No interval
//         */
//        int intervalStart1[] = {};
//        int intervalEnd1[] = {};
//        assertEquals(new MaxInterval(0, 0, Integer.MIN_VALUE),  maxOverlap(intervalStart1, intervalEnd1));
//
//        /**
//         * Test case 2: Single interval
//         */
//        int intervalStart2[] = {10};
//        int intervalEnd2[] = {40};
//        assertEquals(new MaxInterval(10, 40, 1),  maxOverlap(intervalStart2, intervalEnd2));
//
//        /**
//         * Test Case 3.1: Non Adjoint. [10 - 20] [30 - 40]
//         */
//        int intervalStart31[] = {10, 30, 50};
//        int intervalEnd31[] = {20, 40, 60};
//        assertEquals(new MaxInterval(50, 60, 1),  maxOverlap(intervalStart31, intervalEnd31));
//
//        /**
//         * Test Case 3.2: Non Adjoint but touching boundaries. [10 - 20] [20 - 40]
//         */
//        int intervalStart32[] = {10, 20, 40};
//        int intervalEnd32[] = {20, 40, 60};
//        assertEquals(new MaxInterval(10, 20, 1),  maxOverlap(intervalStart32, intervalEnd32));
//
//        /**
//         * Test Case 4.1 Partial Overlap [10 - 30][20 - 50]
//         */
//        int intervalStart41[] = {10, 20, 30, 40};
//        int intervalEnd41[] = {30, 40, 50, 60};
//        assertEquals(new MaxInterval(20, 30, 2), maxOverlap(intervalStart41, intervalEnd41));
//
//
//        /**
//         * Test Case 4.2 Common Start. [10-15][10-20][10-30]
//         */
//        int intervalStart42[] = {10, 10, 30};
//        int intervalEnd42[] = {20, 30, 40};
//        assertEquals(new MaxInterval(10, 20, 2), maxOverlap(intervalStart42, intervalEnd42));
//
//        /**
//         * Test Case 4.3 Common end. [10-20][15-20][18-20]
//         */
//        int intervalStart43[] = {10, 20, 30};
//        int intervalEnd43[] = {60, 60, 60};
//        assertEquals(new MaxInterval(30, 60, 3), maxOverlap(intervalStart43, intervalEnd43));
//
//        /**
//         * Test Case 4.4 Nested intervals:
//         * Intended intervals : [10-40][20-25][50-60]
//         * Arrays after sorting turn out to be as listed below and passed to maxOverlap Function.
//         */
//        int intervalStart44[] = {10, 20, 50};
//        int intervalEnd44[] = {25, 40, 60};
//        assertEquals(new MaxInterval(20, 25, 2), maxOverlap(intervalStart44, intervalEnd44));
//
//        /**
//         * Test Case 4.5.1 First interval Repeated: [10 - 40] [10 - 40]
//         */
//        int intervalStart451[] = {10, 10, 10, 30};
//        int intervalEnd451[] = {20, 20, 20, 40};
//        assertEquals(new MaxInterval(10, 20, 3), maxOverlap(intervalStart451, intervalEnd451));
//
//        /**
//         * 4.5.2  Middle interval Repeated: [10 - 40] [10 - 40]
//         */
//        int intervalStart452[] = {10, 30, 30, 30, 70};
//        int intervalEnd452[] = {20, 50, 50, 50, 80};
//        assertEquals(new MaxInterval(30, 50, 3), maxOverlap(intervalStart452, intervalEnd452));
//
//        /**
//         * 4.5.3  Last interval Repeated: [10 - 40] [10 - 40]
//         */
//        int intervalStart453[] = {10, 10, 30, 30, 30};
//        int intervalEnd453[] = {20, 20, 40, 40, 40};
//        assertEquals(new MaxInterval(30, 40, 3), maxOverlap(intervalStart453, intervalEnd453));
//    }

}
