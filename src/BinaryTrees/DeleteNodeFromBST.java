package BinaryTrees;


/**
 * https://www.geeksforgeeks.org/binary-search-tree-set-2-delete/
 */
public class DeleteNodeFromBST {

    private static class TreeNode {
        private TreeNode left;
        private Integer val;
        private TreeNode right;
    }

    public TreeNode deleteNode(TreeNode node, int key) {
        if (node == null) return null;

        if (key < node.val) {
            node.left = deleteNode(node.left, key);
        } else if (key > node.val) {
            node.right = deleteNode(node.right, key);
        } else {
            // finally reached the node.
            if (node.left == null) {
                return node.right;
            }

            if (node.right == null) {
                return node.left;
            }

            // find in-oder successor
            TreeNode minNode = node.right;
            while (minNode.left != null) {
                minNode = minNode.left;
            }

            node.val = minNode.val;
            // delete successor.
            node.right = deleteNode(node.right, minNode.val);
        }

        return node;
    }
}
