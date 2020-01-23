package Heap;

import java.util.*;


/**
 * https://www.youtube.com/watch?v=3dqR2nYElyw&list=PLi9RQVmJD2fYXgBAUfaN5MXgYPUTbzqeb&index=98
 */
public class ConnectSticks {

    public int connectedSticks(Integer[] sticks) {

        final Queue<Integer> pq = new PriorityQueue<>(Arrays.asList(sticks));

        int sum = 0;
        int totalSum = 0;
        while (pq.size() > 1) {
            sum = pq.poll() + pq.poll();
            totalSum  += sum;
            pq.add(sum);
        }

        return totalSum;
    }

}
