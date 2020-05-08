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

        Stack<TreeNode> stack = new Stack<>();
        TreeNode<Integer> node = root;

        while(!stack.isEmpty() || node != null) {
            while(node != null){
                stack.push(node);
                node = node.left;
            }

            node = stack.pop();

            list.add(node.val);
            if (list.size() > k) {
                if (Math.abs(list.peek() - target) > Math.abs(node.val - target)) {
                    list.poll();
                } else {
                    break;
                }
            }

            node = node.right;
        }

        return (List<Integer>) list;
    }
}
