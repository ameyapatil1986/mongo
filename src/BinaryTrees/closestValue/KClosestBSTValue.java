package BinaryTrees.closestValue;

import java.util.*;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class KClosestBSTValue {

    private static class TreeNode<Integer> {
        private TreeNode<Integer> left;
        private Integer val;
        private TreeNode<Integer> right;

        TreeNode(Integer val) {
            this.val = val;
        }
    }

    public List<Integer> closestKValues(TreeNode<Integer> root, double target, int k) {
        Queue<Integer> list = new LinkedList<>();

        if (root == null) {
            return Collections.emptyList();
        }

        final Stack<TreeNode> stack = new Stack<>();
        stack.add(root);
        TreeNode<Integer> node = root;

        while(!stack.isEmpty()) {

            while(node != null) {
                stack.push(node);
                node = node.left;
            }

            node = stack.pop();


            if (list.size() < k) {
                list.add(node.val);
            } else {
                if (Math.abs(list.peek() - target) > Math.abs(node.val - target)) {
                    list.poll();
                    list.add(node.val);
                }
            }

            node = node.right;
        }

        return (List<Integer>) list;
    }
}
