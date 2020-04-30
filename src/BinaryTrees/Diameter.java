package BinaryTrees;

import java.util.LinkedList;
import java.util.Queue;

/**
 * https://leetcode.com/problems/maximum-depth-of-binary-tree/
 */


/**
 * Di(i for inclusive)ameter of a binary tree:
 * "This is a count of number of nodes (inclusive) not count of number of edges"
 *
 * References:
 * http://stackoverflow.com/questions/11897088/diameter-of-binary-tree-better-design
 * http://www.geeksforgeeks.org/diameter-of-a-binary-tree/
 * http://stackoverflow.com/questions/575772/the-best-way-to-calculate-the-height-in-a-binary-search-tree-balancing-an-avl
 *
 * BB: 27
 *
 * Complexity:
 * O(n2)
 *
 */
public class Diameter {

    private TreeNode root;

    /**
     * Idea of inner class is supported by linked list and another code found here:
     * http://stackoverflow.com/questions/5262308/how-do-implement-a-breadth-first-traversal
     *
     * @author SERVICE-NOW\ameya.patil
     */
    private static class TreeNode {
        TreeNode left;
        TreeNode right;
        Integer item;
        TreeNode(TreeNode left, TreeNode right, Integer element) {
            this.left = left;
            this.right = right;
            this.item = element;
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

    public class DiameterData {
        int diameter;
    }

    public int calculateDiamter(TreeNode node, DiameterData pDiameter) {
        if (node == null)
            return 0;

        int left = calculateDiamter(node.left, pDiameter);
        int right = calculateDiamter(node.right, pDiameter);

        pDiameter.diameter = Math.max(pDiameter.diameter, left + 1 + right);

        return Math.max(left, right) + 1;
    }


    //    public int diameter() {
//        return diameterHelper(root);
//    }
//
//    private int diameterHelper(TreeNode node) {
//        if (node == null) {
//            return 0;
//        }
//
//        int currentDiam = binaryTreeHeight(node.left) + binaryTreeHeight(node.right) + 1;
//
//        int leftDiam = diameterHelper(node.left);
//        int rightDiam = diameterHelper(node.right);
//
//        // use math.max as it makes code more readable.
//        return Math.max(currentDiam,  Math.max(leftDiam, rightDiam));
//    }



//    /**
//     * If the term is found then use the term:
//     * http://stackoverflow.com/questions/18903631/what-is-the-term-for-number-of-nodes-from-root-to-the-leaf-in-binary-tree
//     *
//     * else for sake of interview lets stick to the term height.
//     *
//     */
//    private int binaryTreeHeight(TreeNode node) {
//        if (node == null) {
//            return 0; // NOTE: "This is a count of number of nodes (inclusive) not count of number of edges"
//        }
//        int leftHeight = binaryTreeHeight(node.left);
//        int rightHeight = binaryTreeHeight(node.right);
//
//        return Math.max(rightHeight, leftHeight) + 1;
//    }


    public static void main(String[] args) {
//        // balanced tree
//        Integer[] a = {1, 2, 3, 4, 5, 6, 7};
//        Diameter diameter = new Diameter();
//        diameter.createBinaryTree(a);
//        System.out.print("Expected 5, Actual: ");
//        System.out.println(diameter.diameter());
//
//        // unbalanced tree
//        Integer[] a1 = {1, 2, 3, null, null, 6, 7};
//        Diameter diameter1 = new Diameter();
//        diameter1.createBinaryTree(a1);
//        System.out.print("Expected 4, Actual: ");
//        System.out.println(diameter1.diameter());

    }
}
