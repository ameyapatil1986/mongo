package Stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * http://massivealgorithms.blogspot.com/2018/04/leetcode-739-daily-temperatures.html
 *
 * Given a list of daily temperatures, produce a list that, for each day in the input, tells you how many days you would have
 * to wait until a warmer temperature. If there is no future day for which this is possible, put 0 instead.
 * For example, given the list temperatures = [73, 74, 75, 71, 69, 72, 76, 73], your output should be [1, 1, 4, 2, 1, 1, 0, 0].
 *
 */
public class DailyTemperature {

    public int[] dailyTemperatures(int[] temperatures) {
        Stack<Integer> stack = new Stack<>();
        int[] ret = new int[temperatures.length];
        for(int i = 0; i < temperatures.length; i++) {
            // descending
            while(!stack.isEmpty() && temperatures[stack.peek()] < temperatures[i]) {
                int idx = stack.pop();
                ret[idx] = i - idx;
            }
            stack.push(i);
        }
        return ret;
    }

//    public static List<DataSet> getNextGreatestElement(int[] a) {
//
//        final List<DataSet> dataSetList = new ArrayList<DataSet>();
//        final Stack<Integer> stack = new Stack<Integer>();
//
//        for (int i = 0; i < a.length; i++) {
//
//            while (!stack.isEmpty() && stack.peek() < a[i]) {
//                dataSetList.add(new DataSet(stack.pop(), a[i]));
//            }
//
//            stack.push(a[i]);
//        }
//
//        while (!stack.isEmpty()) {
//            dataSetList.add(new DataSet(stack.pop(), -1));
//        }
//
//
//        return dataSetList;
//    }



}
