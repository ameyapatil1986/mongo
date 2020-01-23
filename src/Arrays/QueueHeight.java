package Arrays;

import java.util.*;

/**
 * https://www.programcreek.com/2015/03/leetcode-queue-reconstruction-by-height-java/
 *
 * Suppose you have a random list of people standing in a queue.
 * Each person is described by a pair of integers (h, k), where h is the height of the
 * person and k is the number of people in front of this person who have a height greater
 * than or equal to h. Write an algorithm to reconstruct the queue.
 *
 *
 * Input:
 * [[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]
 *
 * Intermediary step (descending sort, however, if height is equal, ascending sort by number of people)
 * [7, 0] [7, 1] [6, 1] [5, 0] [5, 2] [4, 4]
 *
 *
 * Output:
 * [[5,0], [7,0], [5,2], [6,1], [4,4], [7,1]]
 *
 */
public class QueueHeight {

    /**
     * 1) sort by height
     * 2) group all people with "same number of people in front" together.
     */
    public int[][] reconstructQueue(int[][] people) {

        // sorted descending by height.
        Arrays.sort(people, (a1, a2) -> {
            if(a1[0] != a2[0]) {
                return a2[0] - a1[0];
            } else {
                // if height is the same sort in ascending order of stuff in front of me.
                return a1[1] - a2[1];
            }
        });

        List<int[]> list = new ArrayList<int[]>();

        /**
         * Inserts the specified element at the specified position in this list
         * (optional operation).  Shifts the element currently at that position
         * (if any) and any subsequent elements to the right (adds one to their indices).
         */
        for(int i = 0; i < people.length; i++) {
            int[] arr = people[i];
            // NOTE: this is step.
            list.add(arr[1], arr);
        }

        int[][] result = new int[people.length][];
        for(int i = 0; i < people.length; i++){
            result[i] = list.get(i);
        }

        return result;
    }

}
