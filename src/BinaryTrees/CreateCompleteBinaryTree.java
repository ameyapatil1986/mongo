package BinaryTrees;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


/**
 * Reference:
 * ----------
 * http://www.geeksforgeeks.org/linked-complete-binary-tree-its-creation/
 * http://codereview.stackexchange.com/questions/64673/create-a-complete-binary-tree
 *
 *
 * Complexity:
 * -----------
 * O(n ^ 2)
 *
 * BB:
 * ---
 * 86
 *
 */
public class CreateCompleteBinaryTree<T> {

    private TreeNode<T> root;

    public CreateCompleteBinaryTree(List<T> items) {
        for (T item : items) {
            addItem(item);
        }
    }

    private static class TreeNode<T> {
        private TreeNode<T> left;
        private T item;
        private TreeNode<T> right;

        TreeNode(T item) {
            this.item = item;
        }
    }

    /**
     * Adds item, such the tree is a complete binary tree.
     *
     * @param item the item to be added.
     */
    public void addItem(T item) {

        System.out.println(item);

        if (root == null) {
            root = new TreeNode<T>(item);
            return;
        }

        final Queue<TreeNode<T>> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            final TreeNode<T> currNode = queue.poll();

            if (currNode.left == null) {
                currNode.left = new TreeNode<T>(item);
                return;
            }

            if (currNode.right == null) {
                currNode.right = new TreeNode<T>(item);
                return;
            }

            queue.add(currNode.left);
            queue.add(currNode.right);
        }
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((root == null) ? 0 : root.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        CreateCompleteBinaryTree other = (CreateCompleteBinaryTree) obj;
        return equal(root, other.root);
    }


    private static boolean equal(TreeNode node1, TreeNode node2) {

        if (node1 == null && node2 == null) return true;
        if (node1 == null || node2 == null) return false;
        if (node1.item != node2.item) {
            return false;
        }

        return equal(node1.left, node2.left) && equal(node1.right, node2.right);
    }


    public static void main(String[] args) {
//        CreateCompleteBinaryTree<Integer> ccbt = new CreateCompleteBinaryTree<Integer>(Arrays.asList(1, 2, 3, 4, 5));
//        Iterator<Integer> itr = ccbt.preOrderIterator();
//        while (itr.hasNext()) {
//            System.out.println(itr.next());
//        }
    }
}
