package Arrays;

import java.util.LinkedList;

/**
 * https://www.programcreek.com/2014/05/leetcode-sliding-window-maximum-java/
 *
 * Input: nums = [1,3,-1,-3,5,3,6,7], and k = 3
 * Output:           [3,3,5,5,6,7]
 *
 * Sample example: [ 3 4 5 3 4 3] k = 3;
 *             output: [ 5 5 5 4]
 *
 */
public class SlidingWindowMaximum {

    public int[] maxSlidingWindow(int[] nums, int k) {

        if (nums==null || nums.length==0)
            return new int[0];

        // array size of 4 and queue of size 3, result should be of size = 2.
        int[] result = new int[nums.length - k + 1];

        final LinkedList<Integer> deque = new LinkedList<Integer>();

        for(int i = 0; i < nums.length; i++) {

            // remove last item from queue if queue size exceeds k.
            if (!deque.isEmpty() && deque.peek() == i - k)
                deque.poll();     // a poll removes from the head.

            // now queue is small, keep it in descending order.
            while (!deque.isEmpty() && nums[deque.peekLast()] < nums[i]){
                deque.removeLast(); // removes from the tail.
            }

            deque.add(i);

            // similar to palindrome sub-sequencing : n - currLen + 1
            if (i - k + 1 >= 0) {
                result[i - k + 1] = nums[deque.peek()]; // peek's the head.
            }
        }

        return result;
    }

}
