package Heap;

import java.util.List;
import java.util.*;


/**
 * O(n log k )
 */
public class TopKFrequent {

    public List<Integer> topKFrequent(int[] nums, int k) {
        //count the frequency for each element
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        // create a min heap
        PriorityQueue<Map.Entry<Integer, Integer>> queue = new PriorityQueue<>(Comparator.comparing (e -> e.getValue()));

        //maintain a heap of size k.
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            queue.add(entry);
            if (queue.size() > k) {
                queue.poll();
            }
        }

        //get all elements from the heap
        List<Integer> result = new ArrayList<>();
        while (queue.size() > 0) {
            result.add(queue.poll().getKey());
        }

        //reverse the order
        Collections.reverse(result);

        return result;
    }
}
