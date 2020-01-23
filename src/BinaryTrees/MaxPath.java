package BinaryTrees;

/**
 * https://leetcode.com/problems/binary-tree-maximum-path-sum/
 */
public class MaxPath {

    class TreeNode {
        int val;
        TreeNode left = null, right = null;
        TreeNode(int data) {
            this.val = data;
        }
    }

    class MaxSoFar {
        int maxSoFar;
    }

    public int maxPathSum(TreeNode root) {
        MaxSoFar maxSoFar = new MaxSoFar();
        calculateSum(root, new MaxSoFar());
        return maxSoFar.maxSoFar;
    }

    public int calculateSum(TreeNode root, MaxSoFar maxSoFar) {
        if (root == null)
            return 0;

        int left = calculateSum(root.left, maxSoFar);
        int right = calculateSum(root.right, maxSoFar);

        int oneBranchAndSelf = Math.max(left, right) > 0 ? root.val + Math.max(left, right) : root.val;
        int bothBranchAndSelf = left + root.val + right;

        maxSoFar.maxSoFar = Math.max(maxSoFar.maxSoFar, Math.max(oneBranchAndSelf, bothBranchAndSelf));
        return maxSoFar.maxSoFar;
    }

}
