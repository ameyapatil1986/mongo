package Arrays;

/**
 *
 * References:
 * http://www.geeksforgeeks.org/median-of-two-sorted-arrays/
 * http://stackoverflow.com/questions/21209994/is-this-the-right-use-of-primitive-wrapper-class
 * http://codereview.stackexchange.com/questions/39560/find-the-median-of-sorted-equal-sized-arrays
 * http://stackoverflow.com/questions/1570416/when-to-use-wrapper-class-and-primitive-type
 *
 *
 * Diagram:
 * https://bitbucket.org/ameyapatil/all-images/commits/4da559eadb9c360cba2e4e22b666dbd46c77c295
 *
 * Good example:
 * [1, 3, 5, 7, 9] and  [2, 4, 6, 8, 10]
 *
 * Complexity:
 * O (logn)
 *
 * BB: 19
 *
 * @author SERVICE-NOW\ameya.patil
 */
public class MedianOfTwoSortedArrays {

    private MedianOfTwoSortedArrays() {}


    /**
     * Given two sorted arrays of same length it returns the median.
     * If arrays are not of equal length throws the IllegalArgumentException.
     * If arrays are not sorted the results can be unpredictable.
     *
     * @param a1	the first sorted array
     * @param a2	the second sorted array
     * @return		the median of the two integers or null if no such median is found due to illegal input.
     */
    public static Double median (int[] a1, int[] a2) {
        if (a1.length != a2.length) throw new IllegalArgumentException("The argument thrown is illegal");

        int lb = 0;
        int hb = a1.length - 1;

        return getMedian(a1, a2, 0, hb) ;
    }


    /**
     *
     * @param firstArr
     * @param secondArr
     * @param lb
     * @param hb
     * @return
     */
    private static double getMedian(int[] firstArr, int[] secondArr, int lb, int hb) {
        if (lb > hb) {
            // Example:1, step 3
            return getMedian(secondArr, firstArr, 0, secondArr.length - 1);
        }

        int i = (lb + hb)/2;
	    /*
	     * consider a
	     * 1. first array    [11, 12, 13, 14]
	     * 2. second array   [15, 16, 17, 18]
	     *
	     * here the 'i' should go from left to right
	     * here the 'j' should go from right to left.
	     */
        int j = secondArr.length - 1 - i;

        //
        //      TODO: post checkpoint.
        //
        //	    /*
        //	     * why not "firstArr[i] >= secondArr[j]"
        //	     * to get the answer consider a simple example:
        //	     * firstArr   [10, 20]
        //	     * secondArr  [10, 20]
        //	     * Now you will get your answer.
        //	     */
        if (firstArr[i] > secondArr[j]) {
            if ((j < secondArr.length - 1) && firstArr[i] > secondArr[j + 1]) {
	            /*
                 * Example: 1, step 2
                 *
                 * [40,  60]  [30,  50]
                 * i-1    i    j    j+1
                 */
                // basically firstArr[i] > secondArr[j] && firstArr[i] > secondArr[j + 1]
                return getMedian(firstArr, secondArr, lb,  i - 1);
            } else {
    	        /*
    	         *   Example:1, step 5
    	         *
     	         *   [30, 50]   [40, 60]
    	         *   i-1,  i      j, j+1
    	         */
                if (i == 0 || firstArr[i - 1] <= secondArr[j]) {
                    return (firstArr[i] + secondArr[j]) / 2.0;
                } else {
    	            /*
    	             *  Example:2
    	             *  [45, 50]   [40, 60]
    	             *
    	             *  hard to come up with example 2.
    	             *
    	             */
                    return (firstArr[i - 1] + firstArr[i]) / 2.0;
                }
            }
        } else {
	        /*
	         * Example: 1, step 1
	         *
             * [40,  60]  [30,  50]
             *  i                j
             *
             * Example: 1, step 4.
             * [30, 50]   [40, 60]
             *  i              j
             *
             */
            // basically firstArr[i] <= secondArr[j]
            return getMedian(firstArr, secondArr, i + 1, hb);
        }
    }
}
