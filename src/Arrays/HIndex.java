package Arrays;

/**
 * http://www.learn4master.com/interview-questions/leetcode/leetcode-problems-classified-by-company
 * https://www.youtube.com/watch?v=USt5bmYjyZs
 * https://www.programcreek.com/2014/05/leetcode-h-index-java/
 * https://leetcode.com/problems/h-index/
 */
public class HIndex {

    /**
     * Note for Tanvi:
     * [1, 2, 3] : there are 2 papers with marks greater than 2. thus H-index = 2
     *
     * After populating citationsFrequency looks like:
     * citationsFrequency : [0, 1, 1, 1]
     *
     * s = 1, and k = 3 : means there is 1 paper with score 3 or more.
     * s = 2, and k = 2 : means there are 2 papers with score 2 or more.
     *
     * Now, k decrements inside for loop and becomes 1.
     * s <= k is false, since 2 <= 1 is false.
     *
     * We break out of for loop, and need to increment k by 1.
     */


    /**
     * citations - [0 1 2 3 3 3], [0 0 0], [7 7 7], [1 1 1], [1 2 3]
     */
    public int hIndex(int[] citations) {
        int[] citationsFrequency = new int[citations.length+1];

        for(int c: citations){
            citationsFrequency[Math.min(citationsFrequency.length - 1,c)]++;
        }

        // [0 1 2 3 3 3], [0 0 0], [7 7 7]
        int s = 0;
        int k;
        for (k = citationsFrequency.length - 1 ; s <= k; k--) {
            s += citationsFrequency[k];
        }

        return k + 1;
    }
}
