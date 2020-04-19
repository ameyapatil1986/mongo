package Arrays;

/**
 * http://www.learn4master.com/interview-questions/leetcode/leetcode-problems-classified-by-company
 * https://www.youtube.com/watch?v=USt5bmYjyZs
 * https://www.programcreek.com/2014/05/leetcode-h-index-java/
 * https://leetcode.com/problems/h-index/
 */
public class HIndex {

    /**
     * citations - [0 1 2 3 3 3], [0 0 0], [7 7 7]
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
