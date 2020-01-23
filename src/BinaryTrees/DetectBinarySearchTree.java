package BinaryTrees;

import java.util.Arrays;
import java.util.List;

public class DetectBinarySearchTree {

    private TreeNode root;

    public DetectBinarySearchTree(List<Integer> items) {
        create(items);
    };

    private void create (List<Integer> items) {
        if (items.isEmpty())  {
            throw new NullPointerException("The input array is not empty.");
        }

        root = new TreeNode(items.get(0));

        for (int i = 1; i < items.size(); i++) {
            TreeNode node = root;
            while (node != null) {
                if (items.get(i) < node.element) {
                    if (node.left == null) {
                        node.left = new TreeNode(items.get(i));
                        break;
                    }
                    node = node.left;
                } else {
                    if (node.right == null) {
                        node.right = new TreeNode(items.get(i));
                        break;
                    }
                    node = node.right;
                }
            }
        }
    }

    private static class TreeNode {
        private TreeNode left;
        private Integer element;
        private TreeNode right;

        TreeNode(int element) {
            this.element = element;
        }
    }

    /*
     * NOTE: IF INTERVIEWER ASKS THIS QUESTION, CHANGE THE DEFINITION OF
     * A BINARY SEARCH TREE.
     * A BINARY SEARCH TREE WRT THIS CODE MEANS, LEFT HAS TO BE 'LESS' AND RIGHT HAS TO BE 'GREATER' THAN THE PARENT.
     * NO >= OR <= ALLOWED.
     * IF YOU DO IT THAT WAY, THEN INT.MAX AND INT.MIN WOULD WORK WITHOUT ANY CONCERN OF EDGE CASE.
     */
    public boolean detectBinarySearchTree() {
        if (root == null) {
            throw new IllegalArgumentException("The root is null.");
        }
        return isBinarySearchTree(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private boolean isBinarySearchTree(TreeNode node, int min, int max) {
        if (node == null) return true;

        // if duplicate on the right child is permitted then simply do: if (min <= node.element && node.element < max)
        if (min <= node.element && node.element < max) {
            return isBinarySearchTree(node.left, min, node.element)
                && isBinarySearchTree(node.right, node.element, max);
        }
        return false;
    }
    // now i will clean my code
}
