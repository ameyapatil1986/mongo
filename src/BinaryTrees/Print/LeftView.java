package BinaryTrees.Print;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Queue;

/**
 * https://leetcode.com/problems/find-bottom-left-tree-value/
 *
 * Left view of binary tree.
 * http://www.geeksforgeeks.org/print-left-view-binary-tree/
 * http://codereview.stackexchange.com/questions/47550/print-left-view-of-the-tree
 *
 * Complexity:
 * O(n) - time complexity
 * O(n) - space complexity.
 *
 * BB:
 * 52
 */
public class LeftView<T> {

    private TreeNode<T> root;

    /**
     * Takes in a BFS representation of a tree, and converts it into a tree.
     * here the left and right children of nodes are the (2*i + 1) and (2*i + 2)nd
     * positions respectively.
     *
     * @param items The items to be node values.
     */
    public LeftView(List<? extends T> items) {
        create(items);
    }

    private void create (List<? extends T> items) {
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

    private static class TreeNode<E> {
        TreeNode<E> left;
        E item;
        TreeNode<E> right;

        TreeNode(TreeNode<E> left, E item, TreeNode<E> right) {
            this.left = left;
            this.item = item;
            this.right = right;
        }
    }

    public static class Counter {
        int maxLevelViewed;

        Counter(int counter) {
            this.maxLevelViewed = counter;
        }
    }

    /**
     * Returns the left view of the binary tree
     *
     * @return  the left view of binary tree.
     */
    public List<T> leftView() {
        if (root == null) { throw new NoSuchElementException("The root is null"); }

        final List<T> leftView = new ArrayList<T>();
        computeLeftView(root, leftView, new Counter(-1), 0);
        return leftView;
    }

    private void computeLeftView(TreeNode<T> node, List<T> leftView, Counter counter, int currentDepth) {
        if (node == null) return;

        if (currentDepth > counter.maxLevelViewed) {
            leftView.add(node.item);
            counter.maxLevelViewed++;
        }
        computeLeftView(node.left, leftView, counter, currentDepth + 1);
        computeLeftView(node.right, leftView, counter, currentDepth + 1);
    }


    /**
     * Returns the right view of the binary tree
     * http://www.geeksforgeeks.org/print-right-view-binary-tree-2/
     *
     *
     * @return  the left view of binary tree.
     */
    public List<T> rightView() {
        if (root == null) { throw new NoSuchElementException("The root is null"); }

        final List<T> rightView = new ArrayList<T>();
        computeRightView(root, rightView, new Counter(-1), 0);
        return rightView;
    }

    private void computeRightView(TreeNode<T> node, List<T> rightView, Counter counter, int currentDepth) {
        if (node == null) return;
        if (currentDepth > counter.maxLevelViewed) {
            rightView.add(node.item);
            counter.maxLevelViewed++;
        }
        computeRightView(node.right, rightView, counter, currentDepth + 1);
        computeRightView(node.left, rightView, counter, currentDepth + 1);
    }
}
