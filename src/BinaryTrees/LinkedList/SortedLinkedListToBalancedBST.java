package BinaryTrees.LinkedList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;


/**
 * References:
 * http://leetcode.com/2010/11/convert-sorted-list-to-balanced-binary.html
 * http://codereview.stackexchange.com/questions/51852/convert-sorted-linkedlist-into-balanced-binary-search-tree
 *
 * Basically remember one tiny thing -
 * Sorted linked-list is nothing but in-order traversal.
 * We are leveraging the same concept.
 *
 * Except:
 * instead of ptr.left, we do (low, mid-1)
 * instead of ptr.right we do (mid + 1, low - 1);
 *
 * Complexity:
 * O(n)
 *
 * BB:
 * 55
 */
public class SortedLinkedListToBalancedBST<T> {

    private TreeNode<T> root;

    private static class TreeNode<T> {
        TreeNode<T> left;
        T item;
        TreeNode<T> right;
    }

    public void convert(LinkedList<T> ll) {
        Iterator<T> itr = ll.iterator();
        root = inorderTraversal(0, ll.size() - 1, itr);
    }

    private TreeNode<T> inorderTraversal(int lb, int hb, Iterator<T> itr) {
        if (lb > hb)
            return null;

        int mid = (lb + hb) / 2;

        final TreeNode<T> treeNode = new TreeNode<T>();
        treeNode.left = inorderTraversal(lb, mid - 1, itr);
        treeNode.item = itr.next();
        treeNode.right = inorderTraversal(mid + 1, hb, itr);

        return treeNode;
    }

    public List<T> getInorderList() {
        final List<T> inorderList = new ArrayList<T>();
        inorderList(root, inorderList);
        return inorderList;
    }

    private void inorderList(TreeNode<T> node, List<T> list) {
        if (node != null) {
            inorderList(node.left, list);
            list.add(node.item);
            inorderList(node.right, list);
        }
    }
}
