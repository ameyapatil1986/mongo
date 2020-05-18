package BinaryTrees.LCA;

/**
 * https://www.youtube.com/watch?v=Fl0fIKfTZKA
 * https://leetcode.com/problems/lowest-common-ancestor-of-deepest-leaves/
 *
 * Given a rooted binary tree, return the lowest common ancestor of its deepest leaves.
 *
 * Recall that:
 *
 * The node of a binary tree is a leaf if and only if it has no children
 * The depth of the root of the tree is 0, and if the depth of a node is d,
 * the depth of each of its children is d+1.
 * The lowest common ancestor of a set S of nodes is the node A with the largest
 * depth such that every node in S is in the subtree with root A.
 *
 * Input: root = [1,2,3]
 * Output: [1,2,3]
 * Explanation:
 * The deepest leaves are the nodes with values 2 and 3.
 * The lowest common ancestor of these leaves is the node with value 1.
 * The answer returned is a TreeNode object (not an array) with serialization "[1,2,3]".
 *
 * Example 2:
 *
 * Input: root = [1,2,3,4]
 * Output: [4]
 * Example 3:
 *
 * Input: root = [1,2,3,4,5]
 * Output: [2,4,5]
 *
 */
public class LCAofDeepestNodes {

    class Pair {
        TreeNode node;
        int d;
        Pair(TreeNode node, int d) {
            this.node = node;
            this.d = d;
        }
    }

    class TreeNode {
        TreeNode left;
        TreeNode right;
    }

    public TreeNode lcaDeepestLeaves(TreeNode root) {
        Pair p = getLca(root);
        return p.node;
    }


    private Pair getLca(TreeNode pNode) {
        if (pNode == null) return new Pair(null, 0);

        Pair l = getLca(pNode.left);
        Pair r = getLca(pNode.right);

        if (l.d == r.d) {
            return new Pair(pNode, l.d + 1);
        } else {
            return  l.d > r.d ? new Pair(l.node, l.d + 1) : new Pair(r.node, l.d + 1);
        }
    }
}
