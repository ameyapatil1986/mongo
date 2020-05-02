package Arrays.sum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 *
 * https://leetcode.com/problems/combination-sum/
 *
 *
    Given a set of candidate numbers (C) and a target number (T), find all unique combinations in C
    where the candidate numbers sums to T.
    The same repeated number may be chosen from C unlimited number of times.
    Note:
    All numbers (including target) will be positive integers.
    Elements in a combination (a1, a2, ..., ak) must be in non-descending order. (ie, a1 ? a2 ? ... ? ak).
    The solution set must not contain duplicate combinations.
    For example, given candidate set 2,3,6,7 and target 7,
    A solution set is:
    [7]
    [2, 2, 3]
    Algorithm:
    Basically find out the combination of the int array to sum up to the target and
    it needs to take care of the repeated number, such as [2,2,3] and [1,6] for 7
    This algorithm has time complexity O((n+k)!) where n is the size of candidates,
                                        and k is the max repeated times for each candidates
    and space complexity O(m) where m is the size of array for the solution.
*/
public class CombinationSum {

    public static List<ArrayList<Integer>> combinationSum(int[] candidates, int target) {
        // Start typing your Java solution below
        // DO NOT write main() function
        List<ArrayList<Integer>> ret = new ArrayList<ArrayList<Integer>>();
        List<Integer> list = new ArrayList<Integer>();
        Arrays.sort(candidates);

        combinationSumHelper(candidates, target, 0, 0, ret, list);
        return ret;
    }

    public static void combinationSumHelper(int[] candidates, int target, int start, int sum, List<ArrayList<Integer>> ret, List<Integer> list) {

        if (sum > target) return;// Note: This method cannot finish large set if this line is missing

        if(sum == target) {
            for (int i : list) {
                System.out.print(i);
            }
            System.out.println();
            ret.add(new ArrayList<Integer>(list));
            return;
        }

        for(int i = start; i < candidates.length; i++) {
            list.add(candidates[i]);


            //           https://leetcode.com/problems/combination-sum/
            //           https://leetcode.com/problems/combination-sum-ii/
            //
            //            while ((i < a.length - 1) && (a[i] == a[i + 1])) {
            //                i++;
            //            }

            combinationSumHelper(candidates, target, i, sum + candidates[i], ret, list);



            list.remove(list.size() - 1);
        }
    }


    public static void main(String[] args) {
        int[] arr = {2, 3, 6, 7};
        combinationSum(arr, 7);
    }
}
