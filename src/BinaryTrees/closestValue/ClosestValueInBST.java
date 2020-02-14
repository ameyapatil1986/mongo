package BinaryTrees.closestValue;

import BinaryTrees.BSTInorder;


/**
 * https://leetcode.com/problems/closest-binary-search-tree-value/
 */
public class ClosestValueInBST {

    private TreeNode root;

    private static class TreeNode {
        TreeNode left;
        TreeNode right;
        int item;

        TreeNode (TreeNode left, TreeNode right, int item) {
            this.left = left;
            this.right = right;
            this.item = item;
        }
    }

    public int closesValue(int x) {
        if (root == null) {
            return -1; // or an exception
        }

        TreeNode node = root;
        int min = Integer.MAX_VALUE;
        while (node != null) {

            if (node.item == x) {
                return x;
            }

            min = Math.min(min, Math.abs(x - node.item));

            if (x < node.item) {
                node = node.left;
            } else {
                node = node.right;
            }
        }

        return node.item;
    }
}
