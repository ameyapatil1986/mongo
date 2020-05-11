package Strings;

import java.util.*;


/**
 * O(n)
 */
public class ContainsDuplicate {

    public boolean containsDuplicate(int[] nums) {
        if(nums==null || nums.length==0)
            return false;

        Set<Integer> set = new HashSet<Integer>();
        for (int i: nums){
            // true if adding for first time.
            if (!set.add(i)){
                return true;
            }
        }

        return false;
    }
}
