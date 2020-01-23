package BinaryTrees;

/**
 *  Given a sorted array create a balanced binary tree.
 *
 *  Complexity: O(n)
 *
 *  To test this we have a seperate interview question:
 *  http://stackoverflow.com/questions/742844/how-to-determine-if-binary-tree-is-balanced
 *
 *  Testing this can be dealt as a seperate interview question:
 *  http://stackoverflow.com/questions/18885747/how-to-test-a-if-a-bst-is-balanced-after-its-constructed-from-sorted-array
 *
 *  References:
 *  http://www.geeksforgeeks.org/microsoft-interview-set-35-campus-internship/
 */
public class BSTInorder {

    private TreeNode root;

    private static class TreeNode {
        TreeNode left;
        TreeNode right;
        int item;

        TreeNode (TreeNode left, TreeNode right, int item) {
            this.left = left;
            this.right = right;
            this.item = item;
        }
    }

    public void create (int[] a) {
        if (a == null) {
            throw new NullPointerException(" The input array cannot be null.");
        }
        root = createHelper(a, 0, a.length - 1);
    }

    private TreeNode createHelper (int[] a, int lb, int hb) {
        if (lb > hb) return null;

        int mid = (lb + hb) / 2;

        final TreeNode node  = new TreeNode(null, null, a[mid]);

        node.left = createHelper(a, lb, mid - 1);
        node.right =  createHelper(a, mid + 1, hb);

        return node;
    }

    void traverse() {
        inorder(root);
    }

    /**
     * Returning a list called inorder list, is the simplest ways to solve unit testing of these problems.
     */
    void inorder(TreeNode node) {
        if (node != null) {
            inorder(node.left);
            System.out.print(node.item + " ");
            inorder(node.right);
        }
    }
}
