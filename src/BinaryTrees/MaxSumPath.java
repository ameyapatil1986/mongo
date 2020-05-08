package BinaryTrees;

/**
 * https://www.youtube.com/results?search_query=124.+Binary+Tree+Maximum+Path+Sum
 * https://leetcode.com/problems/binary-tree-maximum-path-sum/
 * https://www.programcreek.com/2013/02/leetcode-binary-tree-maximum-path-sum-java/
 */
public class MaxSumPath {

    private static class TreeNode<T> {
        TreeNode<T> left;
        int val;
        TreeNode<T> right;

        TreeNode(TreeNode<T> left, int item, TreeNode<T> right) {
            this.left = left;
            this.val = item;
            this.right = right;
        }
    }

    private class MaxSum {
        int maxSum = Integer.MIN_VALUE;
    }

    public int maxPathSum(TreeNode root) {
        MaxSum maxSum = new MaxSum();
        calculateSum(root, maxSum);
        return maxSum.maxSum;
    }

    public int calculateSum(TreeNode node, MaxSum pMaxSum) {
        if (node == null)
            return 0;

        int leftSum = Math.max(0, calculateSum(node.left, pMaxSum));
        int rightSum = Math.max(0, calculateSum(node.right, pMaxSum));

        pMaxSum.maxSum = Math.max(pMaxSum.maxSum, leftSum + node.val + rightSum);

        return Math.max(leftSum, rightSum) + node.val;
    }
}
