package Heap;

import java.util.*;


/**
 * https://medium.com/algorithm-and-datastructure/distance-barcodes-986eb0c73b59
 *
 * In a warehouse, there is a row of barcodes, where the i-th barcode is barcodes[i].
 * Rearrange the barcodes so that no two adjacent barcodes are equal.
 * You may return any answer, and it is guaranteed an answer exists.
 *
 * Example 1:
 *
 * Input: [1,1,1,2,2,2]
 * Output: [2,1,2,1,2,1]
 *
 * Example 2:
 *
 * Input: [1,1,1,1,2,2,3,3]
 * Output: [1,3,1,3,2,1,2,1]
 *
 */
public class DistantBarcode {

    public int[] rearrangeBarcodes(int[] barcodes) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = barcodes.length - 1; i >= 0; i--) {
            map.put(barcodes[i], map.getOrDefault(barcodes[i], 0) + 1);
        }

        // Here using the priority queue to sort the map with highest value first
        // Descending sort
        PriorityQueue<Map.Entry<Integer, Integer>> pq = new PriorityQueue<>(Comparator.comparing(e -> -e.getValue()));
        pq.addAll(map.entrySet());

        int idx = 0;
        Arrays.fill(barcodes, -1);

        while (!pq.isEmpty()) {
            Map.Entry m = pq.poll();
            int key = (int) m.getKey();
            int frequencyVal = (int) m.getValue();

            for (int i = 0; i  < frequencyVal; ) {
                if (barcodes[idx] == -1) {
                    barcodes[idx] = key;
                    idx++;
                    i++;
                }

                idx++;

                if (idx >= barcodes.length) {
                    idx = 0;
                }
            }
        }

        return barcodes;
    }
}
