package BinaryTrees;

import com.sun.source.tree.Tree;


/**
 * https://leetcode.com/problems/convert-bst-to-greater-tree/
 */
public class GreaterBST {

    private TreeNode root;

    /**
     * Idea of inner class is supported by linked list and another code found here:
     * http://stackoverflow.com/questions/5262308/how-do-implement-a-breadth-first-traversal
     *
     * @author SERVICE-NOW\ameya.patil
     */
    private static class TreeNode {
        TreeNode left;
        TreeNode right;
        Integer item;
        TreeNode(TreeNode left, TreeNode right, Integer element) {
            this.left = left;
            this.right = right;
            this.item = element;
        }
    }

    private class Sum {
        int sum;
    }

    public void convertGBST(TreeNode node, Sum sum) {
        if (node == null) {
            return;
        }

        convertGBST(node.right, sum);

        node.item = node.item + sum.sum;
        sum.sum = node.item;

        convertGBST(node.left, sum);
    }

}
