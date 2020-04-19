package Stack;

import java.util.Stack;


/**
 * https://leetcode.com/problems/largest-rectangle-in-histogram/
 *
 * Given n non-negative integers representing the histogram's bar height where the
 * width of each bar is 1, find the area of largest rectangle in the histogram.
 *
 * Above is a histogram where width of each bar is 1, given height = [2,1,5,6,2,3].
 *
 * Input: [2,1,5,6,2,3]
 * Output: 10
 *
 */
public class LargestRectangleHistogram {

    public int largestRectangleArea(int[] heights) {
        if (heights == null || heights.length == 0) {
            return 0;
        }

        Stack<Integer> stack = new Stack<Integer>();

        int max = 0;
        int i = 0;

        while (i < heights.length) {
            // push index to stack in ascending order.
            if (stack.isEmpty() || heights[stack.peek()] <= heights[i]) {
                stack.push(i);
                i++;
            } else {
                int height = heights[stack.pop()];
                int width = stack.isEmpty() ? i : i - (stack.peek() + 1);
                max = Math.max(height * width, max);
            }
        }

        while (!stack.isEmpty()) {
            int height = heights[stack.pop()];
            int width = stack.isEmpty() ? i : i - (stack.peek() + 1);
            max = Math.max(height * width, max);
        }

        return max;
    }
}
