package BinaryTrees;

import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.Stack;

/**
 * https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/
 *
 * TODO:
 * -----
 * 1. Recursive solution .
 * http://www.geeksforgeeks.org/level-order-traversal-in-spiral-form/
 * 2. odd levels could be reversed OR even would be reversed that depends on interviewer.
 *
 * Complexity:
 * Time: O (n)
 * Space: O (number of nodes at last level aka 2 ^ height, where height of root is 0)
 *
 * References:
 * -----------
 * http://www.geeksforgeeks.org/level-order-traversal-in-spiral-form/
 * http://stackoverflow.com/questions/17635950/space-complexity-of-level-order-traversal-traversal-using-a-queue
 *
 * Diagram:
 * https://bitbucket.org/ameyapatil/all-images/commits/a19aff3bf301d87f9465d4dba1510bd9d32a8dba
 *
 * BB: 17
 */
public class ZigZag {

    private TreeNode root;

    public ZigZag() { }

    /**
     * Idea of inner class is supported by linked list and another code found here:
     * http://stackoverflow.com/questions/5262308/how-do-implement-a-breadth-first-traversal
     */
    private static class TreeNode {
        TreeNode left;
        TreeNode right;
        Integer element;
        TreeNode(TreeNode left, TreeNode right, Integer element) {
            this.left = left;
            this.right = right;
            this.element = element;
        }
    }

    public void createBinaryTree (Integer[] arr) {
        if (arr == null)  {
            throw new NullPointerException("The input array is null.");
        }

        root = new TreeNode(null, null, arr[0]);

        final Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);

        final int half = arr.length / 2;

        for (int i = 0; i < half; i++) {
            if (arr[i] != null) {
                final TreeNode current = queue.poll();
                final int left = 2 * i + 1;
                final int right = 2 * i + 2;

                if (arr[left] != null) {
                    current.left = new TreeNode(null, null, arr[left]);
                    queue.add(current.left);
                }
                if (right < arr.length && arr[right] != null) {
                    current.right = new TreeNode(null, null, arr[right]);
                    queue.add(current.right);
                }
            }
        }
    }

    public void printTreeInSpiral() {

        /**
         * Copying the paradigm to LinkedList.java
         */
        if (root == null) {
            throw new NoSuchElementException("The root is null.");
        }

        spiralPrinter (root);
    }


    private void spiralPrinter(TreeNode node) {

        /**
         * 2 stacks are better than stack and a queue.
         * Stack simply uses a vector,
         * But the queue would use some kind of linkedlist beneath.
         *
         */
        final Stack<TreeNode> toRight = new Stack<TreeNode>();  // prints from left to right.
        final Stack<TreeNode> toLeft = new Stack<TreeNode>();   // prints from right to left.

        // toLeft.push(node);
        toRight.push(node);

        while (!toRight.empty() || !toLeft.empty()) {

            while (!toRight.empty()) {
                node = toRight.pop();
                System.out.print(node.element + " ");
                if (node.left != null)  {
                    toLeft.push(node.left);
                }
                if (node.right != null)  {
                    toLeft.push(node.right);
                }
            }

            System.out.println();
            while (!toLeft.empty()) {
                node = toLeft.pop();
                System.out.print(node.element + " ");
                if (node.right != null) {
                    toRight.push(node.right);
                }
                if (node.left != null) {
                    toRight.push(node.left);
                }
            }
            System.out.println();
        }
    }
}
