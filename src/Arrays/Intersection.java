package Arrays;

import java.util.*;


/**
 * https://leetcode.com/problems/intersection-of-two-arrays-ii/
 */
public class Intersection {

    /**
     * We need duplicates - thus we cannot use a hashset.
     * https://www.programcreek.com/2014/05/leetcode-intersection-of-two-arrays-ii-java/
     */
    public int[] intersect(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);

        List<Integer> list = new ArrayList<Integer>();
        int p1=0, p2=0;

        while( p1 < nums1.length && p2 < nums2.length){
            if(nums1[p1] < nums2[p2]){
                p1++;
            }else if(nums1[p1] > nums2[p2]){
                p2++;
            }else{
                list.add(nums1[p1]);
                p1++;
                p2++;

            }
        }

        return list.stream().mapToInt(i -> i).toArray();
    }

}
