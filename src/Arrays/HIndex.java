package Arrays;

/**
 * http://www.learn4master.com/interview-questions/leetcode/leetcode-problems-classified-by-company
 * https://www.youtube.com/watch?v=USt5bmYjyZs
 * https://www.programcreek.com/2014/05/leetcode-h-index-java/
 * https://leetcode.com/problems/h-index/
 */
public class HIndex {

    /**
     * citations - [3,0,5,1,5], [ 7 7 7 ], [ 0 0 0]
     */
    public int hIndex(int[] citations) {
        int[] citationsFrequency = new int[citations.length+1];

        for(int c: citations){
            citationsFrequency[Math.min(citations.length,c)]++;
        }

        int k=citations.length;
        for(int s=citationsFrequency[k]; k > s; s += citationsFrequency[k]){
            k--;
        }

        return k;
    }
}
