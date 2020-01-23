package BinaryTrees.LinkedList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Converts a binary tree to a doubly linkedlist.
 *
 * Complexity: O(n)
 *
 * References:
 * http://cslibrary.stanford.edu/109/TreeListRecursion.html
 * http://codereview.stackexchange.com/questions/31841/convert-tree-to-circular-doubly-linkedlist-critique-request
 *
 * BB No: 34
 */
public class BinaryTreeToLinkedList<T> {

    private TreeNode<T> root;

    private static class TreeNode<T> {
        TreeNode<T> left;
        TreeNode<T> right;
        T item;
        public TreeNode(TreeNode<T> left, TreeNode<T> right, T item) {
            this.left = left;
            this.right = right;
            this.item = item;
        }
    }


    public void createBinaryTree (T[] arr) {
        if (arr == null)  {
            throw new NullPointerException("The input array is null.");
        }

        root = new TreeNode<T>(null, null, arr[0]);

        final Queue<TreeNode<T>> queue = new LinkedList<TreeNode<T>>();
        queue.add(root);

        final int half = arr.length / 2;

        for (int i = 0; i < half; i++) {
            if (arr[i] != null) {
                final TreeNode<T> current = queue.poll();
                final int left = 2 * i + 1;
                final int right = 2 * i + 2;

                if (arr[left] != null) {
                    current.left = new TreeNode<>(null, null, arr[left]);
                    queue.add(current.left);
                }
                if (right < arr.length && arr[right] != null) {
                    current.right = new TreeNode<>(null, null, arr[right]);
                    queue.add(current.right);
                }
            }
        }
    }

    /**
     * NOTE:
     * -----
     * left: first node of left-linkedlist
     * leftlast : last node of left-linkedlist
     *
     * right : first node of the right linkedlist
     * rightlast : last node of the right linkedlist.
     */
    private TreeNode<T> appendDLL (TreeNode<T> leftList, TreeNode<T> rightList) {
        if (leftList == null) return rightList;
        if (rightList == null) return leftList;

        // get last element of left linkedlist
        TreeNode<T> leftListLastNode = leftList.left;
        TreeNode<T> rightListLastNode = rightList.left;

        leftListLastNode.right = rightList;
        rightList.left = leftListLastNode;

        // now we form the list into circular again.
        leftList.left = rightListLastNode;
        rightListLastNode.right = leftList;

        return leftList;
    }


    /**
     * If interviewer specified to LL then simply untie first and last.
     */
    public void convertToLinkedList() {
        if (root == null) {
            throw new NullPointerException("The tree is empty");
        }
        root = postOrder(root);
    }

    /**
     * Yes this forcibly has to be postorder
     */
    private TreeNode<T> postOrder(TreeNode<T> node) {
        if (node == null) {
            return null;
        }

        TreeNode<T> leftList = postOrder(node.left);
        TreeNode<T> rightList = postOrder(node.right);

        // convert the current node into doubly LL
        node.left = node;
        node.right = node;

        // hook current node.
        leftList = appendDLL (leftList, node);

        // hook right side linkedlist
        leftList = appendDLL (leftList, rightList);

        return leftList;
    }

    /*
     * this is the only "sane" way to test a code, that flips its own internal data structure.
     */
    public List<T> toList() {
        List<T> nodes = new ArrayList<T>();
        TreeNode<T> node = root;

        //        for (TreeNode<T> node = root; node != null;  node = node.right) {
        //            System.out.println("--- 139 --- ");
        //            nodes.add(node.item);
        //        }

        // since its a doubly linkedlist we cannot use a for loop.
        do {
            nodes.add(node.item);
            node = node.right;
        } while (node != root);

        return nodes;
    }

}
