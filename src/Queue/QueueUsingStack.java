package Queue;

import java.util.Stack;

/**
 * http://stackoverflow.com/questions/69192/how-to-implement-a-queue-using-two-stacks
 * http://www.geeksforgeeks.org/queue-using-stacks/
 *
 * NOTE:
 * ----
 * There are 2 options.
 * 1. making enqueue costly ( this is not O(1) for enqueue, read explanation on: http://www.geeksforgeeks.org/queue-using-stacks/)
 * 2. making deque costly (O(1) - for both enqueue and dequeue).
 *
 * BB:
 * 3
 *
 * Complexity:
 * O(1) : time push,
 *
 * O(1) : time pop,  ( its amortized )
 * http://courses.cs.washington.edu/courses/cse332/10sp/lectures/lecture21.pdf
 * http://stackoverflow.com/questions/23949640/constant-amortized-complexity-for-implementing-a-queue-using-two-stacks
 *
 * O(n) : space
 *
 * Companies:
 * Asked to Ganccha
 *
 */
public class QueueUsingStack<T> {

    private final Stack<T> in;
    private final Stack<T> out;

    private QueueUsingStack() {
        this.in = new Stack<T>();
        this.out = new Stack<T>();
    }

    /**
     * null item can in used as input
     */
    public synchronized void add(T item) {
        in.push(item);
    }

    /**
     *  null is valid input
     *  throws exception on being empty
     */
    public synchronized T poll() {
        if (out.isEmpty()) {
            while(!in.isEmpty()) {
                out.push(in.pop());
            }
        }

        return out.isEmpty() ? null : out.pop();
    }


    public static void main(String[] args) {
        QueueUsingStack<Integer> qus = new QueueUsingStack<Integer>();
        qus.add(1);
        qus.add(2);
        qus.add(3);

        System.out.println(qus.poll());
        System.out.println(qus.poll());
        System.out.println(qus.poll());
        System.out.println(qus.poll()); // printing null.

        //        Queue<Integer> queue = new PriorityQueue<Integer>();
        //        System.out.println(queue.poll());
        //        System.out.println(queue.poll());
    }
}
