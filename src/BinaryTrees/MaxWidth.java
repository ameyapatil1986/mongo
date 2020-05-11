package BinaryTrees;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


/**
 * https://leetcode.com/problems/binary-tree-level-order-traversal/
 */
public class MaxWidth<T> {

    private TreeNode<T> root;

    public MaxWidth(List<T> items) {
        create(items);
    };


    private void create(List<T> items) {
        if (items.isEmpty())  {
            throw new IllegalArgumentException("The input array is not empty.");
        }

        root = new TreeNode<>(items.get(0));

        final Queue<TreeNode<T>> queue = new LinkedList<TreeNode<T>>();
        queue.add(root);

        final int half = items.size() / 2;

        for (int i = 0; i < half; i++) {
            if (items.get(i) != null) {
                final TreeNode<T> current = queue.poll();
                final int left = 2 * i + 1;
                final int right = 2 * i + 2;

                if (items.get(left) != null) {
                    current.left = new TreeNode<>(items.get(left));
                    queue.add(current.left);
                }
                if (right < items.size() && items.get(right) != null) {
                    current.right = new TreeNode<>(items.get(right));
                    queue.add(current.right);
                }
            }
        }
    }

    private static class TreeNode<T> {
        private TreeNode<T> left;
        private T item;
        private TreeNode<T> right;

        TreeNode(T item) {
            this.item = item;
        }
    }

    public int getMaxWidth() {
        if (root == null) {
            throw new IllegalStateException("The root cannot be null.");
        }


        final Queue<TreeNode<T>> queue = new LinkedList<>();
        queue.add(root);
        queue.add(null);

        int max = 1; // width is 1 for first level with only a root.

        while (!queue.isEmpty()) {
            TreeNode<T> currNode = queue.poll();

            if (currNode == null) {
                max = Math.max(max, queue.size());

                if (queue.isEmpty()) {
                    return max;
                } else {
                    queue.add(null);
                }
            } else {

                if (currNode.left != null) queue.add(currNode.left);
                if (currNode.right != null) queue.add(currNode.right);

            }
        }

        return -1;
    }
}



















