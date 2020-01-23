package BinaryTrees;

public class SuccessorPredecessorWithParent {

    private TreeNode root;

    private class TreeNode {
        TreeNode left;
        TreeNode right;
        TreeNode parent;
        int item;

        TreeNode(TreeNode left, TreeNode right, TreeNode parent, int item) {
            this.left = left;
            this.right = right;
            this.parent = parent;
            this.item = item;
        }
    }

    public void makeBinarySearchTree(int[] a) {
        for (int i : a) {
            addElement(i);
        }
    }

    public void addElement(int element) {
        if (root == null) {
            root = new TreeNode(null, null, null, element);
        } else {
            TreeNode prevNode = null;
            TreeNode node = root;

            while (node != null) {
                prevNode = node;
                // no definition exists, and for convenience we will stick to this one.
                if (element < node.item) {
                    node = node.left;
                } else {
                    node = node.right;
                }
            }

            if (element < prevNode.item) {
                prevNode.left = new TreeNode(null, null, prevNode, element);
            } else {
                prevNode.right = new TreeNode(null, null, prevNode, element);
            }
        }
    }

    private TreeNode search (int x) {
        /**
         * QQ:
         * How should private members deal with null instance values ?
         * This method remains unchanged by the answer but question is still generic
         */
        TreeNode node = root;

        while (node != null) {
            if (x < node.item) {
                node = node.left;
            } else if (x  > node.item) {
                node = node.right;
            } else {
                /**
                 * http://stackoverflow.com/questions/19035732/a-return-in-a-while-loop-vs-a-break
                 */
                return node;
            }
        }

        /**
         *  http://stackoverflow.com/questions/19035732/a-return-in-a-while-loop-vs-a-break
         */
        return null;
    }

    public Integer getSucessor(int x) {
        TreeNode node = search(x);
        if (node == null) {
            throw new IllegalArgumentException();
        }

        if (node.right != null) {
            TreeNode successor = node.right;
            while (successor.left != null) {
                successor = successor.left;
            }
            return successor.item;
        }

        TreeNode parent = node.parent;
        while (parent != null && parent.right == node) {
            node = parent;
            parent = node.parent;
        }

        return parent == null ? null : parent.item;
    }


    /**
     * exactly same as successor except left, and right swapped.
     * mug up only successor, predecessor should be swapped at interview time.
     */
    public Integer getPredecessor(int x) {
        TreeNode node = search(x);
        if (node == null) {
            throw new IllegalArgumentException();
        }

        if (node.left != null) {
            TreeNode predecessor = node.left;
            while (predecessor.right != null) {
                predecessor = predecessor.right;
            }
            return predecessor.item;
        }

        TreeNode parent = node.parent;
        while (parent!= null && parent.left == node) {
            node = parent;
            parent = node.parent;
        }

        return parent == null ? null : parent.item;
    }
}
