package BinaryTrees;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * https://leetcode.com/problems/binary-tree-inorder-traversal/
 */

/**
 * SAMPLE EXAMPLE:
 * ---------------
 *             10
 *       5
 *  3        7
 *
 * Lets do only half tree, since it gets very confusing.
 *
 */

/**
 * NOTE:
 * -----
 * This is also called "Inorder traversal without recursion and stack".
 *
 *
 * References:
 * -----------
 * http://www.geeksforgeeks.org/inorder-tree-traversal-without-recursion-and-without-stack/
 * http://codeoverflow.wordpress.com/tag/morris-inorder-traversal/
 * http://stackoverflow.com/questions/6478063/how-is-the-complexity-of-morris-traversal-on
 * http://codereview.stackexchange.com/questions/49252/morris-inorder-traversal
 *
 * Complexity:
 * -----------
 * O(n)
 *
 * Explaining complexity:
 * ----------------------
 * It appears to be O(n2) but it not.
 * It appears O(n2) because it looks like we are finding in order predecessor for each node.
 * Thus O(eachNode(n) * predecessor(n))  => O(n2)
 *
 * Now assuming finding inorder predecessor was considered to be O(logn)
 * Thus it appears complexity would be
 * O(eachNode(n) * predecessor(logn))  => O(nlogn)
 *
 * But the truth is:
 * In a branch finding inorder precedecessor is O(1)
 * example:
 *      A
 *     /
 *    B
 *     \
 *      C
 *       \
 *        D
 *
 *
 * Here only A needs to travel upto D which is O(n), but for the remaining B, C, D its only O(1)
 * Thus in a way O(n / n ) => O(1)
 *
 * Thus each node is traversed 3 times.
 * Take case of element D.
 * D is travelled once, when it becomes inorder predecessor of A ie D.right = A
 * D is travelled once while printing it.
 * D is travelled again while its D.right = null.
 *
 * Thus the complexity is sort of O(3n), but safe to call it O(1).
 *
 * BB:
 * 53
 *
 * @author SERVICE-NOW\ameya.patil
 */
public class MorrisInOrder<T> {

    TreeNode<T> root;

    /**
     * Takes in a BFS representation of a tree, and converts it into a tree.
     * here the left and right children of nodes are the (2*i + 1) and (2*i + 2)nd
     * positions respectively.
     *
     * @param items The items to be node values.
     */
    public MorrisInOrder(List<? extends T> items) {
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
     * Returns the inorder traversal of the tree.
     *
     * @return list containing inorder traversal of elements
     * @throws NoSuchAlgorithmException
     */
    public List<T> inOrder() throws NoSuchAlgorithmException {
        if (root == null) throw new NoSuchAlgorithmException("The root is null.");

        final List<T> list = new ArrayList<T>();
        TreeNode<T> current  = root;

        while (current != null) {
            if (current.left == null) {
                list.add(current.item);
                current = current.right;
            } else {
                TreeNode<T> predecessor = current.left;
                /*
                 * found the in-order predecessor.
                 */
                while (predecessor.right != null && predecessor.right != current) {
                    predecessor = predecessor.right;
                }

                if (predecessor.right == null) {
                    // hook it up.
                    predecessor.right = current;
                    current = current.left;
                } else {
                    list.add(current.item);
                    predecessor.right = null;
                    current  = current.right;
                }
            }
        }
        return list;
    }
}


