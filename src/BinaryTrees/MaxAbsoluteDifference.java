package BinaryTrees;

/**
 * https://massivealgorithms.blogspot.com/2019/04/leetcode-1026-maximum-difference.html
 *
 * Given the root of a binary tree, find the maximum value
 * V for which there exists different nodes A and B where V = |A.val - B.val| and A is an ancestor of B.
 * (A node A is an ancestor of B if either: any child of A is equal to B, or any child of A is an ancestor of B.)
 *
 */
class MaxAbsoluteDifference {

    class TreeNoder {
        int val;
        TreeNoder left = null, right = null;

        TreeNoder(int data) {
            this.val = data;
        }
    }

    public int maxAncestorDiff(TreeNoder root) {
        return dfs(root, root.val, root.val);
    }

    public int dfs(TreeNoder root, int mn, int mx) {
        if (root == null) return mx - mn;

        mx = Math.max(mx, root.val);
        mn = Math.min(mn, root.val);

        return Math.max(dfs(root.left, mn, mx), dfs(root.right, mn, mx));
    }
}
