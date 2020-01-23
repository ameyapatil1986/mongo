package Arrays;

import java.util.*;

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
                currI = left[0];
                currJ = right[0];
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
