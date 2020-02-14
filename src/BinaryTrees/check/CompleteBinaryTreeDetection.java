package BinaryTrees.check;


import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Queue;

/**
 * References:
 * http://codereview.stackexchange.com/questions/54970/detect-a-complete-binary-tree
 * http://www.geeksforgeeks.org/check-if-a-given-binary-tree-is-complete-tree-or-not/
 * http://codereview.stackexchange.com/questions/55626/detect-if-a-tree-is-complete-binary-tree
 *
 * Logic:
 * 1. In this we do BFS.
 * 2. A complete binary tree has a property that while doing a left to right BFS,
 *    an incomplete node must be encountered only once.
 *
 * Complexity:
 * O(n) - time
 * O(n) - space
 *
 * BB:
 * 56
 *
 */
public class CompleteBinaryTreeDetection<T> {

    private TreeNode<T> root;

    /**
     * Constructs a binary tree in order of elements in an array.
     * After the number of nodes in the level have maxed, the next
     * element in the array would be a child of leftmost node.
     */
    public CompleteBinaryTreeDetection(List<T> items) {
        create(items);
    }

    private void create (List<T> items) {
        root = new TreeNode<T>(null, items.get(0), null);

        final Queue<TreeNode<T>> queue = new LinkedList<TreeNode<T>>();
        queue.add(root);

        final int half = items.size() / 2;

        for (int i = 0; i < half; i++) {
            if (items.get(i) != null) {
                final TreeNode<T> current = queue.poll();
                int left = 2 * i + 1;
                int right = 2 * i + 2;

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

        public TreeNode(TreeNode<T> left, T item, TreeNode<T> right) {
            this.left = left;
            this.item = item;
            this.right = right;
        }
    }

    /**
     * Returns true if binary tree is complete
     *
     * @return  true if tree is complete else false.
     */
    public boolean isComplete() {
        if (root == null) {
            throw new NoSuchElementException();
        }
        final Queue<TreeNode<T>> queue = new LinkedList<TreeNode<T>>();
        queue.add(root);

        boolean leftNull = false;
        boolean rightNull = false;

        while (!queue.isEmpty()) {
            TreeNode<T> node = queue.poll();

            if (node.left == null) {
                leftNull = true;
            } else {
                if (rightNull) {
                    return false;
                }
                queue.add(node.left);
            }

            if (node.right == null) {
                rightNull = true;
            } else {
                if (leftNull) {
                    return false;
                }
                queue.add(node.right);
            }
        }
        return true;
    }

}
