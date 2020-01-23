package BinaryTrees.LCA;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 *
 * Reference:
 * http://www.geeksforgeeks.org/lowest-common-ancestor-binary-tree-set-1/
 * http://codereview.stackexchange.com/questions/77483/least-common-ancestorlca-in-a-binary-tree
 *
 * BB:
 * 19
 *
 * Complexity:
 * O(n) - time
 * Space: O(n) or O(logn) depending on wether we are skewed or not.
 * http://stackoverflow.com/questions/27938742/what-is-space-complexity-of-path-from-root-to-leaf-in-the-tree
 *
 */
public class LeastCommonAncestor<E> {

    private TreeNode<E> root;

    /**
     * Constructs a binary tree in order of elements in an array.
     * After the number of nodes in the level have maxed, the next
     * element in the array would be a child of leftmost node.
     */
    public LeastCommonAncestor(List<E> items) {
        createBinaryTree(items);
    }

    private static class TreeNode<E> {
        TreeNode<E> left;
        TreeNode<E> right;
        E item;
        public TreeNode(E item) {
            this.item = item;
        }
    }

    public void createBinaryTree(List<E> items) {
        if (items.size() == 0)  {
            throw new IllegalArgumentException("The input array is null.");
        }

        root = new TreeNode<>(items.get(0));

        final Queue<TreeNode<E>> queue = new LinkedList<TreeNode<E>>();
        queue.add(root);

        final int half = items.size() / 2;

        for (int i = 0; i < half; i++) {
            if (items.get(i) != null) {
                final TreeNode<E> current = queue.poll();
                final int left = 2 * i + 1;
                final int right = 2 * i + 2;

                if (items.get(left) != null) {
                    current.left = new TreeNode<E>(items.get(left));
                    queue.add(current.left);
                }
                if (right < items.size() && items.get(right) != null) {
                    current.right = new TreeNode<E>(items.get(right));
                    queue.add(current.right);
                }
            }
        }
    }

    /**
     * Returns the lease common ancestor of items. A item is also an ancestor or itself. If item1 is parent of item2 or
     * item1 == item2, then item1 is the value returned. If tree contains duplicate elements then the ancestor
     * encountered in the inorder first in the preorder traversal is the one which is returned.
     *
     *
     * @param item1     The first item.
     * @param item2     The second item.
     * @return          The common ancestor of item1 and item2.
     */
    public E getLeastCommonAncestor(E item1, E item2) {
        final List<E> item1Path = new ArrayList<>();
        final List<E> item2Path = new ArrayList<>();

        if (!populatePath(root, item1Path, item1)) {
            throw new IllegalArgumentException("The item1: " + item1 + " is not a tree node.");
        }

        if (!populatePath(root, item2Path, item2)) {
            throw new IllegalArgumentException("The item1: " + item2 + " is not a tree node.");
        }

        return fetchLeastCommonAncestor(item1Path, item2Path);
    }

    private boolean populatePath(TreeNode<E> node, List<E> items, E item) {
        if (node == null) {
            return false;
        }

        items.add(node.item);

        if (node.item == item) {
            return true;
        }

        if (populatePath(node.left, items, item) || populatePath(node.right, items, item)) {
            return true;
        }

        items.remove(items.size() - 1);
        return false;
    }

    private E fetchLeastCommonAncestor(List<E> item1Path, List<E> item2Path) {
        int minLength = Math.min(item1Path.size(), item2Path.size());
        int i;

        for (i = 0; i < minLength; i++) {
            if (item1Path.get(i) != item2Path.get(i)) {
                break;
            }
        }
        return item1Path.get(i - 1);
    }
}
