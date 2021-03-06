package BinaryTrees.Successor;

import BinaryTrees.CountNodesInAGivenRange;


/**
 * https://www.programcreek.com/2014/05/leetcode-inorder-successor-in-bst-java/
 */
public class BSTInorderSuccessorWithoutParentPointer {

    private TreeNode root;

    private static class TreeNode {
        TreeNode left;
        TreeNode right;
        int val;

        TreeNode (TreeNode left, TreeNode right, int item) {
            this.left = left;
            this.right = right;
            this.val = item;
        }
    }

    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        if(root == null)
            return null;

        TreeNode prev = null;
        TreeNode current = root;

        // step 1: just search
        while (current != null) {

            if (current.val == p.val) {
                break;
            }

            if (p.val < current.val) {
                prev = current;
                current = current.left;
            } else {
                current= current.right;
            }
        }

        // if p is missing, then it means input was bad.
        if (current == null)
            return null;

        // if current.
        if (current.right == null)
            return prev;

        current = current.right;
        while (current.left != null)
            current = current.left;

        return current;
    }
}
