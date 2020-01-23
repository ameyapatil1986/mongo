package BinaryTrees;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * https://leetcode.com/articles/flip-equivalent-binary-trees/
 */
public class IsomorphicChecker<T> {

    private TreeNode<T> root;

    /**
     * Constructs a binary tree in order of elements in an array.
     * The input list is treated as  BFS representation of the list.
     * Note that it is the clients reponsibility to not modify input list in objects lifetime.
     */
    IsomorphicChecker(List<T> items) {
        create(items);
    }

    private void create(List<? extends T> items) {
        root = new TreeNode<T>(null, items.get(0), null);

        final Queue<TreeNode<T>> queue = new LinkedList<TreeNode<T>>();
        queue.add(root);

        final int half = items.size() / 2;

        for (int i = 0; i < half; i++) {
            if (items.get(i) != null) {
                final TreeNode<T> current = queue.poll();
                final int left = 2 * i + 1;
                final int right = 2 * i + 2;

                if (items.get(left) != null) {
                    current.left = new TreeNode<T>(null, items.get(left), null);
                    queue.add(current.left);
                }
                if (right < items.size() && items.get(right) != null) {
                    current.right = new TreeNode<T>(null, items.get(right), null);
                    queue.add(current.right);
                }
            }
        }
    }

    private static class TreeNode<T> {
        TreeNode<T> left;
        T item;
        TreeNode<T> right;

        TreeNode(TreeNode<T> left, T item, TreeNode<T> right) {
            this.left = left;
            this.item = item;
            this.right = right;
        }
    }

    /**
     * Checks if trees are isomorphic
     *
     * @param tree      the input tree
     * @return          true if input tree is isomorphic to current tree
     */
    public boolean checkIsomortphic(IsomorphicChecker<T> tree) {
        return checker(root, tree.root);
    }

    private boolean checker(TreeNode<T> node1, TreeNode<T> node2) {
        if (node1 == null && node2 == null) {
            return true;
        }

        if (node1 == null || node2 == null) {
            return false;
        }

        if (!node1.item.equals(node2.item)) {
            return false;
        }

        return  checker(node1.left, node2.left) && checker(node1.right, node2.right) ||
            checker(node1.left, node2.right) && checker(node1.right, node2.left);
    }
}
