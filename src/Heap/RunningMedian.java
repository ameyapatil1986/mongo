package Heap;

/*
For problem and solution description please visit the link below
http://www.dsalgo.com/2013/02/RunningMedian.php.html
*/

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;

/**
 * https://leetcode.com/problems/find-median-from-data-stream/
 */


/**
 * Complexity
 * O (nlogn), where n is the total sum of all elements.
 *
 * BB:
 * 5
 */
public class RunningMedian {

    private final Queue<Integer> maxHeap;
    private final Queue<Integer> minHeap;

    public RunningMedian() {
        // implemented a max heap.
        maxHeap = new PriorityQueue<>(20, (a, b) -> b - a);
        maxHeap.add(Integer.MIN_VALUE);

        minHeap = new PriorityQueue<>();
        minHeap.add(Integer.MAX_VALUE);
    }

    /**
     * 3 step solution:
     * - add.
     * - balance.
     * - return.
     */
    public double getMedian(int num) {

        //adding the number to proper heap
        if(num >= minHeap.peek())
            minHeap.add(num);
        else
            maxHeap.add(num);

        //balancing the heaps
        if(minHeap.size() - maxHeap.size() == 2)
            maxHeap.add(minHeap.poll());
        else if(maxHeap.size() - minHeap.size() == 2)
            minHeap.add(maxHeap.poll());

        //returning the median
        if (minHeap.size() == maxHeap.size())
            return (minHeap.peek() + maxHeap.peek()) / 2.0;
        else if (minHeap.size() > maxHeap.size()) {
            return minHeap.peek();
        } else {
            return maxHeap.peek();
        }
    }

    public static void main(String[] args) {
        Random random=new Random();
        RunningMedian runningMedian=new RunningMedian();

        for(int i= 0;i <= 8; ++i) {
            System.out.println(runningMedian.getMedian(i));
        }

        System.out.println("num\tmedian");

        //        for(int i=0;i < 50; ++i) {
        //            int num = random.nextInt(100);
        //            System.out.print(num);
        //            System.out.print("\t");
        //            System.out.println(runningMedian.getMedian(num));
        //        }
    }
}
