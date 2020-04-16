package Arrays;

import java.util.*;


/**
 * https://leetcode.com/discuss/interview-question/373202/Amazon-or-OA-2019-or-Optimal-Utilization/391917
 *
 * Given 2 lists a and b. Each element is a pair of integers where the
 * first integer represents the unique id and the second integer represents a value.
 * Your task is to find an element from a and an element form b such that the sum of their
 * values is less or equal to target and as close to target as possible.
 * Return a list of ids of selected elements. If no pair is possible, return an empty list.
 */
public class OptimalUtilization {

    public static List<int[]> getOptimalSolution(List<int[]> list1, List<int[]> list2, int target) {

        List<int[]> result = new ArrayList<>();

        int i = 0;
        int j = 0;

        int currI = 0;
        int currJ = 0;
        int min = Integer.MAX_VALUE;

        while (i < list1.size() || j < list2.size()) {

            int[] left = list1.get(i);
            int[] right = list2.get(j);

            int currSum = left[1] + right[1];
            if (currSum > target) {
                break;
            }

            if (target - currSum == min) {
                result.add(new int[]{left[0], right[0]});
            }

            if (target - currSum < min) {
                min = target - currSum;
                result.clear();
                result.add(new int[]{left[0], right[0]});
            }

            if (j == list2.size() - 1) {
                i++;
            } else if (i == list1.size() - 1) {
                j++;
            } else {
                int[] nextLeft = list1.get(i + 1);
                int[] nextRight = list1.get(j + 1);

                if (nextLeft[1] < nextRight[1]) {
                    i++;
                } else {
                    j++;
                }
            }
        }


        return result;
    }

    /**
     *
     * a = [[1, 2], [2, 4], [3, 6]]
     * b = [[1, 2]]
     * target = 7
     *
     */
    public static void main(String[] args) {
        int[] a1 = {1, 2};
        int[] a2 = {2, 4};
        int[] a3 = {3, 6};
        int[] b1 = {1, 2};

        List<int[]> l = getOptimalSolution(Arrays.asList(a1, a2, a3), Arrays.asList(b1), 7);
        for (int[] list : l) {
            System.out.println(list[0] + "," + list[1]);
        }
    }
}
