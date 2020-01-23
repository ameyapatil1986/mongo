package BinaryTrees;

class TreeNodef {
    TreeNodef left;
    int val;
    TreeNodef right;

    TreeNodef(int item) {
        this.val = val;
    }
}


/**
 * https://www.geeksforgeeks.org/binary-search-tree-set-2-delete/
 */
public class DeleteNodeFromBST {

    public class Solution {

        public TreeNodef deleteNode(TreeNodef root, int key) {

            if (root == null) return root;

            if (root.val > key) {
                root.left = deleteNode(root.left, key);
            } else if (root.val < key) {
                root.right = deleteNode(root.right, key);
            } else {
                if (root.left == null) {
                    return root.right;
                } else if (root.right == null) {
                    return root.left;
                }

                // find in-oder successor
                TreeNode minNode = inorderSuccessor(root.right);
                while (minNode != null) {
                    minNode = minNode.left;
                }

                root.val = minNode.val;
                // delete successor.
                root.right = deleteNode(root.right, minNode.val);
            }

            return root;
        }

        int minValue(Node root) {
            int minv = root.key;
            while (root.left != null) {
                minv = root.left.key;
                root = root.left;
            }
            return minv;
        }
    }
}
