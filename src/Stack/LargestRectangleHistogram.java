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


//    public int largestRectangleArea(int[] arr) {
//        Stack<Integer > stack = new Stack<>();
//        int max = 0;
//
//        for (int i = 0; i <= arr.length; i++) {
//
//            while (!stack.isEmpty() && (i == arr.length || arr[stack.peek()] <= arr[i])) {
//                int height = arr[stack.pop()];
//                int width = (!stack.isEmpty()) ? i - stack.peek() - 1 : i;
//                max = Math.max(max, height * width);
//            }
//
//            stack.push(i);
//        }
//
//        return max;
//    }

    public int largestRectangleArea(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }

        Stack<Integer> stack = new Stack<Integer>();

        int max = 0;
        int i = 0;

        while (i < height.length) {
            // push index to stack in ascending order.
            if (stack.isEmpty() || height[stack.peek()] <= height[i]) {
                stack.push(i);
                i++;
            } else {
                int heightValue = height[stack.pop()];
                //calculate max value when the current height is less than the previous one
                int w = stack.isEmpty() ? i : i - (stack.peek() + 1);
                max = Math.max(heightValue * w, max);
            }
        }

        while (!stack.isEmpty()) {
            int heightValue = height[stack.pop()];
            //calculate max value when the current height is less than the previous one
            int w = stack.isEmpty() ? i : i - (stack.peek() + 1);
            max = Math.max(heightValue * w, max);
        }

        return max;
    }
}
