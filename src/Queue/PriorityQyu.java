package Queue;

import java.util.Comparator;
import java.util.PriorityQueue;


public class PriorityQyu {

    public static void main(String[] args) {
        PriorityQueue<Integer> ascendingQueue = new PriorityQueue<Integer>();

        // ascending queue will save the "greatest 3 elements"
        for (int i = 1; i <= 10; i++)  {
            ascendingQueue.add(i);
            if (ascendingQueue.size() > 3) {
                System.out.println(ascendingQueue.poll());
            }
        }

        PriorityQueue<Integer> descendingQueue = new PriorityQueue<Integer>(Comparator.comparing(e -> -e));
        // ascending queue will save the "smallest 3 elements"
        for (int i = 1; i <= 10; i++)  {
            descendingQueue.add(i);
            if (descendingQueue.size() > 3) {
                System.out.println(descendingQueue.poll());
            }
        }
    }
}
