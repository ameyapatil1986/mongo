package Heap;


import java.util.PriorityQueue;
import java.util.*;


/**
 *
 * https://medium.com/algorithm-and-datastructure/last-stone-weight-dbc948aab8a
 *
 * Input: [2,7,4,1,8,1]
 * Output: 1
 * Explanation:
 * We combine 7 and 8 to get 1 so the array converts to [2,4,1,1,1] then,
 * we combine 2 and 4 to get 2 so the array converts to [2,1,1,1] then,
 * we combine 2 and 1 to get 1 so the array converts to [1,1,1] then,
 * we combine 1 and 1 to get 0 so the array converts to [1] then that's the value of last stone.
 *
 * Complexity:
 * -----------
 * O (n log n)
 */
public class LastStoneWeight {

    public int lastStoneWeight(int[] stones) {
        if(stones==null)
            return 0;

        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.comparing(e -> -e));
        for (int i = 0; i < stones.length; i++) {
            pq.offer(stones[i]);
        }

        while (pq.size() > 1) {
            int first = pq.poll();
            int second = pq.poll();
            int rem = first - second;
            if (rem > 0) {
                pq.offer(rem);
            }
        }

        return pq.size()==1 ? pq.poll() : 0;
    }
}
