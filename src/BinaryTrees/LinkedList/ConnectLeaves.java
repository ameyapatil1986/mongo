package BinaryTrees.LinkedList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


/**
 *
 * http://www.geeksforgeeks.org/connect-leaves-doubly-linked-list/
 *
 * BB:
 * ---
 * 95
 *
 * Complexity:
 * ----------
 * O(n)
 *
 */
public final class ConnectLeaves<T> {

    private TreeNode<T> root;
    private TreeNode<T> first;
    private TreeNode<T> firstLeaf;

    public ConnectLeaves(List<T> items) {
        create(items);
    }

    private static class TreeNode<T> {
        TreeNode<T> left;
        TreeNode<T> right;
        T item;
        public TreeNode(T item) {
            this.item = item;
        }
    }

    private void create (List<T> items) {
        root = new TreeNode<T>(items.get(0));

        final Queue<TreeNode<T>> queue = new LinkedList<>();
        queue.add(root);

        final int half = items.size() / 2;

        for (int i = 0; i < half; i++) {
            if (items.get(i) != null) {
                final TreeNode<T> current = queue.poll();
                final int left = 2 * i + 1;
                final int right = 2 * i + 2;

                if (items.get(left) != null) {
                    current.left = new TreeNode<T>(items.get(left));
                    queue.add(current.left);
                }
                if (right < items.size() && items.get(right) != null) {
                    current.right = new TreeNode<T>(items.get(right));
                    queue.add(current.right);
                }
            }
        }
    }



    public void joinLeaves() {
        PrevNodeStore<T> nl = new PrevNodeStore<T>();
        computeJoins(root, nl);
    }


    private static class PrevNodeStore<T> {
        TreeNode<T> node;
    }


    public void computeJoins(TreeNode<T> node, PrevNodeStore<T> prevNodeStore) {
        if (node == null) {
            return;
        }

        if (node.left == null && node.right == null) {

            if (prevNodeStore.node == null) {
                prevNodeStore.node = firstLeaf = node;
            } else {
                prevNodeStore.node.right = node;
                node.left = prevNodeStore.node;

                prevNodeStore.node = node;
            }
            return;
        }

        computeJoins(node.left, prevNodeStore);
        computeJoins(node.right, prevNodeStore);
    }

    public List<T> toList() {
        if (firstLeaf == null) {
            throw new IllegalStateException();
        }

        List<T> list = new ArrayList<>();
        for (TreeNode<T> n = firstLeaf; n != null; n = n.right) {
            list.add(n.item);
        }
        return list;
    }

}
