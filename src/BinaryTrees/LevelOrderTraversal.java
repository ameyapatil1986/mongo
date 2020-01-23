package BinaryTrees;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


/**
 * https://leetcode.com/problems/binary-tree-level-order-traversal/
 */
public class LevelOrderTraversal<T> {

    private TreeNode<T> root;

    public LevelOrderTraversal(List<T> items) {
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

    public List<List<T>> getMaxWidth() {
        if (root == null) {
            throw new IllegalStateException("The root cannot be null.");
        }

        final List<List<T>> levelOrder = new ArrayList<>();

        final Queue<TreeNode<T>> queue = new LinkedList<>();
        queue.add(root);
        queue.add(null);

        List<T> currentLevel = new ArrayList<>();

        while (!queue.isEmpty()) {
            TreeNode<T> currNode = queue.poll();

            if (currNode == null) {
                levelOrder.add(currentLevel);

                // if next level existed, then size should have been > 2.
                if (queue.size() > 0) {
                    currentLevel = new ArrayList<>();
                    queue.add(null); // add new end-of-row-marker.
                }
            } else {
                currentLevel.add(currNode.item);

                if (currNode.left != null) queue.add(currNode.left);
                if (currNode.right != null) queue.add(currNode.right);
            }
        }

        return levelOrder;
    }
}
