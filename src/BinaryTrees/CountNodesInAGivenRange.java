package BinaryTrees;

/**
 * https://www.geeksforgeeks.org/count-bst-nodes-that-are-in-a-given-range/
 * https://leetcode.com/problemset/all/?difficulty=Easy
 */
public class CountNodesInAGivenRange {

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

    public int count(TreeNode node, int min, int max) {
        if (node == null) {
            return 0;
        }

        if (node.item > max) {
            return count(node.left, min, max);
        }

        if (node.item < min) {
            return count(node.right, min, max);
        }

        return countThat(node, min, max);
    }


    public int countThat(TreeNode pTreeNode, int min, int max) {
        if (pTreeNode == null) {
            return 0;
        }

        if (min < pTreeNode.item && pTreeNode.item < max) {
            //
            return count(pTreeNode.left, min, max) + count(pTreeNode.right, min, max) + 1; // node.item.
        }

        return 0;
    }
}
