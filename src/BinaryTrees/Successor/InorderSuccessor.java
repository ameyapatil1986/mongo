package BinaryTrees.Successor;

import BinaryTrees.CountNodesInAGivenRange;


/**
 * https://www.programcreek.com/2014/05/leetcode-inorder-successor-in-bst-java/
 */
public class InorderSuccessor {

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
        if(root==null)
            return null;

        TreeNode prev = null;
        TreeNode current = root;
        while (current!=null && current.val != p.val) {
            if(p.val < current.val){
                prev = current;
                current = current.left;
            }else{
                current= current.right;
            }
        }

        if(current==null)
            return null;

        if(current.right==null)
            return prev;

        current = current.right;
        while(current.left!=null)
            current = current.left;

        return current;
    }
}
