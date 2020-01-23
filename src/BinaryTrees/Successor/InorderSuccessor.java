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

        TreeNode next = null;
        TreeNode c = root;
        while(c!=null && c.val!=p.val){
            if(c.val > p.val){
                next = c;
                c = c.left;
            }else{
                c= c.right;
            }
        }

        if(c==null)
            return null;

        if(c.right==null)
            return next;

        c = c.right;
        while(c.left!=null)
            c = c.left;

        return c;
    }
}
