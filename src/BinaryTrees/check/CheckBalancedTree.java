package BinaryTrees.check;


import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


public class CheckBalancedTree<E> {

    private TreeNode<E> root;

    public CheckBalancedTree(List<E> items) {
        create(items);
    }

    private void create (List<E> items) {
        if (items.size() == 0) { throw new IllegalArgumentException("The list is empty."); }

        root = new TreeNode<>(items.get(0));

        final Queue<TreeNode<E>> queue = new LinkedList<>();
        queue.add(root);

        final int half = items.size() / 2;

        for (int i = 0; i < half; i++) {
            if (items.get(i) != null) {
                final TreeNode<E> current = queue.poll();
                int left = 2 * i + 1;
                int right = 2 * i + 2;

                if (items.get(left) != null) {
                    current.left = new TreeNode<>(items.get(left));
                    queue.add(current.left);
                }
                if (right < items.size() && items.get(right) != null) {
                    current.right = new TreeNode<>(items.get(right));
                    queue.add(current.right);
                }
            }
        }
    }

    // create a binary tree.
    private static class TreeNode<E> {
        private TreeNode<E> left;
        private E item;
        private TreeNode<E> right;

        TreeNode(E item) {
            this.item = item;
        }
    }

    private static class TreeData {
        private int height;
        private boolean isBalanced;

        TreeData(int height, boolean isBalanced) {
            this.height = height;
            this.isBalanced = isBalanced;
        }
    }

    public boolean checkIfBalanced() {
        if (root == null) {
            throw new IllegalStateException();
            // if interviewer absolutely wants null to be valid input then do premature optimization
            // return true;
        }
        return checkBalanced(root).isBalanced;
    }

    /*
     *  O(n) - space
     *  http://codereview.stackexchange.com/questions/80056/check-if-a-binary-tree-is-balanced
     *  http://stackoverflow.com/questions/28384987/space-complexity-on-creating-an-object-on-recursion
     *  Consider a tree with a structure like:
     *    A
     *   / \
     *  B   C
     *     / \
     *    D   E
     *       / \
     *      F   G
     *
     * Now by the time you reach G, the number of TreeData objects created over stack frames is O(n) ie B, D, F
     */
    public TreeData checkBalanced(TreeNode<E> node) {
        if (node == null) return new TreeData(-1, true);

        TreeData tdLeft = checkBalanced(node.left);

        if (!tdLeft.isBalanced) return new TreeData(-1, false); // if boolean value is false, then no need to return the correct value for height.

        TreeData tdRight = checkBalanced(node.right);

        if (tdRight.isBalanced && Math.abs(tdLeft.height - tdRight.height) <= 1) {
            return new TreeData(Math.max(tdLeft.height, tdRight.height) + 1, true);
        }

        return new TreeData(-1, false); // if boolean value is false, then no need to return the correct value for height.
    }
}
