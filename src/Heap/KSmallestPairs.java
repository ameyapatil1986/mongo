package Heap;

import java.util.*;


/**
 * https://leetcode.com/problems/find-k-pairs-with-smallest-sums/
 * https://www.programcreek.com/2015/07/leetcode-find-k-pairs-with-smallest-sums-java/
 *
 * You are given two integer arrays nums1 and nums2 sorted in ascending order and an integer k.
 *
 * Define a pair (u,v) which consists of one element from the first array and one element from the second array.
 *
 * Find the k pairs (u1,v1),(u2,v2) ...(uk,vk) with the smallest sums.
 *
 * Example 1:
 *
 * Input: nums1 = [1,7,11], nums2 = [2,4,6], k = 3
 * Output: [[1,2],[1,4],[1,6]]
 * Explanation: The first 3 pairs are returned from the sequence:
 *              [1,2],[1,4],[1,6],[7,2],[7,4],[11,2],[7,6],[11,4],[11,6]
 * Example 2:
 *
 * Input: nums1 = [1,1,2], nums2 = [1,2,3], k = 2
 * Output: [1,1],[1,1]
 * Explanation: The first 2 pairs are returned from the sequence:
 *              [1,1],[1,1],[1,2],[2,1],[1,2],[2,2],[1,3],[1,3],[2,3]
 * Example 3:
 *
 * Input: nums1 = [1,2], nums2 = [3], k = 3
 * Output: [1,3],[2,3]
 * Explanation: All possible pairs are returned from the sequence: [1,3],[2,3]
 *
 *
 *
 * https://letstalkalgorithms.com/find-k-pairs-with-smallest-sum-java-solution/
 * Output would be [[1,2],[3,2],[4,2]]
 *
 * O(K logK).
 *
 */
public class KSmallestPairs {

    public static void main(String[] args) {
        int[] nums1 = {1, 3, 4};
        int[] nums2 = {2, 6, 9};
        System.out.println(new KSmallestPairs().kSmallestPairs(nums1, nums2, 3));
    }

    public class SomeObject {
        int index1;
        int index2;

        public SomeObject(int pIndex1, int pIndex2) {
            this.index1 = pIndex1;
            this.index2 = pIndex2;
        }
    }

    public List<int[]> kSmallestPairs(int[] nums1, int[] nums2, int k) {

        PriorityQueue<SomeObject> queue = new PriorityQueue<>(Comparator.comparing(o -> nums1[o.index1] + nums2[o.index2]));

        for(int i = 0; i < Math.min(nums1.length, k); i++) {
            queue.offer(new SomeObject(i, 0));
        }

        final List<int[]> res = new ArrayList<>();
        while (k-- > 0 && !queue.isEmpty()) {
            SomeObject o = queue.poll();

            res.add(new int[]{nums1[o.index1], nums2[o.index2]});

            if(o.index2 == nums2.length - 1) {
                continue;
            }

            queue.offer(new SomeObject(o.index1, o.index2 + 1));
        }

        return res;
    }

//    public List<int[]> kSmallestPairs(int[] nums1, int[] nums2, int k) {
//
//        PriorityQueue<SomeObject> queue = new PriorityQueue<>(Comparator.comparing(o -> o.val1 + o.val2));
//
//        for(int i = 0; i < Math.min(nums1.length, k); i++) {
//            queue.offer(new SomeObject(nums1[i], nums2[0], 0));
//        }
//
//        final List<int[]> res = new ArrayList<>();
//        while (k-- > 0 && !queue.isEmpty()) {
//            SomeObject o = queue.poll();
//
//            res.add(new int[]{o.val1, o.val2});
//
//            if(o.index == nums2.length - 1) {
//                continue;
//            }
//
//            queue.offer(new SomeObject(o.val1, nums2[o.index + 1], o.index + 1));
//        }
//
//        return res;
//    }
}
