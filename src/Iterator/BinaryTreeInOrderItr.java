package Iterator;

import java.util.*;

/**
 *
 *
 */
public class BinaryTreeInOrderItr {

    class TreeNode {
        TreeNode left;
        TreeNode right;
        int item;
    }

    final Stack<TreeNode> stack = new Stack<TreeNode>();

    public BinaryTreeInOrderItr(TreeNode root) {
        pushLeftChildren(root);
    }

    private void pushLeftChildren(TreeNode cur) {
        while (cur != null) {
            stack.push(cur);
            cur = cur.left;
        }
    }

    public boolean hasNext() {
        return !stack.isEmpty();
    }

    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException("All nodes have been visited!");
        }

        TreeNode res = stack.pop();
        pushLeftChildren(res.right);

        return res.item;
    }

    public void remove() {
        throw new UnsupportedOperationException("remove() is not supported.");
    }
}



