//package BinaryTrees;
//
///**
// * https://leetcode.com/problems/house-robber-iii/
// *
// * cumultive addition.
// */
//public class HouseRobberThree {
//
//    private static class TreeNode {
//        TreeNode left;
//        int item;
//        TreeNode right;
//
//        TreeNode(TreeNode left, char ch, TreeNode right) {
//            this.left = left;
//            this.ch = ch;
//            this.right = right;
//        }
//    }
//
//    /**
//     * Definition for a binary tree node.
//     * public class TreeNode {
//     *     int val;
//     *     TreeNode left;
//     *     TreeNode right;
//     *     TreeNode(int x) { val = x; }
//     * }
//     */
//    public int rob(TreeNode root) {
//        if (root == null) {
//            return 0;
//        }
//
//        int[] result = robHelper(root);
//        return Math.max(result[0], result[1]);
//    }
//
//    /**
//     *           (2)
//     *          /  \
//     *       (40)  (5)
//     *      /   \     \
//     *    (1)  (2)    (50)
//     *
//     */
//    private int[] robHelper(TreeNode root) {
//        if (root == null) {
//            return new int[2];
//        }
//
//        int[] left = robHelper(root.left);
//        int[] right = robHelper(root.right);
//
//        int[] curr = new int[2];
//        curr[0] = root.item + left[1] + right[1];    // include current & postion '1' does not include immediate check
//        curr[1] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]); // exclude current.
//
//        return curr;
//    }
//}
